package mishka.mingeo.export

import android.content.Context
import android.content.Intent
import android.os.Environment
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import mishka.mingeo.data.datamanager.DataManager
import mishka.mingeo.data.model.Borehole
import mishka.mingeo.data.model.Pumping
import mishka.mingeo.di.ApplicationContext
import java.io.File
import java.io.FileWriter
import javax.inject.Inject

class Exporter @Inject constructor(val dataManager: DataManager, val activity: AppCompatActivity) {
    private val fileName = "test_file.csv"
    private val file = File(Environment.getExternalStorageDirectory(), fileName)
    private val headers = "Dynamic level,Debit,Dynamic level from earth level,Decrease,Distance from central borehole,Time,lg(t),lg(t/r)"
//    private val headers = "Динамический уровень,Дебит,Динамический уровень от поверхности земли,Понижение,Расстояние от центральной скважины,Время в сутках,lg(t),lg(t/r)"

    init {
        if (!file.exists())
            file.createNewFile()
    }

    fun export(pumping: Pumping) {
        writeDataToFile(pumping)
        sendFile()
    }


    private fun writeDataToFile(pumping: Pumping) {
        val writer = FileWriter(file)

        for (borehole in dataManager.getBoreholesForPumping(pumping)) {
            writeBorehole(pumping, borehole, writer)
        }

        writer.flush()
        writer.close()
    }

    private fun writeBorehole(pumping: Pumping, borehole: Borehole, writer: FileWriter) {
        if (pumping.centralBoreholeId == borehole.id)
            writer.append("Borehole ${borehole.id} [Central]")
        else
            writer.append("Borehole ${borehole.id}")
        writer.append("\n")


        writer.append(headers)
        writer.append("\n")
        var row = ""
        for (depth in dataManager.getBoreholeDepths(borehole)) {
            val dynamicLevel = depth.depth + borehole.headHeight
            val debit = pumping.pumpPower
            val dynamicLevelFromEarthLevel = depth.depth
            val decrease = depth.relativeDepth(borehole.initialDepth)
            val distanceFromCentral = borehole.distanceFromCentral
            val time = depth.daysFrom(pumping.startPumpingTime!!)
            val logT = depth.logDaysFrom(pumping.startPumpingTime!!)
            val logTR = depth.logDaysFromDividedByDistance(pumping.startPumpingTime!!, borehole.distanceFromCentral)

            row = "$dynamicLevel,$debit,$dynamicLevelFromEarthLevel,$decrease,$distanceFromCentral,$time,$logT,$logTR"

            writer.append(row)
            writer.append("\n")

        }

        writer.append("\n")

    }


    private fun sendFile() {

        val intent = Intent(Intent.ACTION_SEND)

        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_EMAIL, "mbolgovich@gmail.com")
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject")
        intent.putExtra(Intent.EXTRA_TEXT, "Text")
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

        val fileUri = FileProvider.getUriForFile(activity, "mingeo.provider", file)
        intent.putExtra(Intent.EXTRA_STREAM, fileUri)

        activity.startActivity(intent)
    }
}
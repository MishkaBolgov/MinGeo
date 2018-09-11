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
    init {
        if(!file.exists())
            file.createNewFile()
    }

    fun export(pumping: Pumping){
        writeDataToFile(pumping)
        sendFile()
    }


    private fun writeDataToFile(pumping: Pumping) {
        val writer = FileWriter(file)
        for (borehole in dataManager.getBoreholesForPumping(pumping)){
            writeBorehole(borehole, writer)
        }

        writer.flush()
        writer.close()
    }


    private fun writeBorehole(borehole: Borehole, writer: FileWriter) {
        var row: String
        for (depth in dataManager.getBoreholeDepths(borehole)){
            row = "${depth.depth},${depth.days}\n"
            writer.append(row)
        }
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
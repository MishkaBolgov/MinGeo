package mishka.mingeo.view.pumping.pumpingsummary

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import kotlinx.android.synthetic.main.pump_power_dialog.view.*
import mishka.mingeo.R

class PumpPowerDialog : DialogFragment() {
    lateinit var listener: OnSetPumpPowerListener
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("Мощность насоса")

        val view = LayoutInflater.from(activity).inflate(R.layout.pump_power_dialog, null)

        builder.setView(view)

        builder.setPositiveButton("Сохранить", object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                val power = view.etPumpPower.text.toString().toInt()
                listener.onPumpPowerSet(power)
            }

        })

        return builder.create()
    }

    interface OnSetPumpPowerListener {
        fun onPumpPowerSet(value: Int)
    }
}
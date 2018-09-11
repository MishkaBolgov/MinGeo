package mishka.mingeo.view.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.add_depth_dialog.view.*
import kotlinx.android.synthetic.main.pump_power_dialog.view.*
import mishka.mingeo.R

abstract class SetValueDialog : DialogFragment() {
    var listener: SetValueListener? = null
    abstract val title: String
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(title)

        val dialogView = LayoutInflater.from(activity).inflate(R.layout.add_depth_dialog, null)

        builder.setView(dialogView)

        builder.setPositiveButton("Ок", DialogInterface.OnClickListener { dialogInterface, i ->
            if (dialogView.value.text.toString().isEmpty()) {
                Toast.makeText(activity, "Введено недопустимое значение", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            val value = dialogView.value.text.toString().toFloat()
            listener?.onValueSet(value)
        })

        return builder.create()
    }

    interface SetValueListener {
        fun onValueSet(value: Float)
    }
}
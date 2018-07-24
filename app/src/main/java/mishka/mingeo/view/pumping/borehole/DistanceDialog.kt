package mishka.mingeo.view.pumping.borehole


import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.distance_dialog.*
import kotlinx.android.synthetic.main.distance_dialog.view.*

import mishka.mingeo.R

class DistanceDialog : DialogFragment() {
    lateinit var distanceSetListener: OnDistanceSetListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)

        builder.setTitle("Растояние до основной скважины")

        val view = LayoutInflater.from(activity).inflate(R.layout.distance_dialog, null)

        builder.setPositiveButton("Сохранить", object:DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {
                distanceSetListener.onDistanceSet(view.etDistance.text.toString().toInt())
            }
        })

        builder.setView(view)

        return builder.create()
    }

    interface OnDistanceSetListener{
        fun onDistanceSet(distance: Int)
    }

}


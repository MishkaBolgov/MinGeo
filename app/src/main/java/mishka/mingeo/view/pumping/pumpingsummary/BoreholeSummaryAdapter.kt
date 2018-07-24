package mishka.mingeo.view.pumping.pumpingsummary

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import mishka.mingeo.R
import mishka.mingeo.data.model.Borehole
import javax.inject.Inject

class BoreholeSummaryAdapter @Inject constructor(): RecyclerView.Adapter<BoreholeViewHolder>() {
    var boreholes: List<Borehole> = ArrayList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BoreholeViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.borehole_view_holder, parent, false)
        return BoreholeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return boreholes.size
    }

    override fun onBindViewHolder(holder: BoreholeViewHolder?, position: Int) {
        holder?.borehole = boreholes[position]
    }

}

class BoreholeViewHolder(val view: View): RecyclerView.ViewHolder(view){
    var borehole: Borehole? = null
    set(value) {
        field = value
        view.findViewById<TextView>(R.id.boreholeId).text = "Скважина №${borehole?.id}"
    }

}
package mishka.mingeo.view.pumpinglist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.list_item.view.*

import javax.inject.Inject

import mishka.mingeo.R
import mishka.mingeo.data.model.Pumping
import mishka.mingeo.export.Exporter

class PumpingListAdapter @Inject constructor(val exporter: Exporter) : RecyclerView.Adapter<PumpingListAdapter.PumpingItemViewHolder>() {

    var pumpings: List<Pumping> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    lateinit var pumpingListActivity: PumpingListActivity

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PumpingItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return PumpingItemViewHolder(view, pumpingListActivity, exporter)
    }

    override fun onBindViewHolder(holder: PumpingItemViewHolder, position: Int) {
        holder.pumping = pumpings[position]
    }

    override fun getItemCount(): Int {
        return pumpings.size
    }


    class PumpingItemViewHolder(itemView: View, private val pumpingListActivity: PumpingListActivity, val exporter: Exporter) : RecyclerView.ViewHolder(itemView) {

        var pumping: Pumping? = null
            set(value) {
                field = value
                itemView.leftContent.text = "Выкачка ${value?.id}"
            }

        init {
            itemView.setOnClickListener { pumpingListActivity.onPumpingSelected(pumping!!) }
            itemView.setOnLongClickListener {
                pumping?.let {
                    exporter.export(it)
                }
                true
            }
        }

    }
}

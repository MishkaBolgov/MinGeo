package mishka.mingeo.view.pumping

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item.view.*
import mishka.mingeo.R
import mishka.mingeo.data.model.Borehole
import mishka.mingeo.view.pumping.borehole.BoreholeActivity

import javax.inject.Inject

class BoreholeAdapter @Inject constructor() : RecyclerView.Adapter<BoreholeAdapter.BoreholeView>() {
    var boreholes: List<Borehole> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoreholeView {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return BoreholeView(view)
    }

    override fun getItemCount(): Int {
        return boreholes.size
    }

    override fun onBindViewHolder(holder: BoreholeView, position: Int) {
        holder.borehole = boreholes[position]
        holder.setText(position)
    }

    class BoreholeView(view: View) : RecyclerView.ViewHolder(view) {
        var borehole: Borehole? = null
            set(value) {
                field = value
                itemView.setOnClickListener {
                    var context = itemView.context
                    var intent = Intent(context, BoreholeActivity::class.java)
                    intent.putExtra("borehole", value)
                    context.startActivity(intent)
                }
            }

        fun setText(position: Int) {
            itemView.leftContent.text = "Скважина ${position + 1}"
        }


    }
}

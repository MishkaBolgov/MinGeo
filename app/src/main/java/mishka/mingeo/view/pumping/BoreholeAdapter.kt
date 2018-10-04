package mishka.mingeo.view.pumping

import android.content.Intent
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import kotlinx.android.synthetic.main.list_item_with_options.view.*
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

    var centralBoreholeId: Long? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoreholeView {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_with_options, parent, false)
        return BoreholeView(view)
    }

    override fun getItemCount(): Int {
        return boreholes.size
    }

    override fun onBindViewHolder(holder: BoreholeView, position: Int) {
        holder.borehole = boreholes[position]
        holder.setText(position)
        if (boreholes[position].id == centralBoreholeId)
            holder.markAsCentral()
        else holder.markAsSecondary()
    }

    class BoreholeView(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.btnOptions.setOnClickListener {
                val menu = PopupMenu(itemView.context, itemView.btnOptions)
                menu.inflate(R.menu.borehole_list_item_menu)
                menu.setOnMenuItemClickListener {

                    when (it.itemId) {
                        R.id.markAsCentral ->
                            (view.context as PumpingActivity).markBoreholeAsCentral(borehole)
                    }
                    true
                }
                menu.show()
            }
        }

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

        fun markAsCentral() {
            itemView.leftContent.typeface = Typeface.DEFAULT_BOLD
        }

        fun markAsSecondary() {
            itemView.leftContent.typeface = Typeface.DEFAULT
        }


    }
}

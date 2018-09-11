package mishka.mingeo.view.pumping.borehole

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item.view.*
import mishka.mingeo.R
import mishka.mingeo.data.model.BoreholeDepth
import javax.inject.Inject

class DepthAdapter @Inject constructor(): RecyclerView.Adapter<DepthAdapter.DepthViewHolder>() {
    var depths: List<BoreholeDepth> = ArrayList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepthViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        var boreholeView = DepthViewHolder(view)
        return boreholeView
    }

    override fun getItemCount() = depths.size


    override fun onBindViewHolder(holder: DepthViewHolder, position: Int) {
        holder.depth = depths[position]
    }

    class DepthViewHolder(view: View): RecyclerView.ViewHolder(view){
        var depth: BoreholeDepth? = null
        set(value) {
            field = value
            itemView.leftContent.text = "${field?.depth}"
            itemView.rightContent.text = "${field?.days}"
        }
    }
}
package mishka.mingeo.view.pumping.note

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.android.synthetic.main.note.view.*
import mishka.mingeo.R
import mishka.mingeo.data.model.Note
import javax.inject.Inject

class NoteAdapter @Inject constructor(val recordPlayer: RecordPlayer) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    var notes: List<Note> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return NoteViewHolder(view, recordPlayer)
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.note = notes[position]
        holder.setName(position)
    }

    class NoteViewHolder(view: View, val recordPlayer: RecordPlayer) : RecyclerView.ViewHolder(view) {
        var note: Note? = null

        init {
            itemView.setOnClickListener {
                note?.let {
                    recordPlayer.play(it.path)
                }
            }
        }

        fun setName(position: Int) {
            itemView.leftContent.text = "Заметка ${position + 1}"
        }
    }
}
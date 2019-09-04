package com.cacagdas.noteassistant.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.cacagdas.noteassistant.R
import com.cacagdas.noteassistant.service.model.Note

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {
    private lateinit var notes: List<Note>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_item, parent, false)
        return NoteHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val currentNote: Note = notes.get(position)
        holder.textViewTitle.text = currentNote.title
        holder.textViewDescription.text = currentNote.description
    }

    override fun getItemCount(): Int = notes.size

    fun setNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    open class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.text_view_title)
        internal lateinit var textViewTitle: TextView
        @BindView(R.id.text_view_description)
        internal lateinit var textViewDescription: TextView

        init {
            ButterKnife.bind(itemView)
        }
    }
}
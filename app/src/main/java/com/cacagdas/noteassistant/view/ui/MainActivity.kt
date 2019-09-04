package com.cacagdas.noteassistant.view.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.cacagdas.noteassistant.viewmodel.NoteViewModel
import com.cacagdas.noteassistant.R
import com.cacagdas.noteassistant.service.model.Note
import com.cacagdas.noteassistant.view.adapter.NoteAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var noteViewModel: NoteViewModel

    @BindView(R.id.recycler_view)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.button_add_note)
    lateinit var buttonAddNote: Button

    private lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ButterKnife.bind(this)

        buttonAddNote.setOnClickListener {
            val intent: Intent = Intent(this@MainActivity, AddNoteActivity::class.java)
            startActivityForResult(intent,
                ADD_NOTE_REQUEST
            )
        }

        recyclerView.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        recyclerView.hasFixedSize()
        recyclerView.adapter = noteAdapter

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        noteViewModel.getAllNotes().observe(this, Observer<List<Note>>{ notes ->
            noteAdapter.setNotes(notes)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_NOTE_REQUEST && resultCode == Activity.RESULT_OK) {
            val title: String? = data?.getStringExtra(AddNoteActivity.EXTRA_TITLE)
            val description: String? = data?.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION)

            val note: Note =
                Note(title!!, description!!)
            noteViewModel.insert(note)
        }
    }

    companion object {
        const val ADD_NOTE_REQUEST: Int = 1
    }


}

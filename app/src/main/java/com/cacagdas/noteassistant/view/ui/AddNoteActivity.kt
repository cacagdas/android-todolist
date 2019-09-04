package com.cacagdas.noteassistant.view.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.cacagdas.noteassistant.R

class AddNoteActivity : AppCompatActivity() {

    @BindView(R.id.edit_text_title)
    lateinit var editTextTitle: EditText

    @BindView(R.id.edit_text_description)
    lateinit var editTextDescription: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        ButterKnife.bind(this)

        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_close)
        title = "Add Note"
    }

    private fun saveNote() {
        val title: String = editTextTitle.text.toString()
        val description: String = editTextDescription.text.toString()

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Insert title and description", Toast.LENGTH_SHORT).show()
            return
        }

        lateinit var data: Intent
        data.putExtra(EXTRA_TITLE, title)
        data.putExtra(EXTRA_DESCRIPTION, description)

        setResult(Activity.RESULT_OK)
        finish()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater: MenuInflater = menuInflater
        menuInflater.inflate(R.menu.add_note_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.save_note -> { saveNote()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_TITLE: String = "com.cacagdas.noteassistantmvvm.EXTRA_TITLE"
        const val EXTRA_DESCRIPTION: String = "com.cacagdas.noteassistantmvvm.EXTRA_DESCRIPTION"
    }

}

package com.cacagdas.noteassistant.viewmodel

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cacagdas.noteassistant.service.model.Note
import com.cacagdas.noteassistant.service.repository.NoteRepository

class NoteViewModel : AndroidViewModel {

    private var repository: NoteRepository
    private var allNotes: LiveData<List<Note>>

    constructor(@NonNull application: Application) : super(application) {
        repository = NoteRepository(application)
        allNotes = repository.getAllNotes()
    }

    fun insert(note: Note) = repository.insert(note)
    fun update(note: Note) = repository.update(note)
    fun delete(note: Note) = repository.delete(note)
    fun deleteAllNotes(note: Note) = repository.deleteAllNotes(note)
    fun getAllNotes(): LiveData<List<Note>> = allNotes
}
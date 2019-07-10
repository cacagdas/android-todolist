package com.cacagdas.noteassistantmvvm

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class NoteRepository {

    private lateinit var noteDao: NoteDao
    private lateinit var allNotes: LiveData<List<Note>>

    constructor(application: Application) {
        val database: NoteDatabase? = NoteDatabase.getInstance(application)
        noteDao = database!!.noteDao()
        allNotes = noteDao.getAllNotes()
    }

    fun insert(note: Note) {
        InsertNoteAsyncTask(noteDao).execute(note)
    }
    fun update(note: Note) {
        UpdateNoteAsyncTask(noteDao).execute(note)
    }
    fun delete(note: Note) {
        DeleteNoteAsyncTask(noteDao).execute(note)
    }
    fun deleteAllNotes(note: Note) {
        DeleteAllNotesAsyncTask(noteDao).execute()
    }
    fun getAllNotes(): LiveData<List<Note>> = allNotes

    class InsertNoteAsyncTask(private var noteDao: NoteDao) : AsyncTask<Note, Void, Void>() {

        override fun doInBackground(vararg notes: Note?): Void? {
            noteDao.insert(notes[0]!!)
            return null
        }
    }

    class UpdateNoteAsyncTask(private var noteDao: NoteDao) : AsyncTask<Note, Void, Void>() {

        override fun doInBackground(vararg notes: Note?): Void? {
            noteDao.update(notes[0]!!)
            return null
        }
    }

    class DeleteNoteAsyncTask(private var noteDao: NoteDao) : AsyncTask<Note, Void, Void>() {

        override fun doInBackground(vararg notes: Note?): Void? {
            noteDao.delete(notes[0]!!)
            return null
        }
    }

    class DeleteAllNotesAsyncTask(private var noteDao: NoteDao) : AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg voids: Void?): Void? {
            noteDao.deleteAllNotes()
            return null
        }
    }
}
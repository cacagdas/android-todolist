package com.cacagdas.noteassistantmvvm

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface NoteDao {

    @Insert
    fun insert(vararg note: Note)

    @Update
    fun update(vararg note: Note)

    @Delete
    fun delete(vararg note: Note)

    @Query("DELETE FROM note_table")
    fun deleteAllNotes()

    @Query("SELECT * FROM note_table ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<Note>>
}
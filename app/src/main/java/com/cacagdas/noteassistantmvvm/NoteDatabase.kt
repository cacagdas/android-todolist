package com.cacagdas.noteassistantmvvm

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    private var INSTANCE: NoteDatabase? = null

    fun getInstance(context: Context): NoteDatabase? {
        if (INSTANCE == null) {
            synchronized(NoteDatabase::class) {
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                    NoteDatabase::class.java, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()
            }
        }
        return INSTANCE
    }

    private val roomCallback = object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { PopulateDbAsyncTask(it).execute() }
        }
    }

    private class PopulateDbAsyncTask internal constructor(db: NoteDatabase) : AsyncTask<Void, Void, Void>() {
        private var noteDao: NoteDao = db.noteDao()


        override fun doInBackground(vararg p0: Void?): Void? {
            noteDao.insert(Note("Title 1", "Description 1"))
            noteDao.insert(Note("Title 2", "Description 2"))
            noteDao.insert(Note("Title 3", "Description 3"))
            return null
        }

    }
}
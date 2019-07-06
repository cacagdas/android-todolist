package com.cacagdas.noteassistantmvvm

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
class Note(private var title: String, private var description: String) {

    @PrimaryKey(autoGenerate = true)
    private var id: Int = 0
}
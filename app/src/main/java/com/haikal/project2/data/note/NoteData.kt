package com.haikal.project2.data.note

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_note")
data class NoteData(
    var title: String,
    var description: String,
    @PrimaryKey var key: String
) {
    constructor(): this("", "", "")
}
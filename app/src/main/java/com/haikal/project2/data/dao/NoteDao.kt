package com.haikal.project2.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.haikal.project2.data.note.NoteData

@Dao
interface NoteDao {
    @Query("SELECT * FROM my_note")
    fun getAllNote(note: List<NoteData>): LiveData<List<NoteData>>

    @Insert
    fun insert(note: NoteData)

    @Update
    fun update(note: NoteData)

    @Delete
    fun delete(note: NoteData)

    @Query("DELETE FROM my_note")
    fun deleteAll()
}
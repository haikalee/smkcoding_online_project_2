package com.haikal.project2.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.haikal.project2.data.note.NoteData

@Dao
interface NoteDao {
    @Query("SELECT * FROM my_note")
    fun getAllNote(): LiveData<List<NoteData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(note: List<NoteData>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteData)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note: NoteData)

    @Delete()
    suspend fun delete(note: NoteData)

    @Query("DELETE FROM my_note")
    suspend fun deleteAll()
}
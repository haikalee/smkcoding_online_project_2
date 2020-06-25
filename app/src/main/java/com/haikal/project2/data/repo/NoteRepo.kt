package com.haikal.project2.data.repo

import androidx.lifecycle.LiveData
import com.haikal.project2.data.dao.NoteDao
import com.haikal.project2.data.note.NoteData

class NoteRepo(private val noteDao: NoteDao) {

    val readAllData: LiveData<List<NoteData>> = noteDao.getAllNote()

    suspend fun insertAll(note: List<NoteData>) {
        noteDao.insertAll(note)
    }

    suspend fun insert(note: NoteData) {
        noteDao.insert(note)
    }

    suspend fun update(note: NoteData) {
        noteDao.update(note)
    }

    suspend fun delete(note: NoteData) {
        noteDao.delete(note)
    }

    suspend fun deleteAll() {
        noteDao.deleteAll()
    }

}
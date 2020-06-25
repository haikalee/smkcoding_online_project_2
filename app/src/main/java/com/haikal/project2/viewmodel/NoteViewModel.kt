package com.haikal.project2.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haikal.project2.data.database.NoteDatabase
import com.haikal.project2.data.note.NoteData
import com.haikal.project2.data.repo.NoteRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class NoteViewModel() : ViewModel() {

    private lateinit var repo: NoteRepo
    private lateinit var readAllData: LiveData<List<NoteData>>

    fun init(context: Context) {
        val noteDao = NoteDatabase.getDatabase(context).noteDao()
        repo = NoteRepo(noteDao)
        readAllData = repo.readAllData
    }

    fun insertAllNote(note: List<NoteData>) = viewModelScope.launch(Dispatchers.IO) {
        repo.insertAll(note)
    }

    fun insertNote(note: NoteData) = viewModelScope.launch(Dispatchers.IO) {
        repo.insert(note)
    }

    fun deleteNote(note: NoteData) = viewModelScope.launch(Dispatchers.IO) {
        repo.delete(note)
    }

    fun deleteAllNote() = viewModelScope.launch(Dispatchers.IO) {
        repo.deleteAll()
    }

    fun updateNote(note: NoteData) = viewModelScope.launch(Dispatchers.IO) {
        repo.update(note)
    }

}
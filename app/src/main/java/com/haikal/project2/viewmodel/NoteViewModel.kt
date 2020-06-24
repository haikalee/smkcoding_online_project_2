package com.haikal.project2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haikal.project2.data.note.NoteData
import com.haikal.project2.data.repo.NoteRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class NoteViewModel : ViewModel() {

    private lateinit var repo: NoteRepo
    fun insertNote(note: NoteData) = viewModelScope.launch(Dispatchers.IO) {
        repo.insert(note)
    }

    fun getNote(note: List<NoteData>): Job = viewModelScope.launch(Dispatchers.IO) {
        repo.insertAll(note)
    }

    fun deleteNote(note: NoteData) = viewModelScope.launch(Dispatchers.IO) {
        repo.delete(note)
    }

    fun deleteAllNote() = viewModelScope.launch(Dispatchers.IO) {
        repo.deleteAll()
    }

}
package com.haikal.project2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class NoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
}

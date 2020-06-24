package com.haikal.project2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.haikal.project2.data.note.NoteData
import kotlinx.android.synthetic.main.activity_note.*

class NoteActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var ref: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        auth = FirebaseAuth.getInstance()
        ref = FirebaseDatabase.getInstance().reference

        btn_add_note.setOnClickListener {
            saveData()
        }
    }

    private fun saveData() {
        val title: String = edt_note_title.text.toString()
        val description: String = edt_note_description.text.toString()
        val userID: String = auth.currentUser!!.uid

        when {
            title.isEmpty() -> edt_note_title.error = "Tidak Boleh Kosong"
            description.isEmpty() -> edt_note_description.error = "Tidak Boleh Kosong"
            else -> {
                val note = NoteData(title, description, "")
                ref.child(userID).child("Note").push().setValue(note).addOnSuccessListener {
                    Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

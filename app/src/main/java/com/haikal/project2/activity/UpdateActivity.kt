package com.haikal.project2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.haikal.project2.R
import com.haikal.project2.data.note.NoteData
import com.haikal.project2.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_update.*

class UpdateActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var ref: DatabaseReference
    private lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        auth = FirebaseAuth.getInstance()
        ref = FirebaseDatabase.getInstance().reference

        viewModel = NoteViewModel()
        viewModel.init(this)

        getData()

        btn_update_note.setOnClickListener {
            updateData()
        }
    }

    private fun updateData() {
        val titleNew = edt_update_note_title.text.toString()
        val descriptionNew = edt_update_note_description.text.toString()
        val key = intent.getStringExtra("EXTRA_KEY").toString()

        when {
            titleNew.isEmpty() -> edt_update_note_title.error = "Tidak Bolek Kosong"
            descriptionNew.isEmpty() -> edt_update_note_description.error = "Tidak Bolek Kosong"
            else -> {
                val userID = auth.currentUser?.uid.toString()
                val newNote = NoteData(titleNew, descriptionNew, "")
                ref.child(userID)
                    .child("Note")
                    .child(key)
                    .setValue(newNote)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Berhasil Update...", Toast.LENGTH_SHORT).show()
                        viewModel.updateNote(newNote)
                        finish()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Gagal Update...", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }

    private fun getData() {
        val title = intent.getStringExtra("EXTRA_TITLE")
        val description = intent.getStringExtra("EXTRA_DESCRIPTION")
        edt_update_note_title.setText(title)
        edt_update_note_description.setText(description)
    }
}

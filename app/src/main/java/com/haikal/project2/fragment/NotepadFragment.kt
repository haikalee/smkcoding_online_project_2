package com.haikal.project2.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.haikal.project2.activity.NoteActivity

import com.haikal.project2.R
import com.haikal.project2.data.note.NoteData
import com.haikal.project2.rvadapter.NotepadAdapter
import com.haikal.project2.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_notepad.*

/**
 * A simple [Fragment] subclass.
 */
class NotepadFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var ref: DatabaseReference
    private lateinit var noteData: ArrayList<NoteData>
    private lateinit var rv: RecyclerView
    private lateinit var viewModel: NoteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_notepad, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv = view.findViewById(R.id.rv_note)
        fb_add_note.setOnClickListener {
            startActivity(Intent(context, NoteActivity::class.java))
        }

        viewModel = NoteViewModel()
        viewModel.init(view.context)

        getData()

    }

    private fun getData() {
        auth = FirebaseAuth.getInstance()
        ref = FirebaseDatabase.getInstance().reference
        val userID = auth.currentUser?.uid.toString()

        ref.child(userID).child("Note").addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                noteData = java.util.ArrayList<NoteData>()
                for (snapshot in dataSnapshot.children) {
                    val note = snapshot.getValue(NoteData::class.java)
                    note?.key = snapshot.key.toString()
                    noteData.add(note!!)
                }
                viewModel.insertAllNote(noteData)
                rv.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = NotepadAdapter(context ,noteData)
                }
            }
        })
    }
}

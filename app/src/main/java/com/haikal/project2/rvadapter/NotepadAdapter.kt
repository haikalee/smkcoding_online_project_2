package com.haikal.project2.rvadapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.haikal.project2.R
import com.haikal.project2.activity.UpdateActivity
import com.haikal.project2.data.note.NoteData

class NotepadAdapter internal constructor(private val context: Context, private val note: ArrayList<NoteData>) : RecyclerView.Adapter<NotepadAdapter.NotepadViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotepadViewHolder {
        return NotepadViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))
    }

    override fun getItemCount(): Int = note.size

    override fun onBindViewHolder(holder: NotepadViewHolder, position: Int) {
        holder.bind(note[position])
        holder.itemView.setOnClickListener { view ->
            val items = arrayOf("Update", "Delete")
            val alert = AlertDialog.Builder(view.context)
            alert.setItems(items) { dialog, which ->
                when (which) {
                    0 -> {
                        val bundle = Bundle()
                        bundle.putString("EXTRA_TITLE", note[position].title)
                        bundle.putString("EXTRA_DESCRIPTION", note[position].description)
                        bundle.putString("EXTRA_KEY", note[position].key)
                        val move = Intent(context, UpdateActivity::class.java)
                        move.putExtras(bundle)
                        holder.itemView.context.startActivity(move)
                    }
                    1 -> {
                        val auth = FirebaseAuth.getInstance()
                        val ref = FirebaseDatabase.getInstance().reference
                        val userID = auth.currentUser?.uid.toString()
                        if (userID != null) {
                            ref.child(userID).child("Note")
                                .child(note[position].key)
                                .removeValue()
                                .addOnSuccessListener {
                                    Toast.makeText(context, "Berhasil Dihapus...", Toast.LENGTH_SHORT).show()
                                }
                        }
                    }
                }
            }
            alert.setTitle(note[position].title)
            alert.create()
            alert.show()
            true
        }
    }

    class NotepadViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(note: NoteData) {
            val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
            val tvDescription: TextView = itemView.findViewById(R.id.tv_description)
            tvTitle.text = note.title
            tvDescription.text = note.description
        }
    }
}
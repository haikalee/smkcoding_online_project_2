package com.haikal.project2.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.haikal.project2.NoteActivity

import com.haikal.project2.R
import kotlinx.android.synthetic.main.fragment_notepad.*

/**
 * A simple [Fragment] subclass.
 */
class NotepadFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_notepad, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_catatan.setOnClickListener {
            startActivity(Intent(context, NoteActivity::class.java))
        }

    }

}

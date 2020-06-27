package com.haikal.project2.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.*
import kotlinx.android.synthetic.main.activity_login.*
import com.firebase.ui.auth.AuthUI
import com.haikal.project2.R


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private var auth: FirebaseAuth? = null
    private val RC_SIGN_IN = 9001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        btn_login_google.setOnClickListener(this)

    }

    override fun onStart() {
        super.onStart()
        if (auth!!.currentUser != null) {
            val move = Intent(this, MainActivity::class.java)
            startActivity(move)
        } else {
            Toast.makeText(this, "Belum Login", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_login_google -> googleLogin()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Login Berhasil...", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else Toast.makeText(this, "Login Gagal...", Toast.LENGTH_SHORT).show()
        }
    }

    private fun googleLogin() {
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(listOf(
                    AuthUI.IdpConfig.GoogleBuilder().build()))
                .setIsSmartLockEnabled(true)
                .build(),
            RC_SIGN_IN
        )
    }
}

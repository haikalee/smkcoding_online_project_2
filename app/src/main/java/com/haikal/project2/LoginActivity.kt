package com.haikal.project2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.facebook.*
import com.google.firebase.auth.*
import kotlinx.android.synthetic.main.activity_login.*
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginResult
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FacebookAuthProvider


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    var auth: FirebaseAuth? = null
    private val RC_SIGN_IN = 9001
    private var callbackManager: CallbackManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)

        auth = FirebaseAuth.getInstance()

        btn_login_google.setOnClickListener(this)
        btn_login_facebook.setOnClickListener(this)

        if (auth!!.currentUser == null) {
            Toast.makeText(this, "belum Login...", Toast.LENGTH_SHORT).show()
        } else {
            val move = Intent(this, MainActivity::class.java)
            startActivity(move)
            finish()
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_login_google -> googleLogin()
            R.id.btn_login_facebook -> facebookLogin()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Login Berhasil...", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else Toast.makeText(this, "Login Gagal...", Toast.LENGTH_SHORT).show()
        }
        callbackManager!!.onActivityResult(requestCode, resultCode, data)
    }

    private fun googleLogin() {
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(listOf(AuthUI.IdpConfig.GoogleBuilder().build()))
                .setIsSmartLockEnabled(true)
                .build(),
            RC_SIGN_IN
        )
    }

    private fun facebookLogin() {
        callbackManager = CallbackManager.Factory.create()
        btn_login_facebook.setReadPermissions("email", "public_profile")
        btn_login_facebook.registerCallback(callbackManager, object: FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                val move = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(move)
                finish()
            }

            override fun onCancel() {
                Toast.makeText(this@LoginActivity, "Batal Login...", Toast.LENGTH_SHORT).show()
            }

            override fun onError(error: FacebookException?) {
                Toast.makeText(this@LoginActivity, "Gagal Login...", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

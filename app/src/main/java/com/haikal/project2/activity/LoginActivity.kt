package com.haikal.project2.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.facebook.*
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import com.haikal.project2.R


class LoginActivity : AppCompatActivity(), View.OnClickListener  {

    private lateinit var auth: FirebaseAuth
    private val RC_SIGN_IN = 9001
    private lateinit var callbackManager: CallbackManager
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth
        callbackManager = CallbackManager.Factory.create()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        btn_login_google.setOnClickListener(this)
        btn_login_facebook.setOnClickListener(this)
        loginFacebook()

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_login_facebook -> loginFacebook()
            R.id.btn_login_google -> loginGoogle()
        }
    }

    public override fun onStart() {
        super.onStart()
        if (auth.currentUser != null) {
            val currentUser = auth.currentUser
            updateUI(currentUser)
        }
    }

    private fun loginGoogle() {
        val signIn = googleSignInClient.signInIntent
        startActivityForResult(signIn, RC_SIGN_IN)
    }

    private fun loginFacebook() {
        btn_login_facebook.setReadPermissions("email", "public_profile")
        btn_login_facebook.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                handleFacebookAccessToken(loginResult.accessToken)
                Toast.makeText(baseContext, "Tunggu Sebentar", Toast.LENGTH_SHORT).show()
            }

            override fun onCancel() {
                Log.d("FacebookLogin", "cancel")
            }

            override fun onError(error: FacebookException) {
                Log.d("FacebookLogin", error.message)
            }
        })
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                    Log.d("FacebookLogin", "task.isSuccessful")
                    Toast.makeText(baseContext, task.result!!.user!!.displayName, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(baseContext, "Gagal Auth", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthGoogle(account.idToken!!)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun firebaseAuthGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) {task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                    Log.d("GoogleLogin", "task.isSuccessful")
                } else {
                    Toast.makeText(baseContext, "Gagal Auth", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            Toast.makeText(this, "Belum Login", Toast.LENGTH_SHORT).show()
        }
    }
}

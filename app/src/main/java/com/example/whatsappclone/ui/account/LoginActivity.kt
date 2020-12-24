package com.example.whatsappclone.ui.account

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.whatsappclone.R
import com.example.whatsappclone.databinding.ActivityLoginBinding
import com.example.whatsappclone.framework.BaseActivity
import com.example.whatsappclone.ui.home.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class LoginActivity : BaseActivity() {

    lateinit var bind: ActivityLoginBinding
    lateinit var auth: FirebaseAuth
    lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_login)

        // init toolbar
        initToolbar()

        // init firebase auth
        initAuth()

        // init listener
        intiListener()
    }

    private fun intiListener() {
        bind.btnRegister.setOnClickListener {

            val email = bind.email.text.toString()
            val password = bind.password.text.toString()

            if (email.isEmpty()) {
                bind.email.error = "Email is required"
                bind.email.requestFocus()
            } else if (password.isEmpty()) {
                bind.password.error = "Password is required"
                bind.password.requestFocus()
            } else if(password.length < 6) {
                bind.password.error = "Password is too short"
                bind.password.requestFocus()
            } else {
                login(email, password)
                progress(true, "Login...")
            }

        }
    }

    private fun initAuth() {
        auth = FirebaseAuth.getInstance()
    }

    private fun initToolbar() {
        val toolbar = bind.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Login"
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if(task.isSuccessful) {
                progress(false)
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            } else {
                progress(false)
                Toast.makeText(this, "Authentication failed!!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
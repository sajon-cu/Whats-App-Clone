package com.example.whatsappclone.ui.account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.whatsappclone.R
import com.example.whatsappclone.databinding.ActivityRegisterBinding
import com.example.whatsappclone.framework.BaseActivity
import com.example.whatsappclone.ui.StartActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : BaseActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var reference: DatabaseReference
    lateinit var bind: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_register)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_register)

        // init toolbar
        initToolbar()

        // init firebase auth
        initAuth()

        // init listener
        intiListener()
    }

    private fun initToolbar() {
        val toolbar = bind.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Register"
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun initAuth() {
        auth = FirebaseAuth.getInstance()
    }

    private fun intiListener() {

        bind.btnRegister.setOnClickListener {

            val username = bind.username.text.toString()
            val email = bind.email.text.toString()
            val password = bind.password.text.toString()

            if(username.isEmpty()) {
                bind.username.error = "Username is required"
                bind.username.requestFocus()
            } else if (email.isEmpty()) {
                bind.email.error = "Email is required"
                bind.email.requestFocus()
            } else if (password.isEmpty()) {
                bind.password.error = "Password is required"
                bind.password.requestFocus()
            } else if(password.length < 6) {
                bind.password.error = "Password is too short"
                bind.password.requestFocus()
            } else {
                register(username, email, password)
                progress(true, "Registering...")
            }

        }

    }

    /**
     * create new account
     * @param username
     * @param email
     * @param password
     */
    private fun register(username: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    progress(false)
                    val firebaseUser: FirebaseUser? = auth.currentUser
                    val userId = firebaseUser?.uid

                    reference = userId?.let {
                        FirebaseDatabase.getInstance().getReference("Users").child(
                            it
                        )
                    }!!

                    val hashMap = mutableMapOf<String, String>(
                        "id" to userId,
                        "username" to username,
                        "imageUrl" to "default"
                    )

                    reference.setValue(hashMap).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val intent = Intent(this, StartActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "You can not register with this email or password", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    progress(false)
                }
            }

    }
}
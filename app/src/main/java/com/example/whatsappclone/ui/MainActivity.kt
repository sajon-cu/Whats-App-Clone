package com.example.whatsappclone.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.whatsappclone.R
import com.example.whatsappclone.databinding.ActivityMainBinding
import com.example.whatsappclone.model.user.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import java.sql.DatabaseMetaData

class MainActivity : AppCompatActivity() {
    lateinit var bind: ActivityMainBinding
    lateinit var firebaseUser: FirebaseUser
    lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // init toolbar
        initToolbar()

        // init firebase auth
        initAuth()

        // load user data
        loadUser()
    }

    private fun initToolbar() {
        val toolbar = bind.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = ""
        }
    }

    private fun initAuth() {
        firebaseUser = FirebaseAuth.getInstance().currentUser!!
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.uid)
    }

    private fun loadUser() {
        reference.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java)
                bind.tvUsername.text = user?.username

                if(user?.imageUrl.equals("default")) {
                    bind.ivProfile.setImageResource(R.drawable.men_demo)
                } else {
                    Glide.with(bind.ivProfile).load(user?.imageUrl).into(bind.ivProfile)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    private fun logout() {
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this, StartActivity::class.java))
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.logout -> {
                logout()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
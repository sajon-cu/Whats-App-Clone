package com.example.whatsappclone.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.whatsappclone.R
import com.example.whatsappclone.databinding.ActivityMainBinding
import com.example.whatsappclone.databinding.ActivityStartBinding
import com.example.whatsappclone.ui.account.LoginActivity
import com.example.whatsappclone.ui.account.RegisterActivity

class StartActivity : AppCompatActivity() {
    lateinit var bind: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_start)

        // init listener
        initListener()
    }

    private fun initListener() {
        bind.btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        bind.btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
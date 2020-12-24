package com.example.whatsappclone.model.user

import com.example.whatsappclone.utils.Constants

data class User(
    val id: String = Constants.Default.STRING,
    val username: String = Constants.Default.STRING,
    val imageUrl: String = Constants.Default.STRING
)
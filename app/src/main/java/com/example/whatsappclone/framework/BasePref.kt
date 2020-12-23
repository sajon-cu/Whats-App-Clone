package com.example.whatsappclone.framework

import android.content.Context
import com.example.whatsappclone.utils.Constants
import com.github.pwittchen.prefser.library.rx2.Prefser

abstract class BasePref(val context: Context) {
    private val publicPref: Prefser
    private val privatePref: Prefser

    init {
        publicPref = Prefser(context)
        val prefName = getPrivateName(context)
        val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        privatePref = Prefser(pref)
    }

    private fun getPrivateName(context: Context): String? {
        return Constants.Keys.PrefKeys.DEFAULT
    }
}
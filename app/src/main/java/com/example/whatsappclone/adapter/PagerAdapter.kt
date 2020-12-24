package com.example.whatsappclone.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.whatsappclone.R
import com.example.whatsappclone.ui.home.fragments.ChatsFragment
import com.example.whatsappclone.ui.home.fragments.UsersFragment


class PagerAdapter(activity: AppCompatActivity) : BasePagerAdapter(activity) {
    init {
        addItem(ChatsFragment(), R.string.chats)
        addItem(UsersFragment(), R.string.chats)
    }
}
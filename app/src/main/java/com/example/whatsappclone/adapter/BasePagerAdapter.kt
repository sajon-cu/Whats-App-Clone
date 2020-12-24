package com.example.whatsappclone.adapter

import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

abstract class BasePagerAdapter(val activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    protected val fragment: ArrayList<Fragment>
    protected val titles: MutableMap<Fragment, String>
    protected val pageIds : ArrayList<Long>

    init {
        fragment = arrayListOf()
        titles = mutableMapOf()
        pageIds = arrayListOf()
    }

    open fun addItem(item: Fragment, @StringRes titleRes: Int = 0, notify: Boolean = false) {
        if (titleRes != 0) {
            titles.put(item, activity.getString(titleRes))
        }
        val position = getPosition(item)
        if (position == -1) {
            fragment.add(item)
            pageIds.add(item.hashCode().toLong())
            if (notify)
                notifyItemInserted(itemCount - 1)
        } else {
            fragment[position] = item
            pageIds.add(item.hashCode().toLong())
            if (notify)
                notifyItemChanged(position)
        }
    }

    override fun createFragment(position: Int): Fragment = fragment.get(position)

    override fun containsItem(itemId: Long): Boolean {
        return pageIds.contains(itemId)
    }

    override fun getItemCount(): Int {
        return fragment.size
    }

    override fun getItemId(position: Int): Long {
        return fragment.get(position).hashCode().toLong()
    }

    fun getPosition(item: Fragment?): Int = fragment.indexOf(item)
}
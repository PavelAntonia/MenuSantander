package com.example.menudrawersantander.editMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fragmentManager: FragmentManager, titles: ArrayList<String>) : FragmentPagerAdapter(fragmentManager) {

    val titleNames= titles

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0-> FragmentYourFeatures()
            else -> FragmentAllFeatures()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0->titleNames[0]
            else->titleNames[1]
        }
    }
}
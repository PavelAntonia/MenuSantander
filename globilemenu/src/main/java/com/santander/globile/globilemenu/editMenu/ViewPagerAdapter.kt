package com.santander.globile.globilemenu.editMenu

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.santander.globile.globilemenu.R

private const val POS_FRAGMENT_YOUR_FEATURES = 0
private const val POS_FRAGMENT_ALL_FEATURES = 1

class ViewPagerAdapter(fragmentManager: FragmentManager,private val context: Context) : FragmentPagerAdapter(fragmentManager) {


    override fun getCount(): Int {
        //There are only 2 fragments to display YOUR_FEATURES, ALL_FEATURES
        return 2
    }

    override fun getItem(position: Int): Fragment? {

        return when(position){
            POS_FRAGMENT_YOUR_FEATURES -> FragmentYourFeatures()
            POS_FRAGMENT_ALL_FEATURES -> FragmentAllFeatures()
            else -> null
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {

        return when(position){
            POS_FRAGMENT_YOUR_FEATURES-> context.getString(R.string.your_features)
            POS_FRAGMENT_ALL_FEATURES-> context.getString(R.string.all_features)
            else -> null
        }
    }
}
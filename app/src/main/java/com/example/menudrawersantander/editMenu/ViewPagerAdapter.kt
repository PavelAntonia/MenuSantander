package com.example.menudrawersantander.editMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fragmentManager: FragmentManager, titles: ArrayList<String>) : FragmentPagerAdapter(fragmentManager) {


    val titleNames = titles

    override fun getCount(): Int {
        return 2 //Por qué 2? y no 3? A veces hace falta, pero quien lo lee no sabe el motivo, comentarios :)
    }

    override fun getItem(position: Int): Fragment {

        return when(position){
            0-> FragmentYourFeatures() //por qué solo con el 0?
            else -> FragmentAllFeatures()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {

        return when(position){
            0->titleNames[0]
            else->titleNames[1] //Y si no hay más de 0? Crash de la app, hay que asegurarse. No des por supuesto.
        }
    }
}
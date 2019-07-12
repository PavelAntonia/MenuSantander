package com.example.menudrawersantander.editMenu

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.menudrawersantander.R
import com.example.menudrawersantander.menu.ItemMenu
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_edit_menu.*

class EditMenuActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        setContentView(R.layout.activity_edit_menu)

        val adapter = ViewPagerAdapter(supportFragmentManager, this)
        pager.adapter = adapter

        tabs.setupWithViewPager(pager)
        tabs.setTabTextColors(getColor(R.color.medium_grey), getColor(R.color.dark_grey))

    }

    fun cancelButton(view: View) {
        finish()
    }

    fun doneButton(view: View) {

        val sharedPref: SharedPreferences = getSharedPreferences("features", 0) //Private mode


        val editor = sharedPref.edit()
        editor.putInt("otherFeaturesPosition", removeSeparator(DataAdapterYourFeatures.listYourFeatures))
        val listYour = Gson().toJson(DataAdapterYourFeatures.listYourFeatures)
        val listAll = Gson().toJson(DataAdapterAllFeatures.listAllFeatures)
        editor.putString("yourFeatures", listYour)
        editor.putString("allFeatures", listAll)
        editor.apply()

        finish()

    }

    //Remove separator from list and return the position where it was
    private fun removeSeparator(list: ArrayList<ItemMenu>): Int {

        var i = 0
        while (i < list.size) {

            if (list[i].type == 2) {
                list.removeAt(i)
                return i
            } else i++
        }
        return 4//TODO default position for separator

    }

}
package com.example.menudrawersantander.editMenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.menudrawersantander.R
import com.example.menudrawersantander.menu.AccessSharedPref
import com.example.menudrawersantander.menu.ItemMenu
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

        //Update internal position in ItemMenu
        var i = 0
        DataAdapterYourFeatures.listYourFeatures.forEach {
            it.position = i++
        }

        val accessSharedPref = AccessSharedPref(this)
        accessSharedPref.writeAllFeatures(DataAdapterAllFeatures.listAllFeatures)
        accessSharedPref.writeYourFeatures(DataAdapterYourFeatures.listYourFeatures)
        accessSharedPref.writePosOtherFeatures(ItemMenu.removeSeparator(DataAdapterYourFeatures.listYourFeatures))

        finish()
    }
}
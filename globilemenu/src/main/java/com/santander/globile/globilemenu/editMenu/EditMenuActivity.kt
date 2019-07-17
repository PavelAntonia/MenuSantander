package com.santander.globile.globilemenu.editMenu

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.santander.globile.globilemenu.R
import com.santander.globile.globilemenu.menu.AccessSharedPref
import com.santander.globile.globilemenu.menu.ItemMenu
import kotlinx.android.synthetic.main.activity_edit_menu.*

class EditMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_edit_menu)
        val adapter = ViewPagerAdapter(supportFragmentManager, this)
        pager.adapter = adapter

        tabs.setupWithViewPager(pager)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tabs.setTabTextColors(resources.getColor(R.color.medium_grey, null), resources.getColor(R.color.dark_grey, null))
        } else {
            tabs.setTabTextColors(resources.getColor(R.color.medium_grey), resources.getColor(R.color.dark_grey))
        }

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
        accessSharedPref.writePosOtherFeatures(ItemMenu.removeSeparator(DataAdapterYourFeatures.listYourFeatures))
        accessSharedPref.writeAllFeatures(DataAdapterAllFeatures.listAllFeatures)
        accessSharedPref.writeYourFeatures(DataAdapterYourFeatures.listYourFeatures)


        finish()
    }
}

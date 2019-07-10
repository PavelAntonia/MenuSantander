package com.example.menudrawersantander.editMenu

import android.content.SharedPreferences
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.menudrawersantander.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_edit_menu.*

class EditMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        setContentView(R.layout.activity_edit_menu)

        val titles = arrayListOf<String>()
        titles.add(getString(R.string.your_features))
        titles.add(getString(R.string.all_features))

        val adapter = ViewPagerAdapter(supportFragmentManager, titles)
        pager.adapter = adapter

        tabs.setupWithViewPager(pager)
    }

    //back to the previous activity
    fun cancelButton(view: View){
        finish()
    }

    //to save the drag and drop changes (done button)
    fun doneButton(view: View){

        val sharedPref: SharedPreferences = getSharedPreferences("features", 0) //Private mode
        val data = Gson().toJson(FragmentYourFeatures.itemList)
        val editor = sharedPref.edit()
        editor.putString("yourFeatures", data)
        editor.apply()
        finish()
    }
}

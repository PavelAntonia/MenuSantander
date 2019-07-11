package com.example.menudrawersantander.editMenu

import android.content.SharedPreferences
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.menudrawersantander.R
import com.example.menudrawersantander.editMenu.FragmentYourFeatures.Companion.itemList
import com.example.menudrawersantander.menu.ItemMenu
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_edit_menu.*

class EditMenuActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        setContentView(R.layout.activity_edit_menu)

        //Intentad mover esto a un repositirio, que se encargue de obtener estos valores
        //Principio de compatimentación, cada cosa hace solo lo que le corresponde
        val titles = arrayListOf<String>()
        titles.add(getString(R.string.your_features))
        titles.add(getString(R.string.all_features))

        val adapter = ViewPagerAdapter(supportFragmentManager,titles)
        pager.adapter = adapter

        tabs.setupWithViewPager(pager)

    }

    fun cancelButton(view: View){
        finish()
    }

    fun doneButton(view: View){

        //Igual que arriba, el repositorio es quien gestiona esto, para la activity debería ser, leer o guardar.
        val sharedPref: SharedPreferences = getSharedPreferences("features", 0) //Private mode


        val editor = sharedPref.edit()
        editor.putInt("otherFeaturesPosition",removeSeparator(itemList))
        val data = Gson().toJson(itemList)
        editor.putString("yourFeatures", data)
        editor.apply()

        finish()

    }

    //Remove separator from list and return the position where it was
    private fun removeSeparator(list: ArrayList<ItemMenu>):Int {

        var i = 0
        while(i < list.size){

            if(list[i].type == 2) {
                list.removeAt(i)
                return i
            }
            else i++
        }
        return 4//TODO default position for separator

    }

}
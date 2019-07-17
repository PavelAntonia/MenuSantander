package com.example.menudrawersantander.menu

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.menudrawersantander.R
import com.example.menudrawersantander.editMenu.EditMenuActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.navigation_menu.*

class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        val toggle =
            ActionBarDrawerToggle(
                this, drawerLayout,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
            )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ///////////////////// INSERT DATA IN SHARED PREFERENCES (TEMPORAL) //////////////////

        val shared: SharedPreferences = getSharedPreferences("features", 0) //Private mode

        val yourFeatures = ArrayList<ItemMenu>()

        yourFeatures.add(
            ItemMenu(

                "Mobile payment",
                R.drawable.ic_func_052,
                2,

                0
            )
        )
        yourFeatures.add(
            ItemMenu(
                "Payments and transfers",
                R.drawable.ic_ban_070,
                3,
                0
            )
        )
        yourFeatures.add(
            ItemMenu(
                "Cards",
                R.drawable.ic_ban_099,
                1,
                1
            )
        )
        yourFeatures.add(
            ItemMenu(
                "Global position",
                R.drawable.ic_serv_023,
                1,
                0
            )
        )
        yourFeatures.add(
            ItemMenu(

                "Products",
                R.drawable.ic_ban_089,
                4,
                1
            )
        )

        val allFeatures = ArrayList<ItemMenu>()
        allFeatures.add(
            ItemMenu(
               "Accounts", R.drawable.ic_ban_001_b, 1,1
            )
        )
        allFeatures.add(
            ItemMenu(
                "Utilities", R.drawable.ic_serv_098, 2, 1
            )
        )
        allFeatures.add(
            ItemMenu(
               "Funds", R.drawable.ic_ban_ban_034, 3, 1
            )
        )
        allFeatures.add(
            ItemMenu(
             "Loans", R.drawable.ic_ban_025, 4, 1
            )
        )

        val data = Gson().toJson(yourFeatures)
        val data2 = Gson().toJson(allFeatures)
        val editor = shared.edit()
        editor.putString("yourFeatures", data)
        editor.putString("allFeatures", data2)
        editor.putInt("otherFeaturesPosition",2)
        editor.apply()

       /////////////////////////////////////////////////////////////////////////
    }

    override fun onStart() {
        super.onStart()
        setUpMenu()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (item.itemId == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                drawerLayout.closeDrawer(GravityCompat.END)
            } else {
                drawerLayout.openDrawer(GravityCompat.END)
            }
        }

        return super.onOptionsItemSelected(item)

    }

    private fun setUpMenu() {

        val accessSharedPref = AccessSharedPref(this)
        val itemList = accessSharedPref.readYourFeatures()
        val positionOtherFeatures = accessSharedPref.readPosOtherFeatures()

        selected_items.layoutManager = LinearLayoutManager(this)
        selected_items.adapter = DataAdapter(itemList,positionOtherFeatures)
    }

    fun intentEditMenu(view: View){
        val intent = Intent(view.context,EditMenuActivity::class.java)
        view.context.startActivity(intent)
    }
}
package com.example.menudrawersantander.menu

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.menudrawersantander.R
import com.example.menudrawersantander.editMenu.EditMenuActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.navigation_menu.*

class MainActivity : AppCompatActivity(){

    lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle =
            ActionBarDrawerToggle(this, drawerLayout,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
            )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ///////////////////// INSERT DATA IN SHARED PREFERENCES (TEMPORAL) //////////////////

//        val shared: SharedPreferences = getSharedPreferences("features", 0) //Private mode
//
//        val sharedList = ArrayList<ItemMenu>()
//        sharedList.add(
//            ItemMenu(
//                1,
//                "Mobile payment",
//                R.drawable.ic_func_052,
//                2,
//                false,
//                0
//            )
//        )
//        sharedList.add(
//            ItemMenu(
//                2,
//                "Payments and transfers",
//                R.drawable.ic_ban_070,
//                3,
//                false,
//                0
//            )
//        )
//        sharedList.add(ItemMenu(3, "Cards", R.drawable.ic_ban_099, 1, false, 1))
//        sharedList.add(
//            ItemMenu(
//                4,
//                "Global position",
//                R.drawable.ic_serv_023,
//                1,
//                true,
//                1
//            )
//        )
//        sharedList.add(ItemMenu(5, "Products", R.drawable.ic_ban_089, 4, false, 2))
//
//
//        val data = Gson().toJson(sharedList)
//        val editor = shared.edit()
//        editor.putString("yourFeatures", data)
//        editor.apply()

    }

    override fun onResume() {
        super.onResume()
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
        val groupListType = object : TypeToken<ArrayList<ItemMenu>>() {}.type

        val sharedPref: SharedPreferences = getSharedPreferences("features", 0) //Private mode
        val itemList = Gson().fromJson<ArrayList<ItemMenu>>(sharedPref.getString("yourFeatures", ""), groupListType)

        itemList.sort()

        selected_items.layoutManager = LinearLayoutManager(this)
        var mAdapter = DataAdapter(itemList)
        selected_items.adapter = mAdapter
    }

    fun intentEditMenu(view: View){

        val intent = Intent(view.context, EditMenuActivity::class.java)
        view.context.startActivity(intent)
    }
}
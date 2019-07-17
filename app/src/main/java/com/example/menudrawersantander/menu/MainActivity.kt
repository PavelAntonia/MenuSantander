package com.example.menudrawersantander.menu

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.menudrawersantander.R
import com.google.gson.Gson
import com.santander.globile.globilemenu.menu.GlobileBankNavigationView
import com.santander.globile.globilemenu.menu.ItemMenu
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), GlobileBankNavigationView.Listener {

    lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ///////////////////// INSERT DATA IN SHARED PREFERENCES (TEMPORAL) //////////////////

        val shared: SharedPreferences = getSharedPreferences("features", Context.MODE_PRIVATE) //Private mode

        val yourFeatures = ArrayList<ItemMenu>()

        yourFeatures.add(
            ItemMenu(

                "Mobile payment",
                com.santander.globile.globilemenu.R.drawable.ic_func_052,
                2,

                0
            )
        )
        yourFeatures.add(
            ItemMenu(
                "Payments and transfers",
                com.santander.globile.globilemenu.R.drawable.ic_ban_070,
                3,
                0
            )
        )
        yourFeatures.add(
            ItemMenu(
                "Cards",
                com.santander.globile.globilemenu.R.drawable.ic_ban_099,
                1,
                1
            )
        )
        yourFeatures.add(
            ItemMenu(
                "Global position",
                com.santander.globile.globilemenu.R.drawable.ic_serv_023,
                1,
                0
            )
        )
        yourFeatures.add(
            ItemMenu(

                "Products",
                com.santander.globile.globilemenu.R.drawable.ic_ban_089,
                4,
                1
            )
        )

        val allFeatures = ArrayList<ItemMenu>()
        allFeatures.add(
            ItemMenu(
                "Accounts", com.santander.globile.globilemenu.R.drawable.ic_ban_001_b, 1,1
            )
        )
        allFeatures.add(
            ItemMenu(
                "Utilities", com.santander.globile.globilemenu.R.drawable.ic_serv_098, 2, 1
            )
        )
        allFeatures.add(
            ItemMenu(
                "Funds", com.santander.globile.globilemenu.R.drawable.ic_ban_ban_034, 3, 1
            )
        )
        allFeatures.add(
            ItemMenu(
                "Loans", com.santander.globile.globilemenu.R.drawable.ic_ban_025, 4, 1
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

    override fun onLogOutClicked() {

    }

    override fun onResume() {
        super.onResume()
        hola.setUpMenu()
        hola.setListener(this)
    }
}

package com.santander.globile.globilemenu.menu

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.View
import android.view.ViewTreeObserver
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.santander.globile.globilemenu.R
import com.santander.globile.globilemenu.editMenu.EditMenuActivity
import kotlinx.android.synthetic.main.globilebank_navigation_view.view.*

class GlobileBankNavigationView(context: Context, attrs: AttributeSet) : NavigationView(context, attrs) {

    private var listener: Listener? = null

    init {
        View.inflate(context, R.layout.globilebank_navigation_view, this)

        val title = title
        val edit = edit_menu
        val logOut = logOut

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.GlobileBankNavigationView)
        title.text = attributes.getString(R.styleable.GlobileBankNavigationView_title)

        attributes.recycle()

        edit.setOnClickListener {
            val intent = Intent(context, EditMenuActivity::class.java)
            startActivity(context, intent, null)
        }

        logOut.setOnClickListener {
            listener?.onLogOutClicked()
        }
    }

    fun setUpMenu() {

        val accessSharedPref = AccessSharedPref(context)
        val itemList = accessSharedPref.readYourFeatures()
        val positionOtherFeatures = accessSharedPref.readPosOtherFeatures()

        selected_items.layoutManager = LinearLayoutManager(context)
        selected_items.adapter = DataAdapter(itemList,positionOtherFeatures)
    }

    fun setListener(listener: Listener) {
        this.listener = listener
    }

    interface Listener {
        fun onLogOutClicked()
    }
}

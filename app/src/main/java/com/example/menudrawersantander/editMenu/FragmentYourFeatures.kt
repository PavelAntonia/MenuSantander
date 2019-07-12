package com.example.menudrawersantander.editMenu


import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.menudrawersantander.R
import com.example.menudrawersantander.menu.DataAdapter
import com.example.menudrawersantander.menu.ItemMenu
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_edit_menu.*
import kotlinx.android.synthetic.main.fragment_edit.*
import kotlinx.android.synthetic.main.fragment_edit.view.*
import kotlinx.android.synthetic.main.fragment_edit.view.recycler_features
import java.util.*
import kotlin.collections.ArrayList

class FragmentYourFeatures : Fragment(), OnStartDragListener {


    lateinit var helper: ItemTouchHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_edit, container, false)

        val groupListType = object : TypeToken<ArrayList<ItemMenu>>() {}.type

        val sharedPref: SharedPreferences = activity!!.getSharedPreferences("features", 0) //Private mode
        val listYourFeatures =
            Gson().fromJson<ArrayList<ItemMenu>>(sharedPref.getString("yourFeatures", ""), groupListType)
        val positionOtherFeatures = sharedPref.getInt("otherFeaturesPosition", 4)

        listYourFeatures.sort()

        view.recycler_features.layoutManager = LinearLayoutManager(view.context)
        val mAdapter = DataAdapterYourFeatures(listYourFeatures, positionOtherFeatures, this)
        view.recycler_features.adapter = mAdapter

        helper =
            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0) {


                override fun isLongPressDragEnabled(): Boolean {
                    return false
                }

                override fun onMove(
                    recyclerView: RecyclerView,
                    dragged: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {

                    val posDragged = dragged.adapterPosition
                    val posTarget = target.adapterPosition


                    Collections.swap(DataAdapterYourFeatures.listYourFeatures, posDragged, posTarget)
                    mAdapter.notifyItemMoved(posDragged, posTarget)


                    var i = 0
                    listYourFeatures.forEach {
                        it.position = i++
                    }

                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}
            })


        helper.attachToRecyclerView(view.recycler_features)

        return view
    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        helper.startDrag(viewHolder)
    }
}
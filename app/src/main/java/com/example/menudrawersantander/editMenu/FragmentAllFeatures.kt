package com.example.menudrawersantander.editMenu


import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.menudrawersantander.R
import com.example.menudrawersantander.menu.ItemMenu
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_edit.view.*

class FragmentAllFeatures : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_edit, container, false)

        val groupListType = object : TypeToken<ArrayList<ItemMenu>>() {}.type

        val sharedPref: SharedPreferences = activity!!.getSharedPreferences("features", 0) //Private mode
        val listAllFeatures = Gson().fromJson<ArrayList<ItemMenu>>(sharedPref.getString("allFeatures", ""), groupListType)

        listAllFeatures.sort()

        view.recycler_features.layoutManager = LinearLayoutManager(view.context)
        var mAdapter = DataAdapterAllFeatures(listAllFeatures)
        view.recycler_features.adapter = mAdapter

        return view
    }
}
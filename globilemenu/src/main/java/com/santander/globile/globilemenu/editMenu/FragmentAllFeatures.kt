package com.santander.globile.globilemenu.editMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.santander.globile.globilemenu.R
import com.santander.globile.globilemenu.menu.AccessSharedPref
import kotlinx.android.synthetic.main.fragment_edit.view.*

class FragmentAllFeatures : Fragment() {

    companion object {
        lateinit var adapterAllFeatures: DataAdapterAllFeatures
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_edit, container, false)

        val accessSharedPref = AccessSharedPref(view.context)
        val listAllFeatures = accessSharedPref.readAllFeatures()

        listAllFeatures.sort()

        view.recycler_features.layoutManager = LinearLayoutManager(view.context)
        adapterAllFeatures = DataAdapterAllFeatures(listAllFeatures)
        view.recycler_features.adapter = adapterAllFeatures

        return view
    }
}
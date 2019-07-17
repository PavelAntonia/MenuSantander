package com.santander.globile.globilemenu.editMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_IDLE
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.santander.globile.globilemenu.R
import com.santander.globile.globilemenu.menu.AccessSharedPref
import com.santander.globile.globilemenu.menu.ItemMenu
import kotlinx.android.synthetic.main.fragment_edit.view.recycler_features
import java.util.*

class FragmentYourFeatures : Fragment(),OnStartDragListener  {


    companion object{
        lateinit var adapterYourFeatures: DataAdapterYourFeatures
    }

    lateinit var helper: ItemTouchHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_edit, container, false)


        val accessSharedPref = AccessSharedPref(view.context)
        val listYourFeatures = accessSharedPref.readYourFeatures()
        val positionOtherFeatures = accessSharedPref.readPosOtherFeatures()

        view.recycler_features.layoutManager = LinearLayoutManager(view.context)
        adapterYourFeatures = DataAdapterYourFeatures(listYourFeatures, positionOtherFeatures,this)
        view.recycler_features.adapter = adapterYourFeatures

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
                    adapterYourFeatures.notifyItemMoved(posDragged, posTarget)

                    return false
                }

                override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
                    super.onSelectedChanged(viewHolder, actionState)

                   if(actionState==ACTION_STATE_IDLE) {
                        val posOtherFeatures = ItemMenu.getPositionOtherFeatures(DataAdapterYourFeatures.listYourFeatures)
                        FragmentYourFeatures.adapterYourFeatures.notifyItemChanged(posOtherFeatures)
                    }
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
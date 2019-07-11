package com.example.menudrawersantander.editMenu

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent.ACTION_DOWN
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.MotionEventCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.menudrawersantander.R
import com.example.menudrawersantander.menu.ItemMenu
import java.util.ArrayList

private const val ITEM_VIEWHOLDER = 0
private const val OTHER_FEATURES_VIEWHOLDER = 1


internal class DataAdapterYourFeatures(private val names: ArrayList<ItemMenu>,
                                       private val positionOtherFeatures: Int,
                                       private val dragStartListener: OnStartDragListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    init {
        names.add(positionOtherFeatures, ItemMenu(1, "Separador", 232, positionOtherFeatures, false, 2))
    }

    override fun getItemViewType(position: Int): Int {
        return if (names[position].type == 2) OTHER_FEATURES_VIEWHOLDER else ITEM_VIEWHOLDER
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val layout = when (viewType) {
            ITEM_VIEWHOLDER -> R.layout.item_your_features
            else -> R.layout.item_other_features
        }

        val view = LayoutInflater.from(viewGroup.context).inflate(layout, viewGroup, false)
        return if (viewType == ITEM_VIEWHOLDER) ViewHolderItem(view) else ViewHolderOtherFeatures(view)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int) {

        if (getItemViewType(i) == 0) {

            (viewHolder as ViewHolderItem)
            viewHolder.sliderItem.setOnTouchListener(View.OnTouchListener { v, event ->
                if (MotionEventCompat.getActionMasked(event) == ACTION_DOWN) {
                    dragStartListener.onStartDrag(viewHolder)
                }
                false
            })

            if (names[i].type != 0) {
                viewHolder.deleteItem.setImageResource(R.drawable.ic_func_031)
            }
            viewHolder.iconItem.setImageResource(names[i].itemIcon)
            viewHolder.nameItem.text = names[i].itemName
            viewHolder.sliderItem.setImageResource(R.drawable.ic_sys_15)

        } else {

            if (i != names.size - 1)
                (viewHolder as ViewHolderOtherFeatures).infoTextOtherFeatures.visibility = View.GONE
        }
    }


    override fun getItemCount(): Int {
        return names.size
    }

    internal inner class ViewHolderItem(view: View) : RecyclerView.ViewHolder(view) {
        var deleteItem: ImageView = view.findViewById(R.id.delete_item)
        var iconItem: ImageView = view.findViewById(R.id.icon_item)
        var nameItem: TextView = view.findViewById(R.id.name_item)
        var sliderItem: ImageView = view.findViewById(R.id.slider_item)
    }

    internal inner class ViewHolderOtherFeatures(view: View) : RecyclerView.ViewHolder(view) {
        val infoTextOtherFeatures: TextView = view.findViewById(R.id.info_text_other_features)
    }
}
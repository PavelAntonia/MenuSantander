package com.example.menudrawersantander.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.menudrawersantander.R
import java.util.ArrayList

private const val ITEM_VIEWHOLDER = 0
private const val OTHER_FEATURES_VIEWHOLDER = 1

internal class DataAdapter(private val items: ArrayList<ItemMenu>, private val positionOtherFeatures: Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun getItemViewType(position: Int): Int {
        return if (position == positionOtherFeatures) OTHER_FEATURES_VIEWHOLDER else ITEM_VIEWHOLDER
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val layout = when (viewType) {
            ITEM_VIEWHOLDER -> R.layout.item_menu
            else -> R.layout.item_other_features
        }

        val view = LayoutInflater.from(viewGroup.context).inflate(layout, viewGroup, false)
        return if (viewType == ITEM_VIEWHOLDER) ViewHolderItem(view) else ViewHolderOtherFeatures(view)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int) {

        if (getItemViewType(i) == ITEM_VIEWHOLDER) {
            val pos = if (positionOtherFeatures < i) {
                i - 1
            } else i

            val item= items[pos]

            //Acceso repetido a un mismo array, gasta tiempo :)
            (viewHolder as ViewHolderItem).itemName.text = item.itemName
            viewHolder.itemIcon.setImageResource(item.itemIcon)

        } else (viewHolder as ViewHolderOtherFeatures).infoTextOtherFeatures.visibility = View.GONE
    }

    override fun getItemCount(): Int {
        return if (positionOtherFeatures == items.size) items.size else items.size + 1
    }

    internal inner class ViewHolderItem(view: View) : RecyclerView.ViewHolder(view) {
        var itemName: TextView
        var itemIcon: ImageView

        init {
            itemName = view.findViewById(R.id.name_item)
            itemIcon = view.findViewById(R.id.img_item)
        }
    }

    internal inner class ViewHolderOtherFeatures(view: View) : RecyclerView.ViewHolder(view){
        val infoTextOtherFeatures: TextView = view.findViewById(R.id.info_text_other_features)
    }

}
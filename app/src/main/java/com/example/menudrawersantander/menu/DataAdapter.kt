package com.example.menudrawersantander.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.menudrawersantander.R
import com.google.android.material.snackbar.Snackbar
import java.util.ArrayList

private const val ITEM_VIEWHOLDER = 0
private const val OTHER_FEATURES_VIEWHOLDER = 1

internal class DataAdapter(private val names: ArrayList<ItemMenu>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return if (position == countNoDefaultDeleted(names)) OTHER_FEATURES_VIEWHOLDER else ITEM_VIEWHOLDER
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

        if (getItemViewType(i) == 0) {
            val pos = if (countNoDefaultDeleted(names) < i) {
                i - 1
            } else i
            (viewHolder as ViewHolderItem).itemName.text = names[pos].itemName
            viewHolder.itemIcon.setImageResource(names[pos].itemIcon)
        }
    }

    override fun getItemCount(): Int {
        return if (countNoDefaultDeleted(names) == names.size) names.size else names.size + 1
    }

    internal inner class ViewHolderItem(view: View) : RecyclerView.ViewHolder(view) {
        var itemName: TextView
        var itemIcon: ImageView

        init {
            itemName = view.findViewById(R.id.name_item)
            itemIcon = view.findViewById(R.id.img_item)

            view.setOnClickListener(object : View.OnClickListener{
                override fun onClick(p0: View?) {
                    Snackbar.make(p0!!, "Replace with your own action", Snackbar.LENGTH_LONG).show()
                }
            })
        }
    }

    internal inner class ViewHolderOtherFeatures(view: View) : RecyclerView.ViewHolder(view)

    private fun countNoDefaultDeleted(list: ArrayList<ItemMenu>): Int {

        var count = 0

        list.forEach {
            if (!it.isDeleted)
                count++
        }

        return count
    }
}
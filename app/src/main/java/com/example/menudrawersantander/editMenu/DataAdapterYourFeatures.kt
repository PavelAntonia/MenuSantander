package com.example.menudrawersantander.editMenu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.menudrawersantander.R
import com.example.menudrawersantander.menu.ItemMenu
import java.util.ArrayList

internal class DataAdapterYourFeatures(private val names: ArrayList<ItemMenu>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_your_features, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int) {
        // Qué significa este 0? Se podría sustituir por un enumerado?
        // Qué valores podrían llegar en este campo?
        if(names[i].type!=0) {
            (viewHolder as ViewHolder).deleteItem.setImageResource(R.drawable.ic_func_031)
        }

        //Podeis ahorraros tener que acceder tantas veces al arry de names, guardando el valor en una variable
        (viewHolder as ViewHolder).iconItem.setImageResource(names[i].itemIcon)
        viewHolder.nameItem.text = names[i].itemName

        //Si este elemento siempre tiene el mismo valor, tiene que asignarse  cada vez que hago un bind?
        viewHolder.sliderItem.setImageResource(R.drawable.ic_sys_15)
    }

    override fun getItemCount(): Int {
        return names.size
    }

    internal inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var deleteItem: ImageView
        var iconItem: ImageView
        var nameItem: TextView
        var sliderItem: ImageView

        init {
            deleteItem = view.findViewById(R.id.delete_item)
            iconItem = view.findViewById(R.id.icon_item)
            nameItem = view.findViewById(R.id.name_item)
            sliderItem = view.findViewById(R.id.slider_item)

        }
    }
}

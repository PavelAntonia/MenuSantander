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
import com.example.menudrawersantander.menu.TypeItemMenu
import java.util.ArrayList

private const val ITEM_VIEWHOLDER = 0
private const val OTHER_FEATURES_VIEWHOLDER = 1


internal class DataAdapterYourFeatures(private val items: ArrayList<ItemMenu>,
                                       positionOtherFeatures: Int,
                                       private val dragStartListener: OnStartDragListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        lateinit var listYourFeatures: ArrayList<ItemMenu>
    }

    init {
        listYourFeatures = items
        items.add(positionOtherFeatures,ItemMenu(1,"Separator",1, positionOtherFeatures,false,TypeItemMenu.SEPARATOR.value))
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position].type == TypeItemMenu.SEPARATOR.value) OTHER_FEATURES_VIEWHOLDER else ITEM_VIEWHOLDER
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

        if (getItemViewType(i) == ITEM_VIEWHOLDER) {

            (viewHolder as ViewHolderItem)

            viewHolder.sliderItem.setOnTouchListener(View.OnTouchListener { v, event ->
                if (MotionEventCompat.getActionMasked(event) == ACTION_DOWN) {
                    dragStartListener.onStartDrag(viewHolder)
                }
                false
            })

            val item = items[i]

            if (item.type != TypeItemMenu.DEFAULT.value) {
                viewHolder.deleteItem.setImageResource(R.drawable.ic_func_031)

                viewHolder.deleteItem.setOnClickListener {

                    items.remove(item)
                    notifyItemRemoved(i)
                    item.position = DataAdapterAllFeatures.listAllFeatures.size
                    DataAdapterAllFeatures.listAllFeatures.add(item)



                }
            }

            viewHolder.iconItem.setImageResource(item.itemIcon)
            viewHolder.nameItem.text = item.itemName
            viewHolder.sliderItem.setImageResource(R.drawable.ic_sys_15)

        } else{

            if(i != items.size - 1)
                (viewHolder as ViewHolderOtherFeatures).infoTextOtherFeatures.visibility = View.GONE
        }
    }


    override fun getItemCount(): Int {
        return items.size
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

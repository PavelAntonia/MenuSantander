package com.example.menudrawersantander.editMenu

import android.annotation.SuppressLint
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.MotionEvent.ACTION_DOWN
import android.view.MotionEvent.ACTION_MASK
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.menudrawersantander.menu.ItemMenu
import com.example.menudrawersantander.menu.TypeItemMenu
import java.util.ArrayList


private const val ITEM_VIEWHOLDER = 0
private const val OTHER_FEATURES_VIEWHOLDER = 1


class DataAdapterYourFeatures(private val items: ArrayList<ItemMenu>, positionOtherFeatures: Int?,
                              private val dragStartListener: OnStartDragListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val Int.toDP: Int
        get() = (this * Resources.getSystem().displayMetrics.density).toInt()

    companion object {
        lateinit var listYourFeatures: ArrayList<ItemMenu>
    }

    init {
        listYourFeatures = items
        ItemMenu.addSeparator(items, positionOtherFeatures)
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position].type == TypeItemMenu.SEPARATOR.value) OTHER_FEATURES_VIEWHOLDER else ITEM_VIEWHOLDER
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val layout = when (viewType) {
            ITEM_VIEWHOLDER -> com.example.menudrawersantander.R.layout.item_your_features
            else -> com.example.menudrawersantander.R.layout.item_other_features
        }

        val view = LayoutInflater.from(viewGroup.context).inflate(layout, viewGroup, false)
        return if (viewType == ITEM_VIEWHOLDER) ViewHolderItem(view) else ViewHolderOtherFeatures(view)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int) {

        if (getItemViewType(i) == ITEM_VIEWHOLDER) {

            (viewHolder as ViewHolderItem)

            viewHolder.draggableZone.setOnTouchListener { _, event ->
                if (event.action and ACTION_MASK == ACTION_DOWN) {
                    dragStartListener.onStartDrag(viewHolder)
                }
                false
            }

            val item = items[i]

            if (item.type != TypeItemMenu.DEFAULT.value) {
                viewHolder.deleteItem.setImageResource(com.example.menudrawersantander.R.drawable.ic_func_031)

                viewHolder.deleteItem.setOnClickListener {

                    val positionOfItem = item.getPosItemAt(items)

                    if (positionOfItem != null) {
                        items.removeAt(positionOfItem)
                        notifyItemRemoved(positionOfItem)

                        val destinationPos = DataAdapterAllFeatures.listAllFeatures.size

                        item.position = destinationPos

                        val posOtherFeatures = ItemMenu.getPositionOtherFeatures(listYourFeatures)
                        FragmentYourFeatures.adapterYourFeatures.notifyItemChanged(posOtherFeatures)

                        DataAdapterAllFeatures.listAllFeatures.add(item)
                        FragmentAllFeatures.adapterAllFeatures.notifyItemInserted(destinationPos)
                    }
                }
            } else {
                viewHolder.deleteItem.visibility = View.GONE

                val parameter = viewHolder.iconItem.getLayoutParams() as ConstraintLayout.LayoutParams
                parameter.marginStart = 24.toDP // left, top, right, bottom
                viewHolder.iconItem.setLayoutParams(parameter)


            }

            viewHolder.iconItem.setImageResource(item.itemIcon)
            viewHolder.nameItem.text = item.itemName
            viewHolder.sliderItem.setImageResource(com.example.menudrawersantander.R.drawable.ic_sys_15)

        } else {

            val visibility = if (i != items.size - 1) View.GONE else View.VISIBLE
            (viewHolder as ViewHolderOtherFeatures).infoTextOtherFeatures.visibility = visibility
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    internal inner class ViewHolderItem(view: View) : RecyclerView.ViewHolder(view) {
        var deleteItem: ImageView = view.findViewById(com.example.menudrawersantander.R.id.delete_item)
        var iconItem: ImageView = view.findViewById(com.example.menudrawersantander.R.id.icon_item)
        var nameItem: TextView = view.findViewById(com.example.menudrawersantander.R.id.name_item)
        var sliderItem: ImageView = view.findViewById(com.example.menudrawersantander.R.id.slider_item)
        var draggableZone: ConstraintLayout = view.findViewById(com.example.menudrawersantander.R.id.draggable_zone)
    }

    internal inner class ViewHolderOtherFeatures(view: View) : RecyclerView.ViewHolder(view) {
        val infoTextOtherFeatures: TextView =
            view.findViewById(com.example.menudrawersantander.R.id.info_text_other_features)
    }
}
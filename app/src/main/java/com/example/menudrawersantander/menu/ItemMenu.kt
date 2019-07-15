package com.example.menudrawersantander.menu

class ItemMenu(

    var id: Int,
    var itemName: String,
    var itemIcon: Int,
    var position: Int,
    var isDeleted: Boolean,
    var type: Int //Types->  Default: 0,  Other: 1, Separador: 2


) : Comparable<ItemMenu> {

    companion object {
        //Remove separator from list and return the position where it was
        fun removeSeparator(list: ArrayList<ItemMenu>): Int? {

            var i = 0
            while (i < list.size) {

                if (list[i].type == TypeItemMenu.SEPARATOR.value) {
                    list.removeAt(i)
                    return i
                } else i++
            }
            return null
        }

        fun getPositionOtherFeatures(list: ArrayList<ItemMenu>):Int {
            var i = 0
            while(i < list.size){
                if(list[i].type == TypeItemMenu.SEPARATOR.value) {
                    return i
                }
                else i++
            }
            return list.size
        }

        fun addSeparator(list: java.util.ArrayList<ItemMenu>, positionOtherFeatures: Int?) {

            val pos = positionOtherFeatures ?: list.size

            list.add(pos, ItemMenu(0, "Separator", 0, pos, false, TypeItemMenu.SEPARATOR.value))
        }
    }

    override fun compareTo(other: ItemMenu): Int {

        return this.position - other.position

    }

    override fun equals(other: Any?): Boolean {
        return if (other is ItemMenu) {
            id == other.id && itemName == other.itemName && position == other.position && isDeleted == other.isDeleted && type == other.type
        } else false
    }

    fun getPosItemAt(items: ArrayList<ItemMenu>): Int? {
        var i = 0
        items.forEach {
            if (it == (this)) {
                return i
            }
            i++
        }
        return null
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + itemName.hashCode()
        result = 31 * result + itemIcon
        result = 31 * result + position
        result = 31 * result + isDeleted.hashCode()
        result = 31 * result + type
        return result
    }
}
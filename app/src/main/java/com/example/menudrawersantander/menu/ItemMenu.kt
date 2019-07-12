package com.example.menudrawersantander.menu

//Genial que esté documentado el type aquí, pero me he recorrido una app entera para verlo xDD
//Intentad usar los enums

class ItemMenu(

    var id: Int,
    var itemName: String,
    var itemIcon: Int,
    var position: Int,
    var isDeleted: Boolean,
    var type: Int //Types->  Default: 0,  Other: 1, Separador: 2



) : Comparable<ItemMenu> {

    override fun compareTo(other: ItemMenu): Int {

        return this.position - other.position

    }

    override fun equals(other: Any?): Boolean {
        return if (other is ItemMenu){
            id == other.id && itemName == other.itemName &&  position == other.position && isDeleted == other.isDeleted && type == other.type
        } else false
    }

    fun getPosItemAt(items: ArrayList<ItemMenu>):Int? {
        var i = 0
        items.forEach {
            if(it == (this)){
                return i
            }
            i++
        }
        return null
    }
}
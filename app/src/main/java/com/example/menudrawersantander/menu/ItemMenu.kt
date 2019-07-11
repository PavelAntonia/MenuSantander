package com.example.menudrawersantander.menu

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
}

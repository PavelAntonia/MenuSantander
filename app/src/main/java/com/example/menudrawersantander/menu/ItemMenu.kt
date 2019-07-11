package com.example.menudrawersantander.menu

//Genial que esté documentado el type aquí, pero me he recorrido una app entera para verlo xDD
//Intentad usar los enums
class ItemMenu(
    var id:Int,
    var itemName:String,
    var itemIcon:Int,
    var position: Int,
    var isDeleted: Boolean,
    var type: Int // 0:must   1:default   2:other
    ):Comparable<ItemMenu>{

    override fun compareTo(other: ItemMenu): Int {
//        if (this.type == 1 && this.isDeleted) {
//            return when (other.type == 1 && other.isDeleted) {
//                true -> 0
//                false -> 1
//            }
//        }
//        else if (this.type == 0 or 2 && other.type == 1 && other.isDeleted){
//            return -1
//        }
        return this.position - other.position
    }
}


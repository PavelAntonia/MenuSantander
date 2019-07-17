package com.santander.globile.globilemenu.menu

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

private const val NAME_SHARED_PREFERENCES = "features"
private const val NAME_YOUR_FEATURES = "yourFeatures"
private const val NAME_ALL_FEATURES = "allFeatures"
private const val NAME_OTHER_FEATURES_POSITION = "otherFeaturesPosition"


class AccessSharedPref(context: Context) {

    private val sharedPref: SharedPreferences = context.getSharedPreferences(NAME_SHARED_PREFERENCES, 0) //Private mode
    private val editor = sharedPref.edit()
    private val groupListType = object : TypeToken<ArrayList<ItemMenu>>() {}.type
    private val gson = Gson()

    //////////////////////////READ DATA//////////////////////////

    fun readYourFeatures(): ArrayList<ItemMenu> {
        val list = gson.fromJson<ArrayList<ItemMenu>>(sharedPref.getString(NAME_YOUR_FEATURES, ""), groupListType)
        list.sort()
        return list
    }

    fun readAllFeatures(): ArrayList<ItemMenu> =
        gson.fromJson(sharedPref.getString(NAME_ALL_FEATURES, ""), groupListType)

    fun readPosOtherFeatures(): Int? {

        val positionOtherFeatures = sharedPref.getInt(NAME_OTHER_FEATURES_POSITION, -1)
        return if (positionOtherFeatures == -1) null else positionOtherFeatures
    }


    //////////////////////////WRITE DATA//////////////////////////

    fun writeYourFeatures(list: ArrayList<ItemMenu>) {

        val listYour = Gson().toJson(list)
        editor.putString(NAME_YOUR_FEATURES, listYour)
        editor.apply()

    }

    fun writeAllFeatures(list: ArrayList<ItemMenu>) {

        val listAll = Gson().toJson(list)
        editor.putString(NAME_ALL_FEATURES, listAll)
        editor.apply()

    }

    fun writePosOtherFeatures(pos: Int?) {

        val posOtherFeatures = pos ?: -1
        editor.putInt(NAME_OTHER_FEATURES_POSITION,posOtherFeatures)
        editor.apply()
    }
}

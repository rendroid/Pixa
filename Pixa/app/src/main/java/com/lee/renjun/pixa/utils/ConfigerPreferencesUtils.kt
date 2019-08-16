package com.lee.renjun.pixa.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class ConfigerPreferencesUtils(var context : Context) {
    companion object {
//        var CONFIG = "config"
        var BaseUrl = "baseurl"
    }

        private var preferences = PreferenceManager.getDefaultSharedPreferences(context)

        fun get(key : String) : String{
            return preferences.getString(key,"one")
        }

        fun set(key: String,value: String) {
            preferences.edit().putString(key,value).commit()
        }

}
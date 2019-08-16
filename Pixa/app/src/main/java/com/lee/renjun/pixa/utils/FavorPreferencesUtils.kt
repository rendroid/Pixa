package com.lee.renjun.pixa.utils

import android.content.Context
import android.preference.PreferenceManager

class FavorPreferencesUtils(var context : Context) {
    companion object {
        var FAVOR = "favor"
    }

        private var preferences = context.getSharedPreferences(FAVOR,Context.MODE_PRIVATE)!!

        fun contain(str : String) : Boolean{
            var set = preferences.getStringSet(FAVOR,null)
            if (null == set){
                return false
            }else return set.contains(str)
        }

        fun getAll() : ArrayList<String>{
            var list = arrayListOf<String>()
            var set = preferences.getStringSet(FAVOR,null)
            if (null == set){
                return list
            }else{
                for (item in set){
                    list.add(item)
                }
                return list
            }
        }

        fun remove(str : String){
            preferences.getStringSet(FAVOR,null)?.remove(str)
        }

         fun insert (str : String){
             var set = preferences.getStringSet(FAVOR,null)
             if (null == set){
                 var newSet = HashSet<String>()
                 newSet.add(str)
                 preferences.edit().putStringSet(FAVOR,newSet).commit()
             }else{
                 var newSet = HashSet<String>()
                 newSet.addAll(set)
                 newSet.add(str)
                 preferences.edit().putStringSet(FAVOR,newSet).commit()
             }
         }

}
package com.lee.renjun.pixa.utils

import android.content.Context
import android.preference.PreferenceManager
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class ColumnPreferencesUtils(var context : Context) {
    companion object {
        var COLUMN = "column"
    }

        private var preferences = context.getSharedPreferences(COLUMN,Context.MODE_PRIVATE)

        fun contain(str : String) : Boolean{
            var set = preferences.getStringSet(COLUMN,null)
            if (null == set){
                return false
            }else return set.contains(str)
        }

        fun getAll() : ArrayList<String>{
            var list = arrayListOf<String>()
            var set = preferences.getStringSet(COLUMN,null)
            if (null == set){
                return list
            }else{
                print("nonull>>>>>>>>>>>>>>>>>>>"+set.toString())
                for (item in set){
                    list.add(item)
                }
                return list
            }
        }

        fun remove(str : String){
            preferences.getStringSet(COLUMN,null)?.remove(str)
        }

         fun insert (name: String,str : String){
             var set = preferences.getStringSet(name,null)
             if (null == set){
                 var newSet = HashSet<String>()
                 newSet.add(str)
                 preferences.edit().putStringSet(COLUMN,newSet).commit()

             }else{
                 var newSet = HashSet<String>()
                 newSet.addAll(set)
                 newSet.add(str)
                 preferences.edit().putStringSet(COLUMN,newSet).commit()

             }
         }

}
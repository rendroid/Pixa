package com.lee.renjun.pixa.utils

import com.lee.renjun.pixa.R

class BgColor {
    companion object {
        var colorList = arrayListOf(R.color.bg,R.color.bg1,R.color.bg2,R.color.bg3,R.color.bg4,R.color.bg5,R.color.bg6)
        fun getRandom() : Int{
            return colorList[(Math.random() * (colorList.size-1)).toInt()]
        }
    }
}
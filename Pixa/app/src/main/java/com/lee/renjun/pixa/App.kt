package com.lee.renjun.pixa

import android.app.Application
import com.lee.renjun.pixa.utils.GetUrlList
import com.umeng.commonsdk.UMConfigure

class App : Application() {
    lateinit var getUrlList : GetUrlList
    override fun onCreate() {
        super.onCreate()
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE,"")
        getUrlList = GetUrlList(this)
    }
}
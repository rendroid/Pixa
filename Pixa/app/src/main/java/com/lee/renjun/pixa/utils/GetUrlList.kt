package com.lee.renjun.pixa.utils

import android.app.Activity
import android.app.Application
import org.jsoup.Jsoup
import java.io.IOException
import java.net.SocketTimeoutException

class GetUrlList(var context: Application) {


    init {
        var type = ConfigerPreferencesUtils(context).get(ConfigerPreferencesUtils.BaseUrl)
        if (type == "one") {
            baseUrl = baseUrl_one
            addPage = addPage_one
        } else {
            baseUrl = baseUrl_two
            addPage = addPage_two
        }
    }

    interface CallBack {
        fun run(urlList: ArrayList<String>)
    }

    companion object {


        var baseUrl_one = "https://pixabay.com/zh/images/search/"
        var addPage_one = "?pagi="
        var baseUrl_two = "https://www.pexels.com/zh-cn/search/"
        var addPage_two = "?page="
        var baseUrl_three = "https://www.piqsels.com/zh/search"
        var addPage_three = "&page=1"
        var baseUrl_four = "https://www.colorhub.me/search"
        var addPage_four = "?page="
        var urlList = arrayListOf(baseUrl_one, baseUrl_two, baseUrl_three, baseUrl_four)
        var addPageList = arrayListOf(addPage_one, addPage_two, addPage_three, addPage_four)


        var baseUrl = urlList[0]
        var addPage = addPageList[0]

        fun setBaseUrl(id : Int){
            baseUrl = urlList[id]
            addPage = addPageList[id]
        }
    }

    fun strToUrl(keyWord: String, page: Int): String {
        return baseUrl + keyWord + addPage + page
    }

    fun urlToList(url: String): ArrayList<String> {
        var urlList = ArrayList<String>()

        try {
            val doc = Jsoup.connect(url).get()//java.net.SocketTimeoutException: timeout
            val media = doc.select("[src]")
            for (src in media) {
                if (src.tagName() == "img" && src.attr("abs:src").contains(getSubStr(baseUrl))) {
                    urlList.add(src.attr("abs:src"))
                }
            }
        } catch (e: Exception) {
            //
        }
        return urlList
    }

    fun strToList(keyWord: String, page: Int): ArrayList<String> {
        return urlToList(strToUrl(keyWord, page))
    }

    fun getUrlsAsync(context: Activity, baseUrl: String, KeyWord: String, page: Int, callBack: CallBack) {
        Thread(Runnable {
            var urlList = urlToList(strToUrl(KeyWord, page))
            context.runOnUiThread {
                callBack.run(urlList)
            }
        }).start()
    }

    fun getSubStr(url:String) : String{
        if (url == baseUrl_one){
            return "cdn.pixabay.com"
        }else{
            return "images.pexels.com/photos"
        }
    }


}
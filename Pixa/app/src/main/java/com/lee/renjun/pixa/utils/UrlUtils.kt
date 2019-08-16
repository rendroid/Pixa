package com.lee.renjun.pixa.utils

class UrlUtils {
    companion object {
        var urlBuddha = "https://images.unsplash.com/photo-1529485726363-95c8d62f656f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=667&q=80"
        var urlYuga = "https://cdn.pixabay.com/photo/2018/11/30/21/07/sunrise-3848628_960_720.jpg"
        var urlCat = "https://images.pexels.com/photos/1870376/pexels-photo-1870376.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940"
        var urlLove = "https://cdn.pixabay.com/photo/2015/06/22/08/37/children-817365__340.jpg"
        var urlFamily = "https://cdn.pixabay.com/photo/2017/09/05/11/37/baby-2717347__340.jpg"
        var urlboyandgirl = "https://images.pexels.com/photos/1767434/pexels-photo-1767434.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
        var urldesert = "https://images.pexels.com/photos/53537/caravan-desert-safari-dune-53537.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
        var urlColor = "https://images.pexels.com/photos/1020315/pexels-photo-1020315.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
        var urlNature = "https://images.unsplash.com/photo-1437422061949-f6efbde0a471?ixlib=rb-1.2.1&auto=format&fit=crop&w=400&q=60"
        var urlSunset = "https://cdn.pixabay.com/photo/2015/01/28/23/34/landscape-615428__340.jpg"
        var urlPeace  = "https://cdn.pixabay.com/photo/2016/11/14/04/36/boy-1822614__340.jpg"

        var workData = arrayListOf<String>(urlColor,urlFamily,
            urlPeace,urlSunset,urlCat,urlLove,urlNature,urlYuga,urlboyandgirl,urlBuddha,urldesert)
        var keyWords = arrayListOf<String>("颜色","家庭","平静","日落","猫","爱","自然","瑜伽","男孩女孩","佛","沙漠","山"
            ,"海","杯子","宇宙","黑洞","暮光","幸福","阳光","月亮","龙","情侣","旅行","开心","舞蹈","雕塑","宗教","云","笔","儿童","鸟","高峰"
            ,"婚礼","秋天","森林","蓝","树","神秘","小麦","日常","叶子","冥想","女孩","鲜花","日出","原野","猫头鹰","和尚","全景图","灯塔","火山"
            ,"冰川","海底","狼","伊斯兰","耶稣","微笑","美丽")


        fun getWorksData(): ArrayList<String> {
            return workData
        }

        fun workKeyWords() : ArrayList<String>{
            var workKeyWords = arrayListOf<String>()
            while (workKeyWords.size<8){
                var keyWord = keyWords[(Math.random()* (keyWords.size-1)).toInt()]
                if (!workKeyWords.contains(keyWord)){
                    workKeyWords.add(keyWord)
                }
            }
            return workKeyWords
        }
    }
}
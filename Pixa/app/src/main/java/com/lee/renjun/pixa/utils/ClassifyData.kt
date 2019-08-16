package com.lee.renjun.pixa.utils

import com.lee.renjun.pixa.Bean.ClassifyDataBean

class ClassifyData {
    companion object {
        var nameList = arrayListOf<String>("四季","艺术","宇宙","运动","动物","自然","地理","情感")
        var titleList = arrayListOf(arrayListOf("秋","冬","春","夏"),
            arrayListOf("雕塑","绘画","音乐","舞蹈"),arrayListOf("黑洞","大爆炸","土星","木星","火星","金星","水星","天王星"),arrayListOf("瑜伽","极限","滑雪","跳伞"),
            arrayListOf("马","鱼","狮子","鸟"),arrayListOf("山脉","冰霜","火山","大海"),arrayListOf("中国","瑞士","美国","英国"),
            arrayListOf("家庭","友情","爱","幸福"))
        var keyWordList = arrayListOf(arrayListOf("秋","雪","春","夏"),
        arrayListOf("雕塑","绘画","音乐","舞蹈"),arrayListOf("黑洞","大爆炸","土星","木星","火星","金星","水星","天王星"),arrayListOf("瑜伽","极限","滑雪","跳伞"),
        arrayListOf("马","鱼","狮子","鸟"),arrayListOf("山脉","冰霜","火山","大海"),arrayListOf("中国","瑞士","美国","英国"),
        arrayListOf("家庭","友情","爱","幸福"))
        fun getClassifyData() : ArrayList<ClassifyDataBean>{
            var data = arrayListOf<ClassifyDataBean>()
            for (i in 0 until 8){
                var classifyDataBean = ClassifyDataBean(nameList[i], titleList[i], keyWordList[i])
                data.add(classifyDataBean)
            }
            return data
        }
    }

}
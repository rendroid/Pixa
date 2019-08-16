package com.lee.renjun.pixa

import com.lee.renjun.pixa.utils.BgColor
import com.lee.renjun.pixa.utils.GetUrlList
import com.lee.renjun.pixa.utils.UrlUtils
import com.lee.renjun.pixa.utils.UrlUtils.Companion.workKeyWords
import kotlinx.android.synthetic.main.activity_edit.*
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        for (i in 0..100){
            println(BgColor.getRandom())
        }
    }
}

package com.lee.renjun.pixa.ui.activity

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.lee.renjun.pixa.R
import com.lee.renjun.pixa.adapters.PictureViewAdapter
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.activity_picture_view.*

class PictureViewActivity : AppCompatActivity() {

    companion object {
        var DATA = "data"
        var PARAM_POSITION = "position"
    }
    var data = ArrayList<String>()
    var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
//        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        setContentView(R.layout.activity_picture_view)
        data = intent.getStringArrayListExtra(DATA)
        position = intent.getIntExtra(PARAM_POSITION,0)
        view_pager.adapter = PictureViewAdapter(data,supportFragmentManager)
        view_pager.currentItem = position
    }
    override fun onResume() {
        super.onResume()
        MobclickAgent.onResume(this)
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPause(this)
    }
}

package com.lee.renjun.pixa.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lee.renjun.pixa.R
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        imageView_back.setOnClickListener {
            this.finish()
        }
        imageView_search.setOnClickListener {
            submitSearch()
        }
        edit_text_search.setOnEditorActionListener { v, actionId, event ->
            submitSearch()
            true
        }


    }

    fun submitSearch(){
        var keyWord = edit_text_search.text.trim()
        if (null != keyWord){
            var intent = Intent(this,PictureListActivity::class.java)
            intent.putExtra(PictureListActivity.KEYWORD,keyWord.toString())
            startActivity(intent)
        }
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

package com.lee.renjun.pixa.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lee.renjun.pixa.App
import com.lee.renjun.pixa.R
import com.lee.renjun.pixa.adapters.ClassifyShowAdapter
import com.lee.renjun.pixa.utils.GetUrlList
import kotlinx.android.synthetic.main.activity_classify_show.*
import kotlinx.android.synthetic.main.content_classify_show.*
import com.umeng.analytics.MobclickAgent



class ClassifyShowActivity : AppCompatActivity() {
    lateinit var getUrlList : GetUrlList

    private var textViewList = arrayListOf<TextView>()
    private var recyclerViewList = arrayListOf<RecyclerView>()
    companion object {
        var NAME  = "name"
        var TITTLES = "tittleList"
        var KEYWORDS = "keyWordList"
    }
    private var name = ""
    private var tittleList = ArrayList<String>()
    private var keyWordList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_classify_show)

        getUrlList = (applicationContext as App).getUrlList
        name = intent.getStringExtra(NAME)
        tittleList = intent.getStringArrayListExtra(TITTLES)
        keyWordList = intent.getStringArrayListExtra(KEYWORDS)
        textView_title.text = name

        initView()
        goneView()

        for (i in 0 until Math.min(tittleList.size,8)){
            textViewList[i].text = tittleList[i]
            recyclerViewList[i].layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
            Thread(Runnable {
                var urlList = getUrlList.urlToList(getUrlList.strToUrl(keyWordList[i],1))
//                data1.addAll(urlList)
                println(urlList.toString())
                this.runOnUiThread {
                    recyclerViewList[i].adapter = ClassifyShowAdapter(urlList,this)
                }
            }).start()

        }

        imageView_search.setOnClickListener {
            startActivity(Intent(this@ClassifyShowActivity,SearchActivity::class.java))
        }
        imageView_back.setOnClickListener {
            this.finish()
        }

        }

    private fun goneView() {
        for (i in keyWordList.size until textViewList.size){
            textViewList[i].visibility = View.GONE
            recyclerViewList[i].visibility = View.GONE
        }
    }

    private fun initView() {
        textViewList.add(textView1)
        recyclerViewList.add(recycler_view1)
        textViewList.add(textView2)
        recyclerViewList.add(recycler_view2)
        textViewList.add(textView3)
        recyclerViewList.add(recycler_view3)
        textViewList.add(textView4)
        recyclerViewList.add(recycler_view4)
        textViewList.add(textView5)
        recyclerViewList.add(recycler_view5)
        textViewList.add(textView6)
        recyclerViewList.add(recycler_view6)
        textViewList.add(textView7)
        recyclerViewList.add(recycler_view7)
        textViewList.add(textView8)
        recyclerViewList.add(recycler_view8)
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


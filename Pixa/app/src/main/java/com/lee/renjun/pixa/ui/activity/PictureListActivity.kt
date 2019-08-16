package com.lee.renjun.pixa.ui.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.lee.renjun.pixa.App
import com.lee.renjun.pixa.R
import com.lee.renjun.pixa.adapters.PictureListAdapter
import com.lee.renjun.pixa.utils.GetUrlList
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.activity_picture_list.*

class PictureListActivity : AppCompatActivity() {
    lateinit var getUrlList : GetUrlList

    companion object {
        var KEYWORD = "keyWord"
    }
    var activity = this
    var data = ArrayList<String>()
    var currentPage = 0
    var keyWord = ""
    var url = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture_list)

        getUrlList = (application as App).getUrlList
        keyWord = intent.getStringExtra(PictureListActivity.KEYWORD)
        textView_father.text = keyWord

        xRecyclerView.setLoadingMoreEnabled(true)
        xRecyclerView.setPullRefreshEnabled(false)
        xRecyclerView.setLoadingListener(object : XRecyclerView.LoadingListener{
            override fun onLoadMore() {
                Thread(Runnable {
                    url = getUrlList.strToUrl(keyWord,++currentPage)
                    data.addAll(getUrlList.urlToList(url))
                    activity.runOnUiThread {
                        xRecyclerView.refresh()
                        xRecyclerView.loadMoreComplete()
                    }
                }).start()
            }

            override fun onRefresh() {}
        })
        var layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        xRecyclerView.layoutManager = layoutManager
        xRecyclerView.adapter = PictureListAdapter(data,this)

        url = getUrlList.strToUrl(keyWord,++currentPage)

        Thread(Runnable {
            data = getUrlList.urlToList(url)
            activity.runOnUiThread {
                if (data.size>0){
                    Glide.with(this).load(data[0]).into(image_bg)
                    xRecyclerView.adapter  = PictureListAdapter(data,this)
                }

            }
        }).start()

        app_bar_layout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { bar, offset ->
//            toolbar.alpha = offset*1.0f/bar!!.totalScrollRange
            toolbar.setBackgroundColor(changeAlpha(Color.WHITE,Math.abs(offset*1.0f)/bar!!.getTotalScrollRange()))
        })

        imageView_search.setOnClickListener {
            startActivity(Intent(this@PictureListActivity,SearchActivity::class.java))
        }

        imageView_back.setOnClickListener {
            this.finish()
        }


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this , arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),1)
        }
    }


    //    /** 根据百分比改变颜色透明度 */
    private fun changeAlpha(color : Int, fraction : Float) : Int {
        var red = Color.red(color)
        var green = Color.green(color)
        var blue = Color.blue(color)
        var alpha =  (Color.alpha(color) * fraction).toInt()
        return Color.argb(alpha, red, green, blue)
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


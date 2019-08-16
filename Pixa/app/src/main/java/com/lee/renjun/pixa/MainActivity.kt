package com.lee.renjun.pixa

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.lee.renjun.pixa.Bean.ClassifyDataBean
import com.lee.renjun.pixa.adapters.ClassifyAdapter
import com.lee.renjun.pixa.adapters.CollectionAdapter
import com.lee.renjun.pixa.adapters.DownloadAdapter
import com.lee.renjun.pixa.adapters.WorksAdapter
import com.lee.renjun.pixa.recyclerview.UnScrollGridLayoutViewManager
import com.lee.renjun.pixa.ui.activity.EditActivity
import com.lee.renjun.pixa.ui.activity.SearchActivity
import com.lee.renjun.pixa.utils.*
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.io.File

class MainActivity : AppCompatActivity() {
    lateinit var getUrlList : GetUrlList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(!NetState.isNetworkConnected(this)){
            Snackbar.make(imageView_add,"网络异常",Snackbar.LENGTH_SHORT).show()
        }
        getUrlList = (application as App).getUrlList

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this , arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),1)
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this , arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1)
        }

//works
        recycler_view_works.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        var workWords = UrlUtils.workKeyWords()
        recycler_view_works.adapter = WorksAdapter(workWords,this, getUrlList)

// classify
        var layoutManager = UnScrollGridLayoutViewManager(this,2)
        layoutManager.orientation = RecyclerView.VERTICAL
        layoutManager.setScrollEnabled(false)
        recycler_view_classify.layoutManager = layoutManager

        var dataBeans = arrayListOf<ClassifyDataBean>()

        var columns = ColumnPreferencesUtils(this).getAll()
        for (item in columns){
            var dataBean= Gson().fromJson<ClassifyDataBean>(item,ClassifyDataBean::class.java)
            dataBeans.add(dataBean)
        }
        dataBeans.addAll(ClassifyData.getClassifyData())

        recycler_view_classify.adapter = ClassifyAdapter(dataBeans,this, recycler_view_classify)
//favor
        recycler_view_collect.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        recycler_view_collect.adapter = CollectionAdapter(FavorPreferencesUtils(this).getAll(),this)

// download
        var file = File( Environment.getExternalStorageDirectory().toString(),"搜图引擎")
        if(file.exists() && file.listFiles().isNotEmpty()){
            recycler_view_download.visibility = View.VISIBLE
            recycler_view_download.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
            recycler_view_download.adapter = DownloadAdapter(File( Environment.getExternalStorageDirectory().toString(),"搜图引擎"),this)
        }

        imageView_add.setOnClickListener {
            startActivity(Intent(this,EditActivity::class.java))
        }
        imageView_change_baseurl.setOnClickListener {
            var baseurl = ConfigerPreferencesUtils(this).get(ConfigerPreferencesUtils.BaseUrl)
            if (baseurl == "one"){
                ConfigerPreferencesUtils(this).set(ConfigerPreferencesUtils.BaseUrl,"two")
                GetUrlList.setBaseUrl(1)
            }else{
                ConfigerPreferencesUtils(this).set(ConfigerPreferencesUtils.BaseUrl,"one")
                GetUrlList.setBaseUrl(0)
            }

            Snackbar.make(recycler_view_collect,"更换图片来源" ,Snackbar.LENGTH_SHORT).show()
        }

        imageView_search.setOnClickListener {
            startActivity(Intent(this@MainActivity,SearchActivity::class.java))
        }

    }

    override fun onResume() {
        super.onResume()
        var collections = FavorPreferencesUtils(this).getAll()
        if (collections.size == 0){
            recycler_view_collect.visibility = View.GONE
        }else{
            recycler_view_collect.adapter = CollectionAdapter(collections,this)
        }
        MobclickAgent.onResume(this)
    }


    override fun onPause() {
        super.onPause()
        MobclickAgent.onPause(this)
    }


}

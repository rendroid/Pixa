package com.lee.renjun.pixa.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.lee.renjun.pixa.Bean.ClassifyDataBean
import com.lee.renjun.pixa.R
import com.lee.renjun.pixa.utils.ColumnPreferencesUtils
import com.lee.renjun.pixa.utils.FavorPreferencesUtils
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        button_confirm.setOnClickListener {

            var name = text_view_name.text.toString().trim()
            var column = text_view_column.text.toString().trim()
            if(null != name && null!= column && "" != name && "" != column){
                var list = column.split("\\s+".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                var arrayList = arrayListOf<String>()
                for (item in list){
                    arrayList.add(item)
                }
                var dataBean = ClassifyDataBean(name,arrayList,arrayList)
                var gson = Gson()
                var dataStr = gson.toJson(dataBean)
                ColumnPreferencesUtils(this).insert(ColumnPreferencesUtils.COLUMN,dataStr)
                Toast.makeText(this,"添加类目:$name",Toast.LENGTH_SHORT).show()
            }
        }

//        ColumnPreferencesUtils(this).getAll().toString()+"/n"+ FavorPreferencesUtils(this).getAll().toString()

        imageView_back.setOnClickListener {
            this.finish()
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

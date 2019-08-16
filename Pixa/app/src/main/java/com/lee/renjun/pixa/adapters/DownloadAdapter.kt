package com.lee.renjun.pixa.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lee.renjun.pixa.R
import com.lee.renjun.pixa.ui.activity.PictureViewActivity
import java.io.File
import java.io.FilenameFilter

class DownloadAdapter(var file : File, var context : Context) : RecyclerView.Adapter<DownloadAdapter.ViewHolder>() {
    var data = arrayListOf<String>()
    init {
        var fileList = file.listFiles { dir, name -> (null!=name)&&name.contains(".jpg") }
        for (file in fileList){
            data.add(file.absolutePath.toString())
        }
    }



    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_card_view_work,viewGroup,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            var intent = Intent(context, PictureViewActivity::class.java)
            intent.putStringArrayListExtra(PictureViewActivity.DATA,data)
            intent.putExtra(PictureViewActivity.PARAM_POSITION,position)
            context.startActivity(intent)
        }
        Glide.with(context).load(data[position]).into(holder.image)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

//        var title = itemView.findViewById<TextView>(R.id.text)
        var image = itemView.findViewById<ImageView>(R.id.image_view)
    }


}
package com.lee.renjun.pixa.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lee.renjun.pixa.R
import com.lee.renjun.pixa.ui.activity.PictureViewActivity
import com.lee.renjun.pixa.utils.BgColor

class PictureListAdapter(var data : ArrayList<String>, var context : Context) : RecyclerView.Adapter<PictureListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_picture_list,viewGroup,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (null == holder)return
        holder.itemView.setOnClickListener {
            var intent = Intent(context, PictureViewActivity::class.java)
            intent.putStringArrayListExtra(PictureViewActivity.DATA,data)
            intent.putExtra(PictureViewActivity.PARAM_POSITION,position)
            context.startActivity(intent)
        }

        var tag = holder.imageView.tag
//        被复用的holder
        if (null!= tag && tag!= position){
            Glide.with(context).clear(holder.imageView)
        }
        Glide.with(context).load(data[position]).apply(RequestOptions().placeholder(BgColor.getRandom())).into(holder.imageView)
        holder.imageView.setTag(R.id.imageView,position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

//        var title = itemView.findViewById<TextView>(R.id.text)
        var imageView = itemView.findViewById<ImageView>(R.id.imageView)
    }


}
package com.lee.renjun.pixa.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lee.renjun.pixa.MainActivity
import com.lee.renjun.pixa.R
import com.lee.renjun.pixa.ui.activity.PictureListActivity
import com.lee.renjun.pixa.utils.GetUrlList
import com.lee.renjun.pixa.utils.UrlUtils
import kotlinx.android.synthetic.main.activity_picture_list.*

class WorksAdapter(var data : List<String>, var context : Context,var getUrlList : GetUrlList) : RecyclerView.Adapter<WorksAdapter.ViewHolder>() {


    fun reSetData( data : List<String>){
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_card_view_work,viewGroup,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            var intent = Intent(context, PictureListActivity::class.java)
            intent.putExtra(PictureListActivity.KEYWORD,data[position])
            context.startActivity(intent)
        }
        holder.textView.text = data[position]
        Thread(Runnable {
            var list = getUrlList.strToList(data[position],1)
            if (list.size > 0)
                (context as MainActivity).runOnUiThread {
                    Glide.with(context as MainActivity).load(list[0]).into(holder.image)
            }
        }).start()
        Glide.with(context).load(data[position]).into(holder.image)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var image = itemView.findViewById<ImageView>(R.id.image_view)
        var textView = itemView.findViewById<TextView>(R.id.textView_father)
    }


}
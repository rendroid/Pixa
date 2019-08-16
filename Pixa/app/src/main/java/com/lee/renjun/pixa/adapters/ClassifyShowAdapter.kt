package com.lee.renjun.pixa.adapters

import android.content.Context
import android.content.Intent
import android.view.*
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lee.renjun.pixa.MainActivity
import com.lee.renjun.pixa.R
import com.lee.renjun.pixa.ui.activity.ClassifyShowActivity
import com.lee.renjun.pixa.ui.activity.PictureViewActivity

class ClassifyShowAdapter(var data : ArrayList<String>, var context : Context) : RecyclerView.Adapter<ClassifyShowAdapter.ViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_card_view_work,viewGroup,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            var intent = Intent(context, PictureViewActivity::class.java)
            intent.putExtra(PictureViewActivity.PARAM_POSITION,position)
            intent.putStringArrayListExtra(PictureViewActivity.DATA,data)
            context.startActivity(intent)
        }
        Glide.with(context as ClassifyShowActivity).load(data[position]).into(holder.image)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var image = itemView.findViewById<ImageView>(R.id.image_view)
    }


}
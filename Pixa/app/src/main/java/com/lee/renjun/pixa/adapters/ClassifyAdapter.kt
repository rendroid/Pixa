package com.lee.renjun.pixa.adapters

import android.content.Context
import android.content.Intent
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lee.renjun.pixa.App
import com.lee.renjun.pixa.Bean.ClassifyDataBean
import com.lee.renjun.pixa.MainActivity
import com.lee.renjun.pixa.R
import com.lee.renjun.pixa.ui.activity.ClassifyShowActivity
import com.lee.renjun.pixa.utils.BgColor

class ClassifyAdapter(var data : ArrayList<ClassifyDataBean>, var context : Context, var recyclerView: RecyclerView) : RecyclerView.Adapter<ClassifyAdapter.ViewHolder>() {
    init {
        var param = recyclerView.layoutParams
        var scale = context.resources.displayMetrics.density
        param.height = (180 * scale + 0.5f).toInt() * (itemCount/2 + itemCount%2)
        recyclerView.layoutParams = param
    }
    var getUrlList = (context.applicationContext as App).getUrlList


    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_card_view_classify,viewGroup,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            var intent = Intent(context, ClassifyShowActivity::class.java)
            intent.putExtra(ClassifyShowActivity.NAME,data[position].name)
            intent.putStringArrayListExtra(ClassifyShowActivity.TITTLES, data[position].titles)
            intent.putStringArrayListExtra(ClassifyShowActivity.KEYWORDS, data[position].keyWords)
            context.startActivity(intent)
        }
        holder.textView.text = data[position].name
        if(data[position].keyWords.size>0) {
            Thread(Runnable {
            var urlList = getUrlList.urlToList(getUrlList.strToUrl(data[position].keyWords[0],1))
                if (urlList.size > 0 ){
                    (context as MainActivity).runOnUiThread {
                        if (urlList.size > 0) {
                            Glide.with(context as MainActivity).load(urlList[0]).into(holder.image)
                        }
                    }
                }

            }).start()
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var textView = itemView.findViewById<TextView>(R.id.textView_father)
        var image = itemView.findViewById<ImageView>(R.id.image_view)
    }


}
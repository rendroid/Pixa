package com.lee.renjun.pixa.ui.fragment

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bm.library.PhotoView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.snackbar.Snackbar
import com.lee.renjun.pixa.R
import com.lee.renjun.pixa.ui.activity.PictureViewActivity
import com.lee.renjun.pixa.utils.CopyFile
import com.lee.renjun.pixa.utils.FavorPreferencesUtils
import kotlinx.android.synthetic.main.fragment_picture_view.*
import java.io.*
import java.nio.ByteBuffer


class PictureViewFragment : Fragment() {
    private lateinit var url : String
    var isShowOverLay = true

    companion object {
        var KEYWORD = "url"
        fun newInstance(url : String) : PictureViewFragment {
            var fragment = PictureViewFragment()
            var arguments = Bundle()
            arguments.putString(PictureViewFragment.KEYWORD,url)
            fragment.arguments = arguments
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        url = arguments!!.getString(PictureViewFragment.KEYWORD)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_picture_view, container, false)
        var photoView = view.findViewById<PhotoView>(R.id.photoView)
        var imageViewfavor = view.findViewById<ImageView>(R.id.imageView_favor)
        var picoverlay = view.findViewById<ConstraintLayout>(R.id.pic_overlay)
        var imageView_down = view.findViewById<ImageView>(R.id.imageView_down)
        photoView.enable()

        if (FavorPreferencesUtils(activity as Context).contain(url)){
            imageViewfavor.setImageResource(R.drawable.ic_action_favorite)
        }

        photoView.setOnClickListener {
            isShowOverLay = !isShowOverLay
            if (isShowOverLay) {
                picoverlay.visibility = View.VISIBLE
            } else {
                picoverlay.visibility = View.GONE
            }
        }

        imageViewfavor.setOnClickListener {
            if (FavorPreferencesUtils(activity as Context).contain(url)) {
                imageViewfavor.setImageResource(R.drawable.ic_action_favorite_border)
                FavorPreferencesUtils(activity as Context).remove(url)
            } else {
                imageViewfavor.setImageResource(R.drawable.ic_action_favorite)
                FavorPreferencesUtils(activity as Context).insert(url)
            }
        }

        imageView_down.setOnClickListener {
            if (ContextCompat.checkSelfPermission(activity as Context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED){

                Thread(Runnable {
                    var sourceFile = Glide.with(this).asFile().load(url).submit().get()
                    var filePath = File( Environment.getExternalStorageDirectory().toString(),"搜图引擎")
                    filePath.setReadable(true,false)
                    if(!filePath.exists()){
                        filePath.mkdirs()
                        filePath.setReadable(true,false)
                    }
                    var fileName = "搜图引擎" + System.currentTimeMillis() + ".jpg"
                    var file =  File(filePath,fileName)
                    file.createNewFile()
                    file.setReadable(true,false)
                    CopyFile.copyFileUsingFileStreams(sourceFile,file)
                    (context as PictureViewActivity).runOnUiThread {
                        Snackbar.make(imageView_down,"下载完成:" + filePath.absolutePath.toString(),Snackbar.LENGTH_SHORT).show()
                    }
                }).start()
            }


        }


        Glide.with(this).load(url)
            .into(photoView)
        return view
    }

}

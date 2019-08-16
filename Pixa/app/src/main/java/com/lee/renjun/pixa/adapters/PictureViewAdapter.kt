package com.lee.renjun.pixa.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.lee.renjun.pixa.ui.fragment.PictureViewFragment

class PictureViewAdapter(var data : ArrayList<String> , fm : FragmentManager) : FragmentStatePagerAdapter(fm,1) {
    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): Fragment {
        return PictureViewFragment.newInstance(data[position])
    }

}
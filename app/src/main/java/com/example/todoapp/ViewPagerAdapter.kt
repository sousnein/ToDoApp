package com.example.todoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fa: FragmentActivity?): FragmentStateAdapter(fa!!) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> SingleLineCalendarFragment()
            else-> SingleLineCalendarFragment()
        }
    }

}


//class ViewPagerAdapter : RecyclerView.Adapter<PagerVH>() {
//
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH =
//        PagerVH(LayoutInflater.from(parent.context).inflate(R.layout.fragment_single_line_calendar, parent, false))
//
//    override fun getItemCount(): Int = 2
//
//    override fun onBindViewHolder(holder: PagerVH, position: Int) = holder.itemView.run {
//    }
//}
//
//class PagerVH(itemView: View) : RecyclerView.ViewHolder(itemView)
package com.xiyou.homepage.pre

import androidx.navigation.Navigation.createNavigateOnClickListener
import com.xiyou.advance.modulespublic.common.net.CourseInfo
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.xiyou.homepage.R

class Adapter_HomepageRecycler_kt(
    var newsList: List<News_Homepage>,
    private val courseList: List<CourseInfo>
) : RecyclerView.Adapter<Adapter_HomepageRecycler_kt.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_homepagerecyclerview, parent, false)
        val viewHolder = ViewHolder(view)
        view.setOnClickListener(createNavigateOnClickListener(R.id.action_homepage_to_undertake))
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news_homepage = newsList[position]
        val courseInfo = courseList[position]
        //        holder.item_hpr_img.setImageResource(news_homepage.getImg_newsHomepage());
//        holder.item_hpr_title.setText(news_homepage.getTitle_newsHomepage());
        // 这样子加载数据， 传参url， ----lxy
        //TODO: Implement the image view load.
        //holder.item_hpr_img.load(newsList[position])
        holder.item_hpr_title.text = courseInfo.title
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var item_hpr_img: ImageView
        var item_hpr_title: TextView

        init {
            item_hpr_img = itemView.findViewById(R.id.item_hpr_img)
            item_hpr_title = itemView.findViewById(R.id.item_hpr_title)
        }
    }
}
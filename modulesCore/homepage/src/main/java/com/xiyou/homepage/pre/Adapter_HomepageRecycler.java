package com.xiyou.homepage.pre;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xiyou.advance.modulespublic.common.net.CourseInfo;
import com.xiyou.homepage.R;

import java.util.List;

public class Adapter_HomepageRecycler extends RecyclerView.Adapter<Adapter_HomepageRecycler.ViewHolder> {
    List<News_Homepage> newsList;
    private List<CourseInfo> courseList;
    CourseInfo courseInfo;
    final String TAG=  "Adapter_HeRecyclerTAG";
    public Adapter_HomepageRecycler(List<News_Homepage> newsList,List<CourseInfo> courseList) {
        this.newsList = newsList;
        this.courseList = courseList;
    }

    @SuppressLint("ResourceType")
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_homepagerecyclerview,parent,false);
       ViewHolder viewHolder =  new ViewHolder(view);
       view.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               NavController controller=Navigation.findNavController(parent);
               Bundle bundle=new Bundle();
               bundle.putString("img",courseInfo.cover);
               Log.d(TAG,"");
               //Navigation.createNavigateOnClickListener(R.id.action_homepage_to_undertake,bundle);
               controller.navigate(R.id.action_homepage_to_undertake,bundle);
           }
       });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News_Homepage news_homepage = newsList.get(position);
        courseInfo = courseList.get(position);
//        holder.item_hpr_img.setImageResource(news_homepage.getImg_newsHomepage());
//        holder.item_hpr_title.setText(news_homepage.getTitle_newsHomepage());
        Glide.with(holder.item_hpr_title.getContext())
                .load(courseInfo.cover)
                .placeholder(com.advance.modulespublic.common.R.drawable.img0)//图片加载出来前，显示的图片
                .error(com.advance.modulespublic.common.R.drawable.img0)//图片加载失败后，显示的图片
                .into(holder.item_hpr_img);
        holder.item_hpr_title.setText(courseInfo.title);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
    protected static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView item_hpr_img;
        TextView item_hpr_title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_hpr_img = itemView.findViewById(R.id.item_hpr_img);
            item_hpr_title = itemView.findViewById(R.id.item_hpr_title);
        }
    }
}

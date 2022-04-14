package com.xiyou.homepage.pre;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.xiyou.homepage.R;

import java.util.List;

public class Adapter_HomepageRecycler extends RecyclerView.Adapter<Adapter_HomepageRecycler.ViewHolder> {
    List<News_Homepage> newsList;
    public Adapter_HomepageRecycler(List<News_Homepage> newsList) {
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_homepagerecyclerview,parent,false);
       ViewHolder viewHolder =  new ViewHolder(view);
       view.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_homepage_to_undertake));
       return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News_Homepage news_homepage = newsList.get(position);
        holder.item_hpr_img.setImageResource(news_homepage.getImg_newsHomepage());
        holder.item_hpr_title.setText(news_homepage.getTitle_newsHomepage());
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

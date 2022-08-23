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
import com.xiyou.advance.modulespublic.common.bean.CourseInfo;
import com.xiyou.advance.modulespublic.common.utils.ToastUtil;
import com.xiyou.homepage.R;

import java.util.List;

public class Adapter_HomepageRecycler extends RecyclerView.Adapter<Adapter_HomepageRecycler.ViewHolder> {
    List<News_Homepage> newsList;
    private List<CourseInfo> courseList;
    CourseInfo courseInfo;
    final String TAG = "Adapter_HeRecyclerTAG";

    public Adapter_HomepageRecycler(List<News_Homepage> newsList, List<CourseInfo> courseList) {
        this.newsList = newsList;
        this.courseList = courseList;
        Log.d(TAG, "" + courseList.size());
    }
    @SuppressLint("ResourceType")
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_homepagerecyclerview, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // News_Homepage news_homepage = newsList.get(position);
        Log.d(TAG,"ONbIND"+position);
        if (courseList != null) {
            courseInfo = courseList.get(position);
            View.OnClickListener clickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NavController controller = Navigation.findNavController(holder.item_hpr_title);
                    Bundle bundle = new Bundle();
//                    bundle.putString("img", courseList.get(position).cover);
//                    bundle.putInt("courseId",courseList.get(position).courseId);
//                    bundle.putString("name",courseList.get(position).title);
                    bundle.putParcelable("it",courseList.get(position));
                    Log.d(TAG, position+","+courseList.get(position).cover);
                    //Navigation.createNavigateOnClickListener(R.id.action_homepage_to_undertake,bundle);
                    controller.navigate(R.id.action_homepage_to_undertake, bundle);
                }
            };
            holder.item_hpr_img.setOnClickListener(clickListener);
        } else {
            Log.d(TAG, "" + position);
            ToastUtil.INSTANCE.showToast("出错，请重试！！");
            return;
        }
        Glide.with(holder.item_hpr_title.getContext())
                .load(courseInfo.cover)
                .placeholder(com.advance.modulespublic.common.R.drawable.img0)//图片加载出来前，显示的图片
                .error(com.advance.modulespublic.common.R.drawable.img_1)//图片加载失败后，显示的图片
                .into(holder.item_hpr_img);
        holder.item_hpr_title.setText(courseInfo.title);
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView item_hpr_img;
        TextView item_hpr_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_hpr_img = itemView.findViewById(R.id.item_hpr_img);
            item_hpr_title = itemView.findViewById(R.id.item_hpr_title);
        }
    }
}

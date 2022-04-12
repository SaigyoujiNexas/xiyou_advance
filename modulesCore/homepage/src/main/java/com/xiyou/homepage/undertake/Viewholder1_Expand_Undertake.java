package com.xiyou.homepage.undertake;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xiyou.homepage.R;

import java.util.ArrayList;
import java.util.List;

public class Viewholder1_Expand_Undertake extends RecyclerView.ViewHolder {
    RecyclerView mRecyclerView;
    Context context;
    final String TAG = "Viewholder0TAG";
    List<Comment_Course> list = new ArrayList<>();
    public Viewholder1_Expand_Undertake(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
        mRecyclerView = itemView.findViewById(R.id.recycler_viewholder1_undertake);
        initView();
        Adapter_Viewholder1_Undertake adapter_viewholder1_undertake = new Adapter_Viewholder1_Undertake(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setAdapter(adapter_viewholder1_undertake);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    void initView(){
        Comment_Course comment_course = new Comment_Course();
        comment_course.setComment("This is comment!!");
        comment_course.setSendTime("4-12");
        comment_course.setUserImg(R.drawable.img_round_1);
        comment_course.setUserName("UserName");
        for(int i = 0;i<20;i++)     list.add(comment_course);
    }
}

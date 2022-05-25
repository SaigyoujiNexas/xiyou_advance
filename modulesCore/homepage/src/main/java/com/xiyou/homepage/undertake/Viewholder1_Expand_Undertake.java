package com.xiyou.homepage.undertake;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;
import com.xiyou.advance.modulespublic.common.net.Comment_Course;
import com.xiyou.advance.modulespublic.common.net.GetRequest;
import com.xiyou.homepage.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class Viewholder1_Expand_Undertake extends RecyclerView.ViewHolder {
    RecyclerView mRecyclerView;
    Context context;
    List<Comment_Course> list = new ArrayList<>();
    EditText editText_comment;
    MaterialTextView sendtext;
    FloatingActionButton commentEdit;
    final String TAG = "Viewholder1TAG";
    CardView cardView;
    public Viewholder1_Expand_Undertake(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;

        mRecyclerView = itemView.findViewById(R.id.recycler_viewholder1_undertake);

        sendtext = itemView.findViewById(R.id.sendtext_viewholder1_undertake);

    }

    void initView(){
        //Comment_Course comment_course = new Comment_Course();
//        comment_course.setComment("This is comment!!");
//        comment_course.setSendTime("4-12");
//        comment_course.setUserImg(R.drawable.img_round_1);
//        comment_course.setUserName("UserName");
//        for(int i = 0;i<20;i++)     list.add(comment_course);
        Adapter_Viewholder1_Undertake adapter_viewholder1_undertake = new Adapter_Viewholder1_Undertake(list);
        adapter_viewholder1_undertake.notifyDataSetChanged();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setAdapter(adapter_viewholder1_undertake);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }
    void initRetrofit(){
        Log.d(TAG,"initRetrofit");
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://8.142.65.201:8080").addConverterFactory(MoshiConverterFactory.create()).build();
        GetRequest getRequest = retrofit.create(GetRequest.class);
        Call<List<Comment_Course>> call = getRequest.getComments();
        call.enqueue(new Callback<List<Comment_Course>>() {
             @Override
             public void onResponse(Call<List<Comment_Course>> call, Response<List<Comment_Course>> response) {
                 Log.d(TAG,"onresponsebody:"+response.body()+",errorbody:"+response.errorBody()+",message:"+response.message()+"responsesize:"+response.body().size());
                 Log.d(TAG,"name"+response.body().get(0).comment);
                 list = response.body();
             }

             @Override
             public void onFailure(Call<List<Comment_Course>> call, Throwable t) {
                 Log.d(TAG,"error+"+t.toString());
             }
         });
    }
}

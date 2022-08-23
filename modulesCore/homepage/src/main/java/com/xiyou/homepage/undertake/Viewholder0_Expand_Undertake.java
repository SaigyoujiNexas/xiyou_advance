package com.xiyou.homepage.undertake;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.xiyou.advance.modulespublic.common.bean.ChapterInfo;
import com.xiyou.advance.modulespublic.common.bean.CourseInfo;
import com.xiyou.advance.modulespublic.common.bean.SectionInfo;
import com.xiyou.advance.modulespublic.common.net.BaseResponseTwo;
import com.xiyou.advance.modulespublic.common.net.GetRequest;
import com.xiyou.homepage.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Viewholder0_Expand_Undertake extends RecyclerView.ViewHolder {
    RecyclerView mRecyclerView;
    Context context;
    private String courseName;
    private List<ChapterInfo> chapterList;
    TextView title_viewholder0_undertake;
    final String TAG = "Viewholder0TAG";
    Retrofit retrofit;
    GetRequest getRequest;
    private List<SectionInfo> sectionList;
    CourseInfo mCourseInfo;
    public Viewholder0_Expand_Undertake(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
    }


    public void initData(List<SectionInfo> sectionList){
        Log.d(TAG,"initData");
        int count = 0;
        for(ChapterInfo chapterInfo : chapterList){
            chapterInfo.sectionList = sectionList;
            chapterInfo.chapterIndex=count++;
        }
        //mCourseInfo = courseList.get(0);
//        for (int i = 0; i < courseList.size(); i++) {
//            CourseInfo courseInfo = courseList.get(i);
//            for (int j = 0; j < courseList.size(); j++) {
//                ChapterInfo chapterInfo = courseList.get(j);
//                chapterInfo.setChapterIndex(j);
//            }


    }

    public void initViews(CourseInfo courseInfo){
        Log.d(TAG,"initViews");
        mRecyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_viewholder0_undertake);
        title_viewholder0_undertake = itemView.findViewById(R.id.title_viewholder0_undertake);
        title_viewholder0_undertake.setText(courseName);
        this.courseName = courseInfo.title;
        final Adapter_ExpandRecyclerview chapterAdapter = new Adapter_ExpandRecyclerview(mCourseInfo,chapterList);
        mRecyclerView.setAdapter(chapterAdapter);
        chapterAdapter.notifyDataSetChanged();
        chapterAdapter.setOnItemClickListener(new Adapter_ExpandRecyclerview.OnRecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, Adapter_ExpandRecyclerview.ViewName viewName, int chapterIndex, int sectionIndex) {
                //Timber.v("---onClick---"+viewName+", "+chapterIndex+", "+sectionIndex);
                switch (viewName){
                    case CHAPTER_ITEM:
                        if(chapterList.get(chapterIndex).sectionList.size() > 0){
                            Log.v(TAG,"---onClick---just expand or narrow: "+chapterIndex);
                            if(chapterIndex + 1 == chapterList.size()){
                                //如果是最后一个，则滚动到展开的最后一个item
                                mRecyclerView.smoothScrollToPosition(chapterAdapter.getItemCount());
                                Log.v(TAG,"---onClick---scroll to bottom");
                            }
                        }else{
                            onClickChapter(chapterIndex);
                        }
                        break;
                    case CHAPTER_ITEM_PRACTISE:
                        onClickPractise(chapterIndex);
                        break;
                    case SECTION_ITEM:
                        onClickSection(chapterIndex, sectionIndex);
                        break;
                }
            }
        });

//        //以下是对布局进行控制，让课时占据一行，小节每四个占据一行，结果就是相当于一个ListView嵌套GridView的效果。
//        final GridLayoutManager manager = new GridLayoutManager(context, 4);
//        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return chapterAdapter.getItemViewType(position) == Adapter_ExpandRecyclerview.VIEW_TYPE_CHAPTER ? 4 : 1;
//            }
//        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    public void initChapterRetrofit(int courseId,String courseName,CourseInfo courseInfo){
        mCourseInfo = courseInfo;
        Log.d(TAG,"initRetrofit");
        retrofit = new Retrofit.Builder()
                .baseUrl("http://8.142.65.201:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getRequest = retrofit.create(GetRequest.class);
        HashMap<String,Integer> map = new HashMap<>();
        map.put("courseId",1);
        String bodyStr = new Gson().toJson(map);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), bodyStr);
        Call<BaseResponseTwo<List<ChapterInfo>>> call = getRequest.getContent(requestBody);
        call.enqueue(new Callback<BaseResponseTwo<List<ChapterInfo>>>() {
            @Override
            public void onResponse(Call<BaseResponseTwo<List<ChapterInfo>>> call, Response<BaseResponseTwo<List<ChapterInfo>>> response) {
                Log.d(TAG,"onresponsebody:"+response.body()+",errorbody:"+response.errorBody()+",message:"+response.message());
                // Log.d(TAG,"name:"+response.body().get(0).courseId+","+response.body().get(0).name);
                chapterList = response.body().getData();
                initSectionRetrofit();
            }

            @Override
            public void onFailure(Call<BaseResponseTwo<List<ChapterInfo>>> call, Throwable t) {
                Log.d(TAG,"error+"+t.toString());
            }
        });
    }
    public void initSectionRetrofit(){
        Map<String,Integer> map = new HashMap<>();
        map.put("contentId",2);
        String s = new Gson().toJson(map);
        RequestBody requestBody = RequestBody.create(s, MediaType.parse("application/json; charset=utf-8"));
        Call<BaseResponseTwo<List<SectionInfo>>> call = getRequest.getSection(requestBody);
        call.enqueue(new Callback<BaseResponseTwo<List<SectionInfo>>>() {
            @Override
            public void onResponse(Call<BaseResponseTwo<List<SectionInfo>>> call, Response<BaseResponseTwo<List<SectionInfo>>> response) {
                Log.d(TAG,"onresponsebody:"+response.body()+",errorbody:"+response.errorBody()+",message:"+response.message());
                sectionList = response.body().getData();
                initData(sectionList);
                initViews(mCourseInfo);
                Log.d(TAG,response.body().toString());
                Log.d(TAG,response.body().getData().get(0).toString());
            }

            @Override
            public void onFailure(Call<BaseResponseTwo<List<SectionInfo>>> call, Throwable t) {
                Log.d(TAG,"error+"+t.toString());
            }
        });
//        Call<BaseResponseTwo<List<Object>>> call = getRequest.getSection();
//        call.enqueue(new Callback<BaseResponseTwo<List<SectionInfo>>>() {
//            @Override
//            public void onResponse(Call<BaseResponseTwo<List<SectionInfo>>> call, Response<BaseResponseTwo<List<SectionInfo>>> response) {
//                Log.d(TAG,"onresponsebody:"+response.body()+",errorbody:"+response.errorBody()+",message:"+response.message());
////                sectionList = response.body().getData();
////                initViews(mCourseInfo.title);
//                Log.d(TAG,response.body().toString());
//                Log.d(TAG,response.body().getData().get(0).toString());
//            }
//
//            @Override
//            public void onFailure(Call<BaseResponseTwo<List<SectionInfo>>> call, Throwable t) {
//                Log.d(TAG,"error+"+t.toString());
//            }
//        });
    }
    private void onClickChapter(int chapterIndex){
        Log.v(TAG,"---onClick---play chapter: "+chapterIndex);
        //ToastUtil.showToast(ExpandRecyclerViewActivity.this, "播放"+chapterIndex);
    }

    private void onClickSection(int chapterIndex, int sectionIndex){
        Log.v(TAG,"---onClick---play---section: "+chapterIndex+", "+sectionIndex);
        //ToastUtil.showToast(ExpandRecyclerViewActivity.this, "播放"+chapterIndex+", "+sectionIndex);
    }

    private void onClickPractise(int chapterIndex){
        Log.v(TAG,"---onClick---practise: "+chapterIndex);
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Viewholder0_Expand_Undertake that = (Viewholder0_Expand_Undertake) o;
//        return Objects.equals(mRecyclerView, that.mRecyclerView) && Objects.equals(mCourseInfo, that.mCourseInfo) && Objects.equals(context, that.context) && Objects.equals(courseList, that.courseList) && Objects.equals(title_viewholder0_undertake, that.title_viewholder0_undertake) && Objects.equals(TAG, that.TAG);
//    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(mRecyclerView, mCourseInfo, context, courseList, title_viewholder0_undertake, TAG);
//    }
}
//{"target_id":28947472,"user_id":1102297,"recent_user_id":1102297,"project_id":2375801,"local_target_id":"c71688c5-9bba-47e4-b726-6f53ca407d98","local_parent_id":"491cfcf7-1832-44bb-9be9-06240829ebd9","mark":"developing","is_doc":0,"target_type":"api","example_type":"0","name":"获取目录","method":"POST","sort":5,"status":1,"update_dtime":"2022-08-21 21:58:12","create_users":{"create_user":"修普诺斯","update_user":"修普诺斯"},"mock_server_url":"","request":{"url":"localhost:8080/getContent","description":"","body":{"mode":"application/json","parameter":[],"raw":"{\r\n     \"courseId\":\"1\"\r\n     //\"title\":\"ddddd\"\r\n     // \"cover\":\"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimage31.bookschina.com%2F2010%2F20100209%2F4415793.jpg&refer=http%3A%2F%2Fimage31.bookschina.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1652533760&t=ac465d4dde802c6950e030692b5a7dd8\"\r\n}","raw_para":[]},"event":{"pre_script":"","test":""},"auth":{"type":"noauth","kv":{"key":"","value":""},"bearer":{"key":""},"basic":{"username":"","password":""}},"header":{"parameter":[]},"query":{"parameter":[]},"cookie":{"parameter":[]},"resful":{"parameter":[]},"url_var":"localhost:8080/getContent"},"response":{"success":{"raw":"","parameter":[],"expect":{"name":"成功","isDefault":-1,"code":200,"contentType":"json","verifyType":"mock","schema":"","mock":"{}"}},"error":{"raw":"","parameter":[],"expect":{"name":"失败","isDefault":-1,"code":201,"contentType":"json","verifyType":"mock","schema":"","mock":""}}},"markName":"开发中","markColor":"#3A86FF"}
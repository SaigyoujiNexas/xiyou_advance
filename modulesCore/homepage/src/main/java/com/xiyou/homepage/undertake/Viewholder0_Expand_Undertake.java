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
import com.xiyou.advance.modulespublic.common.net.BaseResponseTwo;
import com.xiyou.advance.modulespublic.common.net.GetRequest;
import com.xiyou.homepage.R;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Viewholder0_Expand_Undertake extends RecyclerView.ViewHolder {
    RecyclerView mRecyclerView;
    CourseInfo mCourseInfo;
    Context context;
    private List<ChapterInfo> chapterList;
    TextView title_viewholder0_undertake;
    final String TAG = "Viewholder0TAG";
    public Viewholder0_Expand_Undertake(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
    }


    public void initData(){
        Log.d(TAG,"initData");
        //mCourseInfo = courseList.get(0);
//        for (int i = 0; i < courseList.size(); i++) {
//            CourseInfo courseInfo = courseList.get(i);
//            for (int j = 0; j < courseList.size(); j++) {
//                ChapterInfo chapterInfo = courseList.get(j);
//                chapterInfo.setChapterIndex(j);
////            }
        //}
    }

    public void initViews(String courseName){
        Log.d(TAG,"initViews");
        mRecyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_viewholder0_undertake);
        title_viewholder0_undertake = itemView.findViewById(R.id.title_viewholder0_undertake);
        title_viewholder0_undertake.setText(courseName);
        final Adapter_ExpandRecyclerview chapterAdapter = new Adapter_ExpandRecyclerview(mCourseInfo);
        mRecyclerView.setAdapter(chapterAdapter);
        chapterAdapter.notifyDataSetChanged();
        chapterAdapter.setOnItemClickListener(new Adapter_ExpandRecyclerview.OnRecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, Adapter_ExpandRecyclerview.ViewName viewName, int chapterIndex, int sectionIndex) {
                //Timber.v("---onClick---"+viewName+", "+chapterIndex+", "+sectionIndex);
                switch (viewName){
                    case CHAPTER_ITEM:
                        if(chapterList.get(chapterIndex).list.size() > 0){
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
    public void initRetrofit(int courseId,String courseName){
        Log.d(TAG,"initRetrofit");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://8.142.65.201:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetRequest getRequest = retrofit.create(GetRequest.class);
        HashMap<String,Integer> map = new HashMap<>();
        map.put("courseId",1);
        String bodyStr = new Gson().toJson(map);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), bodyStr);
        Call<BaseResponseTwo<List<ChapterInfo>>> call = getRequest.getContent3(requestBody);
        call.enqueue(new Callback<BaseResponseTwo<List<ChapterInfo>>>() {
            @Override
            public void onResponse(Call<BaseResponseTwo<List<ChapterInfo>>> call, Response<BaseResponseTwo<List<ChapterInfo>>> response) {
                Log.d(TAG,"onresponsebody:"+response.body()+",errorbody:"+response.errorBody()+",message:"+response.message());
                // Log.d(TAG,"name:"+response.body().get(0).courseId+","+response.body().get(0).name);
                chapterList = response.body().getData();
                initData();
                initViews();
            }

            @Override
            public void onFailure(Call<BaseResponseTwo<List<ChapterInfo>>> call, Throwable t) {
                Log.d(TAG,"error+"+t.toString());
            }
        });
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Log.d(TAG,"111");
//                OkHttpClient okHttpClient = new OkHttpClient();
//                String jsonPost = null;
//                try {
//                    jsonPost = JSONObject.numberToString(1);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                //创建一个RequestBody(参数1：数据类型 参数2传递的json串)
//                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonPost);
//                final Request request = new Request.Builder()
//                        .url("http://8.142.65.201:8080/getContent")
//                        .post(requestBody)
//                        .build();
//                //发送请求获取响应
//                try {
//                    Response response=okHttpClient.newCall(request).execute();
//                    //判断请求是否成功
//                    if(response.isSuccessful()){
//                        //打印服务端返回结果
//                        Log.i(TAG,response.body().string());
//
//                    }else {
//                        Log.d(TAG,response.message()+","+response.toString());
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

//        JSONStringer jsonStringer = null;
//        try {
//
//            jsonStringer = new JSONStringer().object().key("courseId").value(1).endObject();
//        } catch (JSONException e) {
//
//            e.printStackTrace();
//
//        }
//        Call<BaseResponseTwo<Object>> call = getRequest.getContent5(jsonStringer.toString());
//        call.enqueue(new Callback<BaseResponseTwo<Object>>() {
//            @Override
//            public void onResponse(Call<BaseResponseTwo<Object>> call, Response<BaseResponseTwo<Object>> response) {
//                // Log.d(TAG,"onresponsebody:"+response.body()+",errorbody:"+response.errorBody()+",message:"+response.message()+"responsesize:"+response.body().size());
//                // Log.d(TAG,"name:"+response.body().get(0).courseId+","+response.body().get(0).name);
//                //courseList = response.body();
//                try {
//                    Log.d(TAG,"onresponsebody:"+response.body()+",errorbody:"+response.errorBody().string()+",message:"+response.message());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                //Log.d(TAG,"code:"+response.body().getCode());
//////                initData();
////                initViews();
//            }
//
//            @Override
//            public void onFailure(Call<BaseResponseTwo<Object>> call, Throwable t) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Viewholder0_Expand_Undertake that = (Viewholder0_Expand_Undertake) o;
        return Objects.equals(mRecyclerView, that.mRecyclerView) && Objects.equals(mCourseInfo, that.mCourseInfo) && Objects.equals(context, that.context) && Objects.equals(courseList, that.courseList) && Objects.equals(title_viewholder0_undertake, that.title_viewholder0_undertake) && Objects.equals(TAG, that.TAG);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mRecyclerView, mCourseInfo, context, courseList, title_viewholder0_undertake, TAG);
    }
}
//{"target_id":28947472,"user_id":1102297,"recent_user_id":1102297,"project_id":2375801,"local_target_id":"c71688c5-9bba-47e4-b726-6f53ca407d98","local_parent_id":"491cfcf7-1832-44bb-9be9-06240829ebd9","mark":"developing","is_doc":0,"target_type":"api","example_type":"0","name":"获取目录","method":"POST","sort":5,"status":1,"update_dtime":"2022-08-21 21:58:12","create_users":{"create_user":"修普诺斯","update_user":"修普诺斯"},"mock_server_url":"","request":{"url":"localhost:8080/getContent","description":"","body":{"mode":"application/json","parameter":[],"raw":"{\r\n     \"courseId\":\"1\"\r\n     //\"title\":\"ddddd\"\r\n     // \"cover\":\"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimage31.bookschina.com%2F2010%2F20100209%2F4415793.jpg&refer=http%3A%2F%2Fimage31.bookschina.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1652533760&t=ac465d4dde802c6950e030692b5a7dd8\"\r\n}","raw_para":[]},"event":{"pre_script":"","test":""},"auth":{"type":"noauth","kv":{"key":"","value":""},"bearer":{"key":""},"basic":{"username":"","password":""}},"header":{"parameter":[]},"query":{"parameter":[]},"cookie":{"parameter":[]},"resful":{"parameter":[]},"url_var":"localhost:8080/getContent"},"response":{"success":{"raw":"","parameter":[],"expect":{"name":"成功","isDefault":-1,"code":200,"contentType":"json","verifyType":"mock","schema":"","mock":"{}"}},"error":{"raw":"","parameter":[],"expect":{"name":"失败","isDefault":-1,"code":201,"contentType":"json","verifyType":"mock","schema":"","mock":""}}},"markName":"开发中","markColor":"#3A86FF"}
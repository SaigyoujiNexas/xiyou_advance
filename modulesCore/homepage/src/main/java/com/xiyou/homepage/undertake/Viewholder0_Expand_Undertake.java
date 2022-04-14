package com.xiyou.homepage.undertake;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xiyou.advance.modulespublic.common.net.ChapterInfo;
import com.xiyou.advance.modulespublic.common.net.CourseInfo;
import com.xiyou.advance.modulespublic.common.net.GetRequest;
import com.xiyou.homepage.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class Viewholder0_Expand_Undertake extends RecyclerView.ViewHolder {
    RecyclerView mRecyclerView;
    CourseInfo mCourseInfo;
    Context context;
    List<ChapterInfo> contentsList;
    final String TAG = "Viewholder0TAG";
    public Viewholder0_Expand_Undertake(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
        Log.d(TAG,"0");
    }


    public void initData(){
        Log.d(TAG,"1");

        mCourseInfo = new CourseInfo();
        mCourseInfo.name = "假装是课程的名称";
        mCourseInfo.chapterInfos = contentsList;
//        for(int i=0; i< contentsList.size(); i++){
//            ChapterInfo chapterInfo = new ChapterInfo();
//            chapterInfo.name = "假装是课时名称"+(i+1);
//            chapterInfo.chapterIndex = i;
//            if(i==0){
//                for(int j=0; j<2; j++){
//                    SectionInfo sectionInfo = new SectionInfo();
//                    sectionInfo.name = "第"+(j+1)+"节";
//                    sectionInfo.chapterIndex = i;
//                    sectionInfo.sectionIndex = j;
//                    chapterInfo.sectionInfos.add(sectionInfo);
//                }
//            }else if(i==1){
//                for(int j=0; j<3; j++){
//                    SectionInfo sectionInfo = new SectionInfo();
//                    sectionInfo.name = "第"+(j+1)+"节";
//                    sectionInfo.chapterIndex = i;
//                    sectionInfo.sectionIndex = j;
//                    chapterInfo.sectionInfos.add(sectionInfo);
//                }
//            }else{
//                for (int j = 0; j < 4; j++) {
//                    SectionInfo sectionInfo = new SectionInfo();
//                    sectionInfo.name = "第" + (j + 1) + "节";
//                    sectionInfo.chapterIndex = i;
//                    sectionInfo.sectionIndex = j;
//                    chapterInfo.sectionInfos.add(sectionInfo);
//                }
//            }
//            mCourseInfo.chapterInfos.add(chapterInfo);//章节
//        }
    }

    public void initViews(){
        Log.d(TAG,"2");
        mRecyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_viewholder0_undertake);
        final Adapter_ExpandRecyclerview chapterAdapter = new Adapter_ExpandRecyclerview(mCourseInfo);
        mRecyclerView.setAdapter(chapterAdapter);
        chapterAdapter.setOnItemClickListener(new Adapter_ExpandRecyclerview.OnRecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, Adapter_ExpandRecyclerview.ViewName viewName, int chapterIndex, int sectionIndex) {
                //Timber.v("---onClick---"+viewName+", "+chapterIndex+", "+sectionIndex);
                switch (viewName){
                    case CHAPTER_ITEM:
                        if(mCourseInfo.chapterInfos.get(chapterIndex).sectionInfos.size() > 0){
                            Log.v(TAG,"---onClick---just expand or narrow: "+chapterIndex);
                            if(chapterIndex + 1 == mCourseInfo.chapterInfos.size()){
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
    public void initRetrofit(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://8.142.65.201:8080").addConverterFactory(MoshiConverterFactory.create()).build();
        GetRequest getRequest = retrofit.create(GetRequest.class);
        Call<List<ChapterInfo>> call = getRequest.getContents();
        call.enqueue(new Callback<List<ChapterInfo>>() {
            @Override
            public void onResponse(Call<List<ChapterInfo>> call, Response<List<ChapterInfo>> response) {
                Log.d(TAG,"onresponsebody:"+response.body()+",errorbody:"+response.errorBody()+",message:"+response.message()+","+response.body().get(0).getTitle());
                contentsList = response.body();
                for (int i = 0; i < contentsList.size(); i++) {
                   Log.d(TAG,contentsList.get(i).getTitle());
                }
                initData();
                initViews();
            }

            @Override
            public void onFailure(Call<List<ChapterInfo>> call, Throwable t) {
                Log.d(TAG,"error+"+t.toString());
            }
        });

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
}

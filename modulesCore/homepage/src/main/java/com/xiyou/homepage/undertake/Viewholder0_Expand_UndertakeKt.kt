package com.xiyou.homepage.undertake

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.xiyou.advance.modulespublic.common.bean.ChapterInfo
import com.xiyou.advance.modulespublic.common.bean.CourseInfo
import com.xiyou.advance.modulespublic.common.bean.SectionInfo
import com.xiyou.advance.modulespublic.common.net.BaseResponseTwo
import com.xiyou.advance.modulespublic.common.net.GetRequest
import com.xiyou.homepage.R
import com.xiyou.homepage.undertake.Adapter_ExpandRecyclerview.ViewName
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class Viewholder0_Expand_UndertakeKt(itemView: View) : RecyclerView.ViewHolder(itemView) {
    constructor(itemView: View,context: Context) : this(itemView) {
        this.context  = context
    }

    var mRecyclerView: RecyclerView? = null
    var context: Context? = null
    private var courseName: String? = null
    private lateinit var chapterList: List<ChapterInfo>
    var title_viewholder0_undertake: TextView? = null
    val TAG = "Viewholder0TAG"
    var retrofit: Retrofit? = null
    var getRequest: GetRequest? = null
    private var sectionList: List<SectionInfo>? = null
    var mCourseInfo: CourseInfo? = null
    fun initData(sectionList:List<SectionInfo> ) {
        Log.d(TAG, "initData")
        var count = 0
        for (chapterInfo in chapterList!!) {
            chapterInfo.sectionList = sectionList
            chapterInfo.chapterIndex = count++
        }
        //mCourseInfo = courseList.get(0);
//        for (int i = 0; i < courseList.size(); i++) {
//            CourseInfo courseInfo = courseList.get(i);
//            for (int j = 0; j < courseList.size(); j++) {
//                ChapterInfo chapterInfo = courseList.get(j);
//                chapterInfo.setChapterIndex(j);
//            }


    }

    fun initViews(courseInfo: CourseInfo) {
        Log.d(TAG, "initViews")
        mRecyclerView =
            itemView.findViewById<View>(R.id.recycler_viewholder0_undertake) as RecyclerView
        title_viewholder0_undertake = itemView.findViewById(R.id.title_viewholder0_undertake)
        title_viewholder0_undertake!!.setText(courseName)
        courseName = courseInfo.title
        val chapterAdapter = Adapter_ExpandRecyclerview(mCourseInfo, chapterList)
        mRecyclerView!!.adapter = chapterAdapter
        chapterAdapter.notifyDataSetChanged()
        chapterAdapter.setOnItemClickListener { view, viewName, chapterIndex, sectionIndex ->
            //Timber.v("---onClick---"+viewName+", "+chapterIndex+", "+sectionIndex);
            when (viewName) {
                ViewName.CHAPTER_ITEM -> if (chapterList!![chapterIndex].sectionList.size > 0) {
                    Log.v(TAG, "---onClick---just expand or narrow: $chapterIndex")
                    if (chapterIndex + 1 == chapterList!!.size) {
                        //如果是最后一个，则滚动到展开的最后一个item
                        mRecyclerView!!.smoothScrollToPosition(chapterAdapter.itemCount)
                        Log.v(TAG, "---onClick---scroll to bottom")
                    }
                } else {
                    onClickChapter(chapterIndex)
                }
                ViewName.CHAPTER_ITEM_PRACTISE -> onClickPractise(chapterIndex)
                ViewName.SECTION_ITEM -> onClickSection(chapterIndex, sectionIndex)
            }
        }

//        //以下是对布局进行控制，让课时占据一行，小节每四个占据一行，结果就是相当于一个ListView嵌套GridView的效果。
//        final GridLayoutManager manager = new GridLayoutManager(context, 4);
//        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return chapterAdapter.getItemViewType(position) == Adapter_ExpandRecyclerview.VIEW_TYPE_CHAPTER ? 4 : 1;
//            }
//        });

//        //以下是对布局进行控制，让课时占据一行，小节每四个占据一行，结果就是相当于一个ListView嵌套GridView的效果。
//        final GridLayoutManager manager = new GridLayoutManager(context, 4);
//        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return chapterAdapter.getItemViewType(position) == Adapter_ExpandRecyclerview.VIEW_TYPE_CHAPTER ? 4 : 1;
//            }
//        });
        val linearLayoutManager = LinearLayoutManager(itemView.context)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        mRecyclerView!!.layoutManager = linearLayoutManager
    }

    fun initChapterRetrofit(courseId: Int, courseName: String?, courseInfo: CourseInfo) {
        mCourseInfo = courseInfo
        Log.d(TAG, "initRetrofit")
        retrofit = Retrofit.Builder()
            .baseUrl("http://8.142.65.201:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        getRequest = retrofit!!.create(GetRequest::class.java)
        val map = HashMap<String, Int>()
        map["courseId"] = 1
        val bodyStr = Gson().toJson(map)
        val requestBody =
            RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), bodyStr)
        val call = getRequest!!.getContent(requestBody)
        call.enqueue(object : Callback<BaseResponseTwo<List<ChapterInfo?>>> {
            override fun onResponse(
                call: Call<BaseResponseTwo<List<ChapterInfo?>>>,
                response: Response<BaseResponseTwo<List<ChapterInfo?>>>
            ) {
                Log.d(
                    TAG,
                    "onresponsebody:" + response.body() + ",errorbody:" + response.errorBody() + ",message:" + response.message()
                )
                // Log.d(TAG,"name:"+response.body().get(0).courseId+","+response.body().get(0).name);
                chapterList = response.body()!!.data as List<ChapterInfo>
                initSectionRetrofit()
            }

            override fun onFailure(call: Call<BaseResponseTwo<List<ChapterInfo?>>>, t: Throwable) {
                Log.d(TAG, "error+$t")
            }
        })
    }

    fun initSectionRetrofit() {
        val map: MutableMap<String, Int> = HashMap()
        map["contentId"] = 2
        val s = Gson().toJson(map)
        val requestBody: RequestBody =
            s.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        val call = getRequest!!.getSection(requestBody)
        call.enqueue(object : Callback<BaseResponseTwo<List<SectionInfo>>> {
            override fun onResponse(
                call: Call<BaseResponseTwo<List<SectionInfo>>>,
                response: Response<BaseResponseTwo<List<SectionInfo>>>
            ) {
                Log.d(
                    TAG,
                    "onresponsebody:" + response.body() + ",errorbody:" + response.errorBody() + ",message:" + response.message()
                )
                sectionList = response.body()!!.data
                initData(sectionList!!)
                initViews(mCourseInfo!!)
                Log.d(TAG, response.body().toString())
                Log.d(TAG, response.body()!!.data[0].toString())
            }

            override fun onFailure(call: Call<BaseResponseTwo<List<SectionInfo>>>, t: Throwable) {
                Log.d(TAG, "error+$t")
            }
        })
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

    private fun onClickChapter(chapterIndex: Int) {
        Log.v(TAG, "---onClick---play chapter: $chapterIndex")
        //ToastUtil.showToast(ExpandRecyclerViewActivity.this, "播放"+chapterIndex);
    }

    private fun onClickSection(chapterIndex: Int, sectionIndex: Int) {
        Log.v(TAG, "---onClick---play---section: $chapterIndex, $sectionIndex")
        //ToastUtil.showToast(ExpandRecyclerViewActivity.this, "播放"+chapterIndex+", "+sectionIndex);
    }

    private fun onClickPractise(chapterIndex: Int) {
        Log.v(TAG, "---onClick---practise: $chapterIndex")
    }

}
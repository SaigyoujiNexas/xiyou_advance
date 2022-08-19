package com.xiyou.homepage.undertake

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xiyou.advance.modulespublic.common.net.CourseInfo
import com.xiyou.advance.modulespublic.common.net.GetRequest
import com.xiyou.homepage.R
import com.xiyou.homepage.undertake.Adapter_ExpandRecyclerview.ViewName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

class Viewholder0_Expand_UndertakeKt(itemView: View) : RecyclerView.ViewHolder(itemView) {
    constructor(itemView: View,context: Context) : this(itemView) {
        this.context  = context
    }
    var mRecyclerView: RecyclerView? = null
    var mCourseInfo: CourseInfo? = null
    var context: Context? = null
    private var courseList: List<CourseInfo>? = null
    var title_viewholder0_undertake: TextView? = null
    val TAG = "Viewholder0TAG"

    fun initData() {
        Log.d(TAG, "initData")
        mCourseInfo = courseList!![0]
        for (i in courseList!!.indices) {
            val courseInfo = courseList!![i]
            //            for (int j = 0; j < list.size(); j++) {
//                ChapterInfo chapterInfo = list.get(j);
//                chapterInfo.setChapterIndex(j);
//            }
        }
    }

    fun initViews() {
        Log.d(TAG, "initViews")
        mRecyclerView =
            itemView.findViewById<View>(R.id.recycler_viewholder0_undertake) as RecyclerView
        title_viewholder0_undertake = itemView.findViewById(R.id.title_viewholder0_undertake)
        title_viewholder0_undertake!!.setText(courseList!![0].title)
        val chapterAdapter = Adapter_ExpandRecyclerview(mCourseInfo)
        mRecyclerView!!.adapter = chapterAdapter
        chapterAdapter.notifyDataSetChanged()
        chapterAdapter.setOnItemClickListener { view, viewName, chapterIndex, sectionIndex ->
            //Timber.v("---onClick---"+viewName+", "+chapterIndex+", "+sectionIndex);
            when (viewName) {
                ViewName.CHAPTER_ITEM -> {//                        if(list.get(chapterIndex).list.size() > 0){
//                            Log.v(TAG,"---onClick---just expand or narrow: "+chapterIndex);
//                            if(chapterIndex + 1 == list.size()){
//                                //如果是最后一个，则滚动到展开的最后一个item
//                                mRecyclerView.smoothScrollToPosition(chapterAdapter.getItemCount());
//                                Log.v(TAG,"---onClick---scroll to bottom");
//                            }
//                        }else{
//                            onClickChapter(chapterIndex);
//                        }
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
        val linearLayoutManager = LinearLayoutManager(itemView.context)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        mRecyclerView!!.layoutManager = linearLayoutManager
    }

    fun initRetrofit() {
        Log.d(TAG, "initRetrofit")
        val retrofit = Retrofit.Builder().baseUrl("http://8.142.65.201:8080")
            .addConverterFactory(MoshiConverterFactory.create()).build()
        val getRequest = retrofit.create(GetRequest::class.java)
        val call = getRequest.courses
        call.enqueue(object : Callback<List<CourseInfo>> {
            override fun onResponse(
                call: Call<List<CourseInfo>>,
                response: Response<List<CourseInfo>>
            ) {
                Log.d(
                    TAG,
                    "onresponsebody:" + response.body() + ",errorbody:" + response.errorBody() + ",message:" + response.message() + "responsesize:" + response.body()!!.size
                )
                Log.d(TAG, "name" + response.body()!![0].title)
                courseList = response.body()
                initData()
                initViews()
            }

            override fun onFailure(call: Call<List<CourseInfo>>, t: Throwable) {
                Log.d(TAG, "error+$t")
            }
        })
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



    override fun hashCode(): Int {
        return Objects.hash(
            mRecyclerView,
            mCourseInfo,
            context,
            courseList,
            title_viewholder0_undertake,
            TAG
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Viewholder0_Expand_UndertakeKt

        if (mRecyclerView != other.mRecyclerView) return false
        if (mCourseInfo != other.mCourseInfo) return false
        if (context != other.context) return false
        if (courseList != other.courseList) return false
        if (title_viewholder0_undertake != other.title_viewholder0_undertake) return false
        if (TAG != other.TAG) return false

        return true
    }
}
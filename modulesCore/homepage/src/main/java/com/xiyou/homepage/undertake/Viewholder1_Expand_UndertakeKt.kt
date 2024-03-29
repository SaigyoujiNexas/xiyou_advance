package com.xiyou.homepage.undertake

import android.content.Context
import android.icu.number.IntegerWidth
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textview.MaterialTextView
import com.google.gson.Gson
import com.xiyou.advance.modulespublic.common.constant.NetConstant
import com.xiyou.advance.modulespublic.common.net.BaseResponse
import com.xiyou.advance.modulespublic.common.net.Comment_Course
import com.xiyou.advance.modulespublic.common.net.GetRequest
import com.xiyou.advance.modulespublic.common.utils.ToastUtil
import com.xiyou.homepage.R
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class Viewholder1_Expand_UndertakeKt(itemView : View) : RecyclerView.ViewHolder(itemView) {
    constructor(itemView: View,context: Context) : this(itemView) {
        this.context  = context

        mRecyclerView = itemView.findViewById(R.id.recycler_viewholder1_undertake)

        sendtext = itemView.findViewById(R.id.sendtext_viewholder1_undertake)
    }
    var mRecyclerView: RecyclerView? = null
    var context: Context? = null
    var list: List<Comment_Course> = ArrayList()
    var editText_comment: EditText? = null
    var sendtext: MaterialTextView? = null
    var commentEdit: FloatingActionButton? = null
    var cardView: CardView? = null

    companion object{
        val TAG = "Viewholder1TAG"
    }

    fun initView() {
        //Comment_Course comment_course = new Comment_Course();
//        comment_course.setComment("This is comment!!");
//        comment_course.setSendTime("4-12");
//        comment_course.setUserImg(R.drawable.img_round_1);
//        comment_course.setUserName("UserName");
//        for(int i = 0;i<20;i++)     list.add(comment_course);
        val adapter_viewholder1_undertake = Adapter_Viewholder1_Undertake(list)
        adapter_viewholder1_undertake.notifyDataSetChanged()
        val linearLayoutManager = LinearLayoutManager(itemView.context)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        mRecyclerView!!.adapter = adapter_viewholder1_undertake
        mRecyclerView!!.layoutManager = linearLayoutManager
    }

    fun initRetrofit() {
        Log.d(TAG, "initRetrofit")
        val getRequest = NetConstant.retrofit.create(GetRequest::class.java)
        val map = mapOf("courseId" to 1)
        val requestBody = Gson().toJson(map).toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        val call = getRequest.getComments(requestBody)
        call.enqueue(object : Callback<BaseResponse<List<Comment_Course>>> {
            override fun onResponse(
                call: Call<BaseResponse<List<Comment_Course>>>,
                response: Response<BaseResponse<List<Comment_Course>>>
            ) {
                Log.d(
                    TAG,
                    "onresponsebody:" + response.body() + ",errorbody:" + response.errorBody() + ",message:" + response.message()
                )
                if(response.body() != null) {
                    list = response.body()!!.data
                    initView();
                }else {
                    ToastUtil.showToast("请求失败！")
                }
            }

            override fun onFailure(call: Call<BaseResponse<List<Comment_Course>>>, t: Throwable) {
                Log.d(TAG, "error+$t")
            }
        })
    }
}
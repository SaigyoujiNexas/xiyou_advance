package com.xiyou.community.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.xiyou.advance.modulespublic.common.net.BaseResponse
import com.xiyou.advance.modulespublic.common.net.BaseResponseTwo
import com.xiyou.advance.modulespublic.common.utils.DialogUtil
import com.xiyou.advance.modulespublic.common.utils.ToastUtil
import com.xiyou.community.data.Answer
import com.xiyou.community.data.QuestionAnswer
import com.xiyou.community.data.QuestionCard
import com.xiyou.community.data.QuestionRelease
import com.xiyou.community.repository.CommunityRepository
import com.xiyou.community.view.ui.CommunityFragment2
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

//@HiltViewModel//@Inject
class QuestionInfoViewModel ()
    : ViewModel() {

    val questionList = MutableLiveData(listOf<QuestionCard>())
    val answerList = MutableLiveData(listOf<QuestionAnswer>())

    val questions: LiveData<List<QuestionCard>>
        get()=questionList

    val answers : LiveData<List<QuestionAnswer>>
        get()=answerList

    var title: String? = ""
    var content: String? = ""
    var repository : CommunityRepository ?= null


    fun getAllQuestion2(onSuccess:() -> Unit = {}, onFailure:() -> Unit = {}) = viewModelScope.launch {
        val call: Call<BaseResponseTwo<List<QuestionCard>>> = repository!!.getAllQuestionList2()
        call.enqueue(object : Callback<BaseResponseTwo<List<QuestionCard>>>{
            override fun onResponse(
                call: Call<BaseResponseTwo<List<QuestionCard>>>,
                response: Response<BaseResponseTwo<List<QuestionCard>>>
            ) {
                //TODO("Not yet implemented")
                Log.d(CommunityFragment2.TAG,"body: ${response.body()} errorbody:${response.errorBody()} message${response.message()}");
                questionList.postValue(response.body()!!.data)          //连接了list和Adapter
            }

            override fun onFailure(call: Call<BaseResponseTwo<List<QuestionCard>>>, t: Throwable) {
                //TODO("Not yet implemented")
                Log.d(CommunityFragment2.TAG,"${t.toString()}")
            }
        })
    }

    fun getAnswer( onSuccess:() -> Unit = {}, onFailure:() -> Unit = {}) = viewModelScope.launch {
        val map:Map<String,Int> = mapOf("id" to 1)
        var requestBody = Gson().toJson(map).toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        var call  = repository!!.getAllAnswer(requestBody)
        call.enqueue(object : Callback<BaseResponseTwo<List<QuestionAnswer>>>{
            override fun onResponse(
                call: Call<BaseResponseTwo<List<QuestionAnswer>>>,
                response: Response<BaseResponseTwo<List<QuestionAnswer>>>
            ) {
                Log.d(CommunityFragment2.TAG,"body: ${response.body()} errorbody:${response.errorBody()} message${response.message()}");
                answerList.postValue(response.body()!!.data)
                //TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<BaseResponseTwo<List<QuestionAnswer>>>, t: Throwable) {
                Log.d(CommunityFragment2.TAG,"${t.toString()}")
                //TODO("Not yet implemented")
            }

        })
//        call.enqueue(object : Callback<BaseResponseTwo<List<QuestionCard>>>{
//            override fun onResponse(
//                call: Call<BaseResponseTwo<List<QuestionCard>>>,
//                response: Response<BaseResponseTwo<List<QuestionCard>>>
//            ) {
//                //TODO("Not yet implemented")
//                Log.d(CommunityFragment2.TAG,"body: ${response.body()} errorbody:${response.errorBody()} message${response.message()}");
//                list.postValue(response.body()!!.data)          //连接了list和Adapter
//            }
//
//            override fun onFailure(call: Call<BaseResponseTwo<List<QuestionCard>>>, t: Throwable) {
//                //TODO("Not yet implemented")
//                Log.d(CommunityFragment2.TAG,"${t.toString()}")
//            }
//        })
    }

    fun releaseQuestion(title: String, content: String, onSuccess: () -> Unit = {}) = viewModelScope.launch {
        val question = QuestionRelease(title = title, content = content)
        var res: BaseResponse<String?>?
        try{
            res = repository!!.releaseQuestion(question)
        }catch(e: Throwable)
        {
            res = BaseResponse(data = e.localizedMessage, code = 200, message = e.localizedMessage)
        }
        when(res!!.isSuccess)
        {
            true ->  onSuccess.invoke()
            false -> ToastUtil.showToast(res.msg)
        }
    }

    fun releaseAnswer(answer: String, onSuccess: () -> Unit) = viewModelScope.launch {
        val ans = Answer(answer)
        var res: BaseResponse<String?>?
        try{
            res = repository!!.releaseAnswer(ans)
        }catch (e: Throwable)
        {
            res = BaseResponse(data = e.localizedMessage, code = 200, message = e.localizedMessage)
        }
        when(res!!.isSuccess)
        {
            true -> onSuccess.invoke()
            false -> ToastUtil.showToast(res.msg)
        }
    }
//    fun getAllQuestion(onSuccess:() -> Unit = {}, onFailure:() -> Unit = {}) = viewModelScope.launch {
//        val res: List<QuestionCard>
//        try{
//            res = repository!!.getAllQuestionList()
//            Log.d(CommunityFragment2.TAG,res.toString());
//            list.postValue(res)
//            onSuccess.invoke()
//        }catch (e: Throwable)
//        {
//            ToastUtil.showToast(e.localizedMessage+"!"+e.toString())
//            onFailure.invoke()
//        }
//    }
}
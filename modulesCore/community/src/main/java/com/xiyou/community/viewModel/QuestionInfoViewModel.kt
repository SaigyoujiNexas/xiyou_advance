package com.xiyou.community.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xiyou.advance.modulespublic.common.net.BaseResponse
import com.xiyou.advance.modulespublic.common.utils.DialogUtil
import com.xiyou.advance.modulespublic.common.utils.ToastUtil
import com.xiyou.community.data.Answer
import com.xiyou.community.data.QuestionAnswer
import com.xiyou.community.data.QuestionCard
import com.xiyou.community.data.QuestionRelease
import com.xiyou.community.repository.CommunityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel//@Inject
class QuestionInfoViewModel ()
    : ViewModel() {

    val list = MutableLiveData(listOf<QuestionCard>())
    val questions: LiveData<List<QuestionCard>>
        get()=list
    var title: String? = ""
    var content: String? = ""
    var repository : CommunityRepository ?= null

    fun getAllQuestion(onSuccess:() -> Unit = {}, onFailure:() -> Unit = {}) = viewModelScope.launch {
        val res: List<QuestionCard>
        try{
            res = repository!!.getAllQuestionList()
            list.postValue(res)
            onSuccess.invoke()
        }catch (e: Throwable)
        {
            ToastUtil.showToast(e.localizedMessage+"!"+e.toString())
            onFailure.invoke()
        }
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
}
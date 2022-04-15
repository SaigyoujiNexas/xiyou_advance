package com.xiyou.homepage.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.play.core.tasks.OnFailureListener
import com.xiyou.advance.modulespublic.common.net.ChapterInfo
import com.xiyou.advance.modulespublic.common.net.CourseInfo
import com.xiyou.advance.modulespublic.common.net.GetService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.ArrayList
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel
@Inject constructor(val getService: GetService): ViewModel(){

    private var contentsLiveData : MutableLiveData<List<ChapterInfo?>> = MutableLiveData(ArrayList())
    val contents: LiveData<List<ChapterInfo?>>
    get() = contentsLiveData

    private var coursesLiveData: MutableLiveData<List<CourseInfo?>> = MutableLiveData(ArrayList())

    val courses:LiveData<List<CourseInfo?>>
    get() = coursesLiveData

    fun getContents(onSuccess:() -> Unit = {}, onFailure: () -> Unit = {}) = viewModelScope.launch {
        val res: List<ChapterInfo?>?
        try{
            res= getService.getContents()?.asList()
            res?.let{contentsLiveData.postValue(res)
                onSuccess.invoke()
            }
        }catch(e: Throwable){
            onFailure.invoke()
        }
    }
    fun getCourses(onSuccess:() -> Unit = {}, onFailure:(t: Throwable) -> Unit = {}) = viewModelScope.launch {
        val res: List<CourseInfo?>?
        try{
            res = getService.getCourses()?.asList()
            res?.let {
                coursesLiveData.postValue(res)
                onSuccess.invoke()
            }
        }catch (e: Throwable)
        {
            onFailure.invoke(e)
        }
    }

}
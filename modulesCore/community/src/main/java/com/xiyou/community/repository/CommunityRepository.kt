package com.xiyou.community.repository

import com.xiyou.advance.modulespublic.common.net.BaseResponse
import com.xiyou.advance.modulespublic.common.net.BaseResponseTwo
import com.xiyou.community.data.Answer
import com.xiyou.community.data.QuestionAnswer
import com.xiyou.community.data.QuestionCard
import com.xiyou.community.data.QuestionRelease
import com.xiyou.community.net.CommunityService
import okhttp3.RequestBody
import retrofit2.Call

class CommunityRepository(val requestService: CommunityService){

    suspend fun getAllQuestionList(): List<QuestionCard>{
        return requestService.getAllQuestionList().toList()
    }

    fun getAllQuestionList2(): Call<BaseResponseTwo<List<QuestionCard>>>{
        return requestService.getAllQuestionList2()
    }
    fun getAllAnswer(requestServiceBody: RequestBody) : Call<BaseResponseTwo<List<QuestionAnswer>>>{
        return requestService.getAnswers(requestServiceBody);
    }
    suspend fun releaseQuestion(question: QuestionRelease): BaseResponse<String?>{
        return requestService.releaseQuestion(question)
    }
//    suspend fun releaseQuestion2(question: QuestionRelease): BaseResponse<String?>{
//        requestService.releaseQuestion2()
//        return requestService.releaseQuestion2(question)
//    }

    suspend fun releaseAnswer(answer: Answer): BaseResponse<String?>{
        return requestService.releaseAnswer(answer)
    }
//    suspend fun releaseAnswer2(answer: Answer): BaseResponse<String?>{
//        return requestService.releaseAnswer2(answer)
//    }
}
package com.xiyou.community.repository

import com.xiyou.advance.modulespublic.common.net.BaseResponse
import com.xiyou.community.data.Answer
import com.xiyou.community.data.QuestionCard
import com.xiyou.community.data.QuestionRelease
import com.xiyou.community.net.CommunityService

class CommunityRepository(val service: CommunityService){
    suspend fun getAllQuestionList(): BaseResponse<List<QuestionCard>>{
        return service.getAllQuestionList()
    }
    suspend fun releaseQuestion(question: QuestionRelease): BaseResponse<String?>{
        return service.releaseQuestion(question)

    }
    suspend fun releaseAnswer(answer: Answer): BaseResponse<String?>{
        return service.releaseAnswer(answer)
    }
}
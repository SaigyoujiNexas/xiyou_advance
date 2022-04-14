package com.xiyou.community.repository

import com.xiyou.advance.modulespublic.common.net.BaseResponse
import com.xiyou.community.data.ui.QuestionCard
import com.xiyou.community.net.CommunityService

class CommunityRepository(val service: CommunityService){
    suspend fun getAllQuestionList(): BaseResponse<List<QuestionCard>>{
        return service.getAllQuestionList()
    }
}
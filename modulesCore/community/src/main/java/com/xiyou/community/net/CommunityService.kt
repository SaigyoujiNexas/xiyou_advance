package com.xiyou.community.net

import com.xiyou.advance.modulespublic.common.net.BaseResponse
import com.xiyou.community.data.ui.QuestionCard
import retrofit2.http.GET

interface CommunityService {
    @GET("")
    suspend fun getAllQuestionList() : BaseResponse<List<QuestionCard>>
}
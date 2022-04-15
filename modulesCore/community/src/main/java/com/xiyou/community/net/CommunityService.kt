package com.xiyou.community.net

import com.xiyou.advance.modulespublic.common.net.BaseResponse
import com.xiyou.community.data.Answer
import com.xiyou.community.data.QuestionCard
import com.xiyou.community.data.QuestionRelease
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CommunityService {
    @GET("")
    suspend fun getAllQuestionList() : BaseResponse<List<QuestionCard>>

    @POST("")
    suspend fun releaseQuestion(@Body request: QuestionRelease): BaseResponse<String?>

    @POST("")
    suspend fun releaseAnswer(@Body request: Answer) : BaseResponse<String?>
}
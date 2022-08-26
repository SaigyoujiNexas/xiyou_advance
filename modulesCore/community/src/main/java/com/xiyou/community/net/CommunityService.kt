package com.xiyou.community.net

import com.xiyou.advance.modulespublic.common.net.BaseResponse
import com.xiyou.advance.modulespublic.common.net.BaseResponseTwo
import com.xiyou.community.data.Answer
import com.xiyou.community.data.QuestionAnswer
import com.xiyou.community.data.QuestionCard
import com.xiyou.community.data.QuestionRelease
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CommunityService {
    @POST("getQuestion")
    suspend fun getAllQuestionList() : Array<QuestionCard>

    @POST("getQuestion")
    fun getAllQuestionList2() : Call<BaseResponseTwo<List<QuestionCard>>>

    @POST("getAnswer")
    fun getAnswers(@Body requestBody: RequestBody):Call<BaseResponseTwo<List<QuestionAnswer>>>

    @POST("")
    suspend fun releaseQuestion(@Body request: QuestionRelease): BaseResponse<String?>
    @POST("")
    fun releaseQuestion2(@Body request: QuestionRelease): Call<BaseResponse<String?>>

    @POST("")
    suspend fun releaseAnswer(@Body request: Answer) : BaseResponse<String?>
    @POST("")
    fun releaseAnswer2(@Body request: Answer) : Call<BaseResponse<String?>>
}
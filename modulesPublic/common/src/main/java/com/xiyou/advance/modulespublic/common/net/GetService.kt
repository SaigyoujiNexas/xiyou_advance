package com.xiyou.advance.modulespublic.common.net

import retrofit2.Call
import retrofit2.http.GET

interface GetService {
    @GET("getContents")
    suspend fun getContents(): Array<ChapterInfo?>?

    @GET("getRecommands")
    suspend fun getCourses(): Array<CourseInfo?>?
}
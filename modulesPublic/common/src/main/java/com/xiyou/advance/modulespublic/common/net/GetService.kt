package com.xiyou.advance.modulespublic.common.net

import retrofit2.http.GET

interface GetService {
    @GET("getContents")
    suspend fun getContents(): Array<com.xiyou.advance.modulespublic.common.bean.ChapterInfo?>?

    @GET("getRecommands")
    suspend fun getCourses(): Array<com.xiyou.advance.modulespublic.common.bean.CourseInfo?>?
}
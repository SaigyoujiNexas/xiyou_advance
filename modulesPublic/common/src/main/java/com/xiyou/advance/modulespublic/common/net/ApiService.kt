package com.xiyou.advance.modulespublic.common.net

import com.xiyou.advance.modulespublic.common.net.BaseResponse
import com.xiyou.advance.modulespublic.common.net.ApiService
import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {
    @GET("/INDEX.TXT")
    fun test(): Observable<BaseResponse<String?>?>?

    @Multipart
    @POST(SET_USER_INFO)
    fun setUserInfo(
        @Header("token") token: String?,
        @Part head: Part?,
        @Query("name") name: String?,
        @Query("gender") gender: String?,
        @Query("birthday") birthday: String?,
        @Query("height") height: String?,
        @Query("weight") weight: String?
    ): Observable<BaseResponse<String?>?>?

    companion object {
        const val SET_USER_INFO = "set/user_info"
    }
}
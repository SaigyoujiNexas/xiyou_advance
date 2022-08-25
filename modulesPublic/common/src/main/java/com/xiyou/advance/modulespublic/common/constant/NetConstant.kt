package com.xiyou.advance.modulespublic.common.constant

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetConstant {
    const val BaseUrl = "http://8.142.65.201:8080/"
    val retrofit = Retrofit.Builder()
        .baseUrl(BaseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}
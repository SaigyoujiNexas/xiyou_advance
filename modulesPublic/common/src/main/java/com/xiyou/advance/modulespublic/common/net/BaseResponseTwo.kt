package com.xiyou.advance.modulespublic.common.net

import com.xiyou.advance.modulesbase.libbase.net.response.NetResponse

data class BaseResponseTwo<T>(private val code: Int, private val  msg: String,private val data: T):NetResponse<T> {
    override fun isSuccess(): Boolean {
        return code != 200
    }

    override fun getData() = data

    override fun getMsg() = msg

    override fun getCode() = code.toString()
}
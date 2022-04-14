package com.xiyou.advance.modulespublic.common.net

import com.xiyou.advance.modulesbase.libbase.net.response.NetResponse

data class BaseResponse<T>(private val data: T, private val code: Int, private val message: String) : NetResponse<T> {
    override fun isSuccess(): Boolean {
        return code != 200
    }

    override fun getData() = data

    override fun getMsg() =message

    override fun getCode() = code.toString()
}
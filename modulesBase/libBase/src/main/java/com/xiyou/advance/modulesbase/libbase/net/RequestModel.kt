package com.xiyou.advance.modulesbase.libbase.net

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.xiyou.advance.modulesbase.libbase.net.response.NetCallback
import com.xiyou.advance.modulesbase.libbase.net.response.NetResponse
import io.reactivex.Observable

private const val TAG = "RequestModel"
class RequestModel{
    companion object{
        @JvmStatic
        fun <T> request(
            o: Observable<NetResponse<T>>, lifecycleOwner: LifecycleOwner,
            callback: NetCallback<T>
        ) {
            o.run {
                compose(ResponseTransformer obtain lifecycleOwner)
                        .subscribe(callback::onSuccess, callback::onFailure)
            }
        }

    }
}
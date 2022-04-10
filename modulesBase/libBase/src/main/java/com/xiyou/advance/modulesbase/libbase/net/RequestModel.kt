package com.xiyou.advance.modulesbase.libbase.net

import androidx.lifecycle.LifecycleOwner
import com.xiyou.advance.modulesbase.libbase.net.response.NetCallback
import com.xiyou.advance.modulesbase.libbase.net.response.NetResponse
import io.reactivex.Observable

private const val TAG = "RequestModel"
class RequestModel{
    companion object{
        fun <T> request(o : Observable<out NetResponse<T>>, lifecycleOwner: LifecycleOwner,
                        callback: NetCallback<T>
        ) {
            o.run {
                compose(ResponseTransformer obtain lifecycleOwner)
                        .subscribe(callback::onSuccess, callback::onFailure)
            }
        }

    }
}
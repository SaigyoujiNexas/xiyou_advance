//package com.xiyou.homepage.undertake
//
//import androidx.lifecycle.ViewModel
//import com.xiyou.advance.modulesbase.libbase.net.RequestModel
//import com.xiyou.advance.modulespublic.common.net.GetRequest
//import dagger.hilt.android.lifecycle.HiltViewModel
//import retrofit2.Retrofit
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
//import retrofit2.converter.moshi.MoshiConverterFactory
//import retrofit2.create
//import javax.inject.Inject
//
//class NetViewModel : ViewModel() {
//        val retrofit = Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .addConverterFactory(MoshiConverterFactory.create()).build()
//    val service = retrofit.create(GetRequest::class.java)
//
//    suspend fun get(onSuccess: () -> Unit=  {}, onFailure: () -> Unit = {})
//    {
//        try{
//            val get = service.chapterName2
//
//        }
//    }
//}
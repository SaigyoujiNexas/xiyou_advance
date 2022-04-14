package com.xiyou.advance.modulesbase.libbase.net.di

import com.xiyou.advance.modulesbase.libbase.util.Logger.Companion.i
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import com.xiyou.advance.modulesbase.libbase.util.PropertiesUtil
import dagger.Module
import dagger.Provides
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetWorkModule {
    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient?): Retrofit {
        val baseUrl = PropertiesUtil.props.getProperty("baseUrl")
        i("Retrofit", "getbaseUrl: $baseUrl")
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client!!)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .connectTimeout(2, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(2, TimeUnit.SECONDS)
            .build()
    }
}
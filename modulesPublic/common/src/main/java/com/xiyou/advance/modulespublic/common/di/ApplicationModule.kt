package com.xiyou.advance.modulespublic.common.di

import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import com.xiyou.advance.modulespublic.common.net.ApiService
import com.xiyou.advance.modulespublic.common.net.GetService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
    @Provides
    @Singleton
    fun provideGetService(retrofit: Retrofit): GetService{
        return retrofit.create(GetService::class.java)
    }
}
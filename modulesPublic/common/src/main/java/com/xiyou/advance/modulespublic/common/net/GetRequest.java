package com.xiyou.advance.modulespublic.common.net;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetRequest {
    @GET("getNames")
    Call<BaseResponse<List<String>>> getChapterName();
    @GET("getNames")
    Observable<List<String>> getChapterName2();
    @GET("getNames")
    Call<String> getChapterName3();
    @GET("getNames")
    Call<BaseResponse<String>> getChapterName4();
    @GET("getContents")
    Call<List<ChapterInfo>> getContents();

}

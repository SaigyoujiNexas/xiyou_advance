package com.xiyou.advance.modulespublic.common.net;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetRequest {
    @GET("/getContents")
    Call<List<ChapterInfo>> getContents();
    @GET("/getRecommands")
    Call<List<CourseInfo>> getCourses();
}

package com.xiyou.advance.modulespublic.common.net;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetRequest {
    @GET("/getSection")
    Call<List<ChapterInfo>> getSection();
    @GET("/getRecommand")
    Call<List<CourseInfo>> getCourses();
    @GET("/getRecommand")
    Call<List<Comment_Course>> getComments();
}

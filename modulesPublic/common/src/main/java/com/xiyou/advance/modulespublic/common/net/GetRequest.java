package com.xiyou.advance.modulespublic.common.net;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetRequest {
//    @GET("/getSection")
//    Call<List<ChapterInfo>> getSection();
    @GET("/getRecommand")
    Call<BaseResponse<List<CourseInfo>>> getCourses();
    @GET("/getRecommand")
    Call<List<Comment_Course>> getComments();
    @GET("/getContent" )
    Call<BaseResponseTwo<List<ChapterInfo1>>> getContent(@Query("courseId") String courseId);
}

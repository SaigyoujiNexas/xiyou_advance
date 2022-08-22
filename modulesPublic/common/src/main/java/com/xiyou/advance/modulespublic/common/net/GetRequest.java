package com.xiyou.advance.modulespublic.common.net;

import com.xiyou.advance.modulespublic.common.bean.ChapterInfo;
import com.xiyou.advance.modulespublic.common.bean.CourseInfo;
import com.xiyou.advance.modulespublic.common.bean.SectionInfo;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface GetRequest {
    @GET("/getSection")
    Call<BaseResponseTwo<List<SectionInfo>>> getSection();
    @POST("/getRecommand")
    Call<BaseResponse<List<CourseInfo>>> getCourses();
    @POST("/getRecommand")
    Call<List<Comment_Course>> getComments();

    @POST("getContent" )
    @Headers("Content-Type:application/json;charset=utf-8")
    Call<BaseResponseTwo<List<ChapterInfo>>> getContent3(@Body RequestBody body);

}

package com.xiyou.advance.modulespublic.common.net;

import com.xiyou.advance.modulespublic.common.bean.ChapterInfo;
import com.xiyou.advance.modulespublic.common.bean.CourseInfo;
import com.xiyou.advance.modulespublic.common.bean.SectionInfo;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface GetRequest {
    @POST("getSection")
    Call<BaseResponseTwo<List<SectionInfo>>> getSection(@Body RequestBody body);
    @POST("/getRecommand")
    Call<BaseResponse<List<CourseInfo>>> getCourses();

    @POST("getComments")
    Call<BaseResponse<List<Comment_Course>>> getComments(@Body RequestBody body);

    @POST("getContent" )
    @Headers("Content-Type:application/json;charset=utf-8")
    Call<BaseResponseTwo<List<ChapterInfo>>> getContent(@Body RequestBody body);

}

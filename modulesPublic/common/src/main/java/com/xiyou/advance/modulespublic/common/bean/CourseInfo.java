package com.xiyou.advance.modulespublic.common.bean;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class CourseInfo extends BaseInfo implements Parcelable {
    public int courseId;
    public String title;
    public String cover;

    protected CourseInfo(Parcel in) {
        courseId = in.readInt();
        title = in.readString();
        cover = in.readString();
    }

    public static final Creator<CourseInfo> CREATOR = new Creator<CourseInfo>() {
        @Override
        public CourseInfo createFromParcel(Parcel in) {
            return new CourseInfo(in);
        }

        @Override
        public CourseInfo[] newArray(int size) {
            return new CourseInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(courseId);
        dest.writeString(title);
        dest.writeString(cover);
    }
}

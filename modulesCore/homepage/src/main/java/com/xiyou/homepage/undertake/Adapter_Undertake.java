package com.xiyou.homepage.undertake;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xiyou.advance.modulespublic.common.bean.CourseInfo;
import com.xiyou.homepage.R;

import java.util.List;

public class Adapter_Undertake extends RecyclerView.Adapter{
    List<CourseInfo> list;
    int courseId;
    String courseName;
    CourseInfo courseInfo;
    public Adapter_Undertake(List<CourseInfo> list, int courseId,String courseName,CourseInfo courseInfo) {
        Log.d("1111111111",courseId+courseName);
        this.list = list;
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseInfo = courseInfo;
    }

    public enum ItemType {
        ITEM0, ITEM1
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == ItemType.ITEM0.ordinal()){
            return new Viewholder0_Expand_Undertake(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder0_undertake,parent,false), parent.getContext());
        }
        else if(viewType == ItemType.ITEM1.ordinal()){
            return new Viewholder1_Expand_UndertakeKt(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder1_undertake,parent,false), parent.getContext());
        }
        return null;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //int courseId = list.get(position).courseId;
        if(holder instanceof Viewholder0_Expand_Undertake){
            ((Viewholder0_Expand_Undertake) holder).initChapterRetrofit(courseId,courseName,courseInfo);
        }else if(holder instanceof Viewholder1_Expand_UndertakeKt){
            ((Viewholder1_Expand_UndertakeKt) holder).initRetrofit(courseId);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0) {
            return ItemType.ITEM0.ordinal();        //获取某个枚举对象的位置索引值
        }else if(position == 1) {
            return ItemType.ITEM1.ordinal();
        }
        return 0;
    }
}

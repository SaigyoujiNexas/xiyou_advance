package com.xiyou.homepage.undertake;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xiyou.homepage.R;

public class Adapter_Undertake extends RecyclerView.Adapter{
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
            return new Viewholder1_Expand_Undertake(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder1_undertake,parent,false), parent.getContext());
        }
        return null;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof Viewholder0_Expand_Undertake){
            ((Viewholder0_Expand_Undertake) holder).initData();
            ((Viewholder0_Expand_Undertake) holder).initViews();
        }else if(holder instanceof Viewholder1_Expand_Undertake){
            ((Viewholder1_Expand_Undertake) holder).initView();
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

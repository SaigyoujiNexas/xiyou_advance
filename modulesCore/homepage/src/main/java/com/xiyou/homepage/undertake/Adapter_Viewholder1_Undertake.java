package com.xiyou.homepage.undertake;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xiyou.homepage.R;

import java.util.List;

public class Adapter_Viewholder1_Undertake extends RecyclerView.Adapter<Adapter_Viewholder1_Undertake.CommentViewholder> {
    List<Comment_Course> list;

    public Adapter_Viewholder1_Undertake(List<Comment_Course> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public CommentViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CommentViewholder commentViewholder = new CommentViewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_commentcourse_undertake,parent,false));
        return commentViewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewholder holder, int position) {
        Comment_Course comment_course = list.get(position);
        holder.usernameText.setText(comment_course.getComment());
        holder.commentText.setText(comment_course.getUserName());
        holder.sendtiemText.setText(comment_course.getSendTime());
        holder.userImg.setImageResource(comment_course.getUserImg());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CommentViewholder extends RecyclerView.ViewHolder {
        ImageView userImg;
        TextView usernameText;
        TextView commentText;
        TextView sendtiemText;
        public CommentViewholder(@NonNull View itemView) {
            super(itemView);
            userImg = itemView.findViewById(R.id.userimg_comment_undertake);
            commentText = itemView.findViewById(R.id.commenttext_undertake);
            sendtiemText = itemView.findViewById(R.id.sendtime_comment_undertake);
            usernameText =  itemView.findViewById(R.id.username_comment_undertake);
        }
    }
}

package com.example.hostelgomadproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.myViewHolder> {

    Context context;
    ArrayList<fetchNotice> list;

    public NoticeAdapter(Context context, ArrayList<fetchNotice> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.notices,parent,false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position) {
        fetchNotice noticeList=list.get(position);
        holder.date.setText(noticeList.getDate());
        holder.title.setText(noticeList.getTitle());
        holder.content.setText(noticeList.getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class myViewHolder extends ViewHolder{

        TextView date,title,content;
        ImageView deleteImage;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            date=itemView.findViewById(R.id.rvNoticeDate);
            title=itemView.findViewById(R.id.rvNoticeTitle);
            content=itemView.findViewById(R.id.rvNoticeContent);

        }
    }

}

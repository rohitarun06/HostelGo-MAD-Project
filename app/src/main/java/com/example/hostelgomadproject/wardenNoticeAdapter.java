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

public class wardenNoticeAdapter extends RecyclerView.Adapter<wardenNoticeAdapter.myViewHolder> {

    Context context;
    ArrayList<fetchNotice> list;

    wardenNoticeAdapter.OnItemClickListener listener;

    DatabaseReference reference= FirebaseDatabase.getInstance().getReference("NOTICE");

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItenClickListener(wardenNoticeAdapter.OnItemClickListener clickListener){
        listener=clickListener;
    }

    public wardenNoticeAdapter(Context context, ArrayList<fetchNotice> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.noticeview,parent,false);
        return new myViewHolder(v,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position) {
        fetchNotice noticeList=list.get(position);
        holder.date.setText(noticeList.getDate());
        holder.title.setText(noticeList.getTitle());
        holder.content.setText(noticeList.getContent());
        holder.deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id =noticeList.getID();
                reference.child(id).removeValue();
                list.remove(position);
                notifyItemRemoved(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class myViewHolder extends ViewHolder{

        TextView date,title,content;
        ImageView deleteImage;

        public myViewHolder(@NonNull View itemView,wardenNoticeAdapter.OnItemClickListener listener) {
            super(itemView);

            date=itemView.findViewById(R.id.rvNoticeDatedelete);
            title=itemView.findViewById(R.id.rvNoticeTitledelete);
            content=itemView.findViewById(R.id.rvNoticeContentdelete);
            deleteImage=itemView.findViewById(R.id.deleteImage);


        }
    }

}

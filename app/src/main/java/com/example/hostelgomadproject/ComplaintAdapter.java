package com.example.hostelgomadproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.myViewHolder> {

    Context context;
    ArrayList<fetchComplaints> list;

    ComplaintAdapter.OnItemClickListener listener;

    DatabaseReference reference= FirebaseDatabase.getInstance().getReference("COMPLAINTS");

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItenClickListener(ComplaintAdapter.OnItemClickListener clickListener){
        listener=clickListener;
    }
    public ComplaintAdapter(Context context, ArrayList<fetchComplaints> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.complaints,parent,false);
        return new myViewHolder(v,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position) {
        fetchComplaints complaintsList=list.get(position);
        holder.date.setText(complaintsList.getDate());
        holder.roomNo.setText(complaintsList.getRoomNo());
        holder.content.setText(complaintsList.getContent());


        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id =complaintsList.getID();
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

    public static class myViewHolder extends RecyclerView.ViewHolder {

        TextView date,roomNo,content;
        ImageView accept;

        public myViewHolder(@NonNull View itemView, ComplaintAdapter.OnItemClickListener listener) {
            super(itemView);

            date=itemView.findViewById(R.id.rvComplaintDate);
            roomNo=itemView.findViewById(R.id.rvComplaintTitle);
            content=itemView.findViewById(R.id.rvComplaintContent);
            accept=itemView.findViewById(R.id.acceptImage);

            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(getAdapterPosition());
                }
            });

        }
    }

}

package com.example.hostelgomadproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.myViewHolder> {

    Context context;
    ArrayList<fetchAtted> list;
    OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(int position,String selectedOption,String Name,String RoomNo);
    }

    public void setOnItenClickListener(OnItemClickListener clickListener){
        listener=clickListener;
    }

    public AttendanceAdapter(Context context, ArrayList<fetchAtted> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.attendence,parent,false);
        return new myViewHolder(v,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        fetchAtted studentList=list.get(position);
        holder.Name.setText(studentList.getName());
        holder.RoomNo.setText(studentList.getRoomNo());
        
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {

        TextView Name,RoomNo;
        RadioButton radioButton1,radioButton2;
        RadioGroup radioGroup;
        String selectedOption,Name1,RoomNo1;

        public myViewHolder(@NonNull View itemView,OnItemClickListener listener) {
            super(itemView);

            Name=itemView.findViewById(R.id.rvName);
            RoomNo=itemView.findViewById(R.id.rvRoomNo);
            radioGroup = itemView.findViewById(R.id.rbtton);
            radioButton1=itemView.findViewById(R.id.radioButton1);
            radioButton2=itemView.findViewById(R.id.radioButton2);

            radioButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    perform(listener);
                }
            });

            radioButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    perform(listener);
                }
            });

        }

        private void perform(OnItemClickListener listener) {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            RadioButton radioButton = itemView.findViewById(selectedId);
            selectedOption = radioButton.getText().toString();

            Name1=Name.getText().toString();
            RoomNo1=RoomNo.getText().toString();

            listener.onItemClick(getAdapterPosition(),selectedOption,Name1,RoomNo1);
        }
    }

}

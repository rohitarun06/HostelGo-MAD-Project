package com.example.hostelgomadproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.ArrayList;

public class FoodMenuAdapter extends RecyclerView.Adapter<FoodMenuAdapter.myViewHolder> {

    Context context;
    ArrayList<fetchFoodMenu> list;

    public FoodMenuAdapter(Context context, ArrayList<fetchFoodMenu> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.food,parent,false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        fetchFoodMenu menuList=list.get(position);
        holder.breakfast.setText(menuList.getBreakfast());
        holder.lunch.setText(menuList.getLunch());
        holder.dinner.setText(menuList.getDinner());
        holder.day.setText(menuList.getWeekday());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {

        TextView breakfast,lunch,dinner,day;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            breakfast=itemView.findViewById(R.id.rvBreakFastMenu);
            lunch=itemView.findViewById(R.id.rvLunchTimeMenu);
            dinner=itemView.findViewById(R.id.rvDinnerTimeMenu);
            day=itemView.findViewById(R.id.rvWeekDay);

        }
    }

}

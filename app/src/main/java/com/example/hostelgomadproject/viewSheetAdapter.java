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

public class viewSheetAdapter extends RecyclerView.Adapter<viewSheetAdapter.myViewHolder> {

    Context context;
    ArrayList<fetchViewAttendence> list;

    public viewSheetAdapter(Context context, ArrayList<fetchViewAttendence> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.viewsheetrecycler,parent,false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        fetchViewAttendence viewSheet=list.get(position);
        holder.Name.setText(viewSheet.getName());
        holder.RoomNo.setText(viewSheet.getRoomNo());
        holder.Status.setText(viewSheet.getStatus());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {

        TextView Name,RoomNo,Status,Date;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            Name=itemView.findViewById(R.id.rvViewSheetName);
            RoomNo=itemView.findViewById(R.id.rvViewSheetRommNo);
            Status=itemView.findViewById(R.id.rvViewSheetStatus);

        }
    }

}

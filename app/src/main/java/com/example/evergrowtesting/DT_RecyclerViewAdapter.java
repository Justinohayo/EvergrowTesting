package com.example.evergrowtesting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DT_RecyclerViewAdapter extends RecyclerView.Adapter<DT_RecyclerViewAdapter.MyViewHolder>{

    Context context;
    ArrayList<Task> task;

    public DT_RecyclerViewAdapter(Context context, ArrayList<Task> task) {
        this.context = context;
        this.task = task;
    }

    @NonNull
    @Override
    public DT_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);
        return new DT_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DT_RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.taskCheckBox.setText(task.get(position).getTaskName());
        holder.dateTextView.setText(task.get(position).getDeadline());
    }

    @Override
    public int getItemCount() {
        return task.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        CheckBox taskCheckBox;
        TextView dateTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            taskCheckBox = itemView.findViewById(R.id.checkBox);
            dateTextView = itemView.findViewById(R.id.textView);

        }
    }


}

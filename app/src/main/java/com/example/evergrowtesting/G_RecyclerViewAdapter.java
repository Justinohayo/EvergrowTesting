package com.example.evergrowtesting;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class G_RecyclerViewAdapter extends RecyclerView.Adapter<G_RecyclerViewAdapter.MyViewHolder>{

    Context context;
    ArrayList<GoalModel> goals;

    public G_RecyclerViewAdapter(Context context, ArrayList<GoalModel> goals) {
        this.context = context;
        this.goals = goals;
    }


    @NonNull
    @Override
    public G_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);
        return new G_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull G_RecyclerViewAdapter.MyViewHolder holder, int position) {

//        Testing no db
//        holder.taskCheckBox.setText(goals.get(position).getDescription());
//        holder.dateTextView.setText(goals.get(position).getDate());

        GoalModel goal = goals.get(position);
        holder.taskCheckBox.setText(goal.getDescription());
        holder.taskCheckBox.setChecked(goal.isCheckDone());
        holder.dateTextView.setText(goal.getDate());


        holder.itemBtn.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditGoalActivity.class);
            intent.putExtra("item", goals.get(position));  // Pass the object
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return goals.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        CheckBox taskCheckBox;
        TextView dateTextView;
        Button itemBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            taskCheckBox = itemView.findViewById(R.id.checkBox);
            dateTextView = itemView.findViewById(R.id.textView);
            itemBtn = itemView.findViewById(R.id.itemBtn);

        }
    }

}

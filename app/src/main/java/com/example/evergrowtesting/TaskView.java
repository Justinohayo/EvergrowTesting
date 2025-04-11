package com.example.evergrowtesting;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;
//
public class TaskView extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<TaskModel> tasks = new ArrayList<>();

    Button AddTaskbutton;

    int goalId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_task_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        AddTaskbutton = findViewById(R.id.AddTaskbutton);

        recyclerView = findViewById(R.id.recyclerView2);
        //setUpDailyTasks();

        setUpAllTasks();

//        DT_RecyclerViewAdapter adapter = new DT_RecyclerViewAdapter(this, tasks);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

//    public void setUpDailyTasks() {
//        String[] dailyTaskNames = getResources().getStringArray(R.array.bogy_data);
//
//        for(int i=0; i<dailyTaskNames.length; i++) {
//            tasks.add(new TaskModel(dailyTaskNames[i]));
//        }
//
//
//    }

    public void onClickAddTask(View view) {
        int passedGoalId = getIntent().getIntExtra("goalId", -1);

        if (passedGoalId != -1) {
            Intent intent = new Intent(TaskView.this, A_editTaskActivity.class);
            intent.putExtra("goalId", passedGoalId);
            startActivity(intent);
        } else {
            Toast.makeText(TaskView.this, "Goal ID not found. Cannot add task.", Toast.LENGTH_SHORT).show();
        }
    }



//    public void setUpTasks(int selectedGoalId) {
//        DatabaseHelper dbHelper = new DatabaseHelper(this);
//        tasks = dbHelper.getTasksByGoalId(selectedGoalId);
//    }

    public void setUpTasks(int goalId) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        tasks = dbHelper.getTasksByGoalId(goalId); // use the field, not a new local variable

        if (tasks != null && !tasks.isEmpty()) {
            DT_RecyclerViewAdapter adapter = new DT_RecyclerViewAdapter(this, tasks);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            Toast.makeText(TaskView.this, "No tasks found for this goal.", Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle item selection based on ID
        switch (Objects.requireNonNull(item.getTitle().toString())) {
            case "Daily Tasks":
                startActivity(new Intent(this, MainActivity.class));
                //Toast.makeText(this,"Daily Tasks clicked", Toast.LENGTH_SHORT).show();
                return true;
            case "Goal View":
                startActivity(new Intent(this, GoalView.class));
                //Toast.makeText(this,"Goal view clicked", Toast.LENGTH_SHORT).show();
                return true;
            case "Edit Goal":
                startActivity(new Intent(this, EditGoalActivity.class));
                //Toast.makeText(this,"Goal edit clicked", Toast.LENGTH_SHORT).show();
                return true;

            case "Task View":
                startActivity(new Intent(this, TaskView.class));
                //Toast.makeText(this,"Task view clicked", Toast.LENGTH_SHORT).show();
                return true;

            case "Edit Tasks":
                startActivity(new Intent(this, A_editTaskActivity.class));
                //Toast.makeText(this,"Task edit clicked", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setUpAllTasks() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        tasks = dbHelper.getAllTasks();

        if (tasks != null && !tasks.isEmpty()) {
            DT_RecyclerViewAdapter adapter = new DT_RecyclerViewAdapter(this, tasks);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        } else {
            recyclerView.setAdapter(null);
            Toast.makeText(this, "No tasks found in the database.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpAllTasks(); // Fetch goals again from DB

    }
}
package com.example.evergrowtesting;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class GoalView extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<GoalModel> goals = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_goal_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);
        setUpGoalView();
        G_RecyclerViewAdapter adapter = new G_RecyclerViewAdapter(this, goals);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

// Testing
//    public void setUpGoalView() {
//        String[] goalsName = getResources().getStringArray(R.array.bogy_goal_data);
//
//        for(int i=0; i<goalsName.length; i++) {
//            goals.add(new GoalModel(goalsName[i]));
//        }
//
//
//    }

    public void setUpGoalView() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        goals = dbHelper.getAllGoals(); // Pull from SQLite instead of resource array
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
}
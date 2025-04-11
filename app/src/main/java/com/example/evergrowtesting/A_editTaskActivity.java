package com.example.evergrowtesting;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;


public class A_editTaskActivity extends AppCompatActivity {

    // test

    Button btn_save;
    EditText ed_task, ed_date, ed_taskdescription;

    CheckBox taskcheckbox;

    int goalId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_task);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });


        btn_save = findViewById(R.id.btn_save);
        ed_date = findViewById(R.id.ed_date);
        ed_task = findViewById(R.id.ed_goal);
        ed_taskdescription = findViewById(R.id.ed_goaldescription);
        taskcheckbox = findViewById(R.id.taskcheckbox);

        // get the id from goalmodel
        goalId = getIntent().getIntExtra("goalId", -1);


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskModel taskModel;
                GoalModel goalModel;

                try {
                    taskModel = new TaskModel(-1, ed_task.getText().toString(), ed_taskdescription.getText().toString(), taskcheckbox.isChecked(), ed_date.getText().toString(), goalId);

                    DatabaseHelper db = new DatabaseHelper(A_editTaskActivity.this);
                    boolean success = db.addOneTask(taskModel);

                    if (success) {
                        Toast.makeText(A_editTaskActivity.this, "Task saved successfully!", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(A_editTaskActivity.this, "Failed to save task.", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e)
                {
                    Toast.makeText(A_editTaskActivity.this, "Error occur while save",  Toast.LENGTH_LONG).show();
                }

                DatabaseHelper db = new DatabaseHelper(A_editTaskActivity.this);

            }
        });
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
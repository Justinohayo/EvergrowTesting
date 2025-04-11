package com.example.evergrowtesting;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class EditGoalActivity extends AppCompatActivity {

    Button btn_save, btn_task;
    EditText ed_goal, ed_goaldescription, ed_date;
    CheckBox goalcheckbox;
    TextView tv_task;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_goal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn_save = findViewById(R.id.btn_save);
        btn_task = findViewById(R.id.btn_task);
        ed_goal = findViewById(R.id.ed_goal);
        ed_date = findViewById(R.id.ed_date);
        ed_goaldescription = findViewById(R.id.ed_goaldescription);
        goalcheckbox = findViewById(R.id.checkBox);
        tv_task = findViewById(R.id.tv_task);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoalModel goalModel;
                try {
                    goalModel = new GoalModel(-1, ed_goal.getText().toString(), ed_goaldescription.getText().toString(), goalcheckbox.isChecked(), ed_date.getText().toString());
                    DatabaseHelper db = new DatabaseHelper(EditGoalActivity.this);
                    boolean success = db.addOneGoal(goalModel);
                    if (success) {
                        Toast.makeText(EditGoalActivity.this, "Task saved successfully!", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(EditGoalActivity.this, "Failed to save task.", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(EditGoalActivity.this, "Error occur while save", Toast.LENGTH_LONG).show();
                }

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
                        startActivity(new Intent(EditGoalActivity.this, MainActivity.class));
                        //Toast.makeText(this,"Daily Tasks clicked", Toast.LENGTH_SHORT).show();
                        return true;
                    case "Goal View":
                        startActivity(new Intent(EditGoalActivity.this, GoalView.class));
                        //Toast.makeText(this,"Goal view clicked", Toast.LENGTH_SHORT).show();
                        return true;
                    case "Edit Goal":
                        startActivity(new Intent(EditGoalActivity.this, EditGoalActivity.class));
                        //Toast.makeText(this,"Goal edit clicked", Toast.LENGTH_SHORT).show();
                        return true;

                    case "Task View":
                        startActivity(new Intent(EditGoalActivity.this, TaskView.class));
                        //Toast.makeText(this,"Task view clicked", Toast.LENGTH_SHORT).show();
                        return true;

                    case "Edit Tasks":
                        startActivity(new Intent(EditGoalActivity.this, A_editTaskActivity.class));
                        //Toast.makeText(this,"Task edit clicked", Toast.LENGTH_SHORT).show();
                        return true;

                    default:
                        return super.onOptionsItemSelected(item);
                }
            }

            public void onClickEditGoalButton(View view) {
                startActivity(new Intent(EditGoalActivity.this, TaskView.class));
            }


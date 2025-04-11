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

    GoalModel incomingGoalModel;
    int goalId;

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


        incomingGoalModel = (GoalModel) getIntent().getSerializableExtra("goal");

        if (incomingGoalModel != null) {
            ed_goal.setText(incomingGoalModel.getName());
            ed_goaldescription.setText(incomingGoalModel.getDescription());
            ed_date.setText(incomingGoalModel.getDate());
            goalcheckbox.setChecked(incomingGoalModel.isCheckDone());
            goalId = incomingGoalModel.getGoalId();
        } else {
            goalId = getIntent().getIntExtra("goalId", -1);
        }

        btn_save.setOnClickListener(view -> {
            try {
                DatabaseHelper db = new DatabaseHelper(EditGoalActivity.this);

                if (goalcheckbox.isChecked() && incomingGoalModel != null && incomingGoalModel.getGoalId() != -1) {
                    boolean deleted = db.deleteGoal(incomingGoalModel.getGoalId());
                    if (deleted) {
                        Toast.makeText(this, "Goal deleted successfully!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(this, "Failed to delete goal.", Toast.LENGTH_SHORT).show();
                    }
                    return;
                }


                GoalModel updatedGoal = new GoalModel(
                        incomingGoalModel != null ? incomingGoalModel.getGoalId() : -1,
                        ed_goal.getText().toString(),
                        ed_goaldescription.getText().toString(),
                        goalcheckbox.isChecked(),
                        ed_date.getText().toString()
                );

                boolean success;
                if (incomingGoalModel != null) {
                    success = db.updateGoal(updatedGoal); // you must create this method
                } else {
                    success = db.addOneGoal(updatedGoal);
                }

                if (success) {
                    Toast.makeText(this, "Goal saved successfully!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Failed to save goal.", Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                Toast.makeText(this, "Error occurred while saving.", Toast.LENGTH_LONG).show();
            }
        });

        btn_task.setOnClickListener(view -> {
            if (goalId != -1) {
                Intent intent = new Intent(this, TaskView.class);
                intent.putExtra("goalId", goalId);
                startActivity(intent);
                Toast.makeText(this, "goalID " + goalId, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Goal data not found.", Toast.LENGTH_SHORT).show();
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
        switch (Objects.requireNonNull(item.getTitle().toString())) {
            case "Daily Tasks":
                startActivity(new Intent(this, MainActivity.class));
                return true;
            case "Goal View":
                startActivity(new Intent(this, GoalView.class));
                return true;
            case "Edit Goal":
                startActivity(new Intent(this, EditGoalActivity.class));
                return true;
            case "Task View":
                startActivity(new Intent(this, TaskView.class));
                return true;
            case "Edit Tasks":
                startActivity(new Intent(this, A_editTaskActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClickEditGoalButton(View view) {
        if (goalId != -1) {
            Intent intent = new Intent(this, TaskView.class);
            intent.putExtra("goalId", goalId);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Goal data not found.", Toast.LENGTH_SHORT).show();
        }
    }
}
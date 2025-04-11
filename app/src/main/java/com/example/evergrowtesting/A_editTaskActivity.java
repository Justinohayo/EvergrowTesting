package com.example.evergrowtesting;

import android.os.Bundle;
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
                taskModel = new TaskModel(-1,ed_taskdescription.getText().toString(), taskcheckbox.isChecked(), ed_date.getText().toString(), goalId);

                DatabaseHelper db = new DatabaseHelper(A_editTaskActivity.this);
                boolean success = db.addOneTask(taskModel);

                //for testing
                if(success)
                {
                    Toast.makeText(A_editTaskActivity.this, "Task saved",  Toast.LENGTH_LONG).show();

                }
                else {
                    Toast.makeText(A_editTaskActivity.this, "Fail to save",  Toast.LENGTH_LONG).show();
                }

            }
        });
    }


}
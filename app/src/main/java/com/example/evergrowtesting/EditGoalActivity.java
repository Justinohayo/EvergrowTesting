package com.example.evergrowtesting;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

        btn_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoalModel goalModel;

                try{
                    goalModel = new GoalModel(-1, ed_goaldescription.getText().toString(),goalcheckbox.isChecked(),ed_date.getText().toString());
                    DatabaseHelper db = new DatabaseHelper(EditGoalActivity.this);
                    boolean success = db.addOneGoal(goalModel);
                } catch (Exception e)
                {

                }



            }
        });


    }

}
package com.karapetyanarthur.canisapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.karapetyanarthur.canisapp.R;

public class EnterActivity extends AppCompatActivity {

    Button act_log_in_btn;
    Button act_registration_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        act_log_in_btn = findViewById(R.id.act_log_in_btn);
        act_registration_btn = findViewById(R.id.act_registration_btn);

        act_log_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(".LogInActivity");
            }
        });

        act_registration_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(".RegNameSurnameActivity");
            }
        });

    }

    public void changeActivity(String name_of_activity){
        Intent changeMyActivity = new Intent(name_of_activity);
        startActivity(changeMyActivity);
    }

}
package com.karapetyanarthur.canisapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.karapetyanarthur.canisapp.R;

public class LogInActivity extends AppCompatActivity {

    Button back_btn;
    EditText login_et;
    EditText pass_et;
    ImageButton show_pass_btn;
    ImageButton hide_pass_btn;
    Button log_in_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        back_btn = findViewById(R.id.back_btn);
        login_et = findViewById(R.id.login_et);
        pass_et = findViewById(R.id.pass_et);
        show_pass_btn = findViewById(R.id.show_pass_btn);
        hide_pass_btn = findViewById(R.id.hide_pass_btn);
        log_in_btn = findViewById(R.id.log_in_btn);

        hide_pass_btn.setVisibility(View.GONE);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(".EnterActivity");
            }
        });

        show_pass_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pass_et.setTransformationMethod(null);
                show_pass_btn.setVisibility(View.GONE);
                hide_pass_btn.setVisibility(View.VISIBLE);
            }
        });

        hide_pass_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pass_et.setTransformationMethod(new PasswordTransformationMethod());
                hide_pass_btn.setVisibility(View.GONE);
                show_pass_btn.setVisibility(View.VISIBLE);
            }
        });

        log_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!login_et.getText().toString().equals("") && !pass_et.getText().toString().equals("")) {
//ДОБАВИТЬ ПРОВЕРКУ ЛОГИНА И ПАРОЛЯ
                    changeActivity(".PermissionsActivity");
                }
            }
        });
    }

    public void changeActivity(String name_of_activity){
        Intent changeMyActivity = new Intent(name_of_activity);
        startActivity(changeMyActivity);
    }
}
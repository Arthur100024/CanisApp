package com.karapetyanarthur.canisapp.Activities.Registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.karapetyanarthur.canisapp.R;

public class RegPasswordActivity extends AppCompatActivity {

    Button back_btn;
    EditText pass_et;
    ImageButton show_pass_btn;
    ImageButton hide_pass_btn;
    EditText repeat_pass_et;
    ImageButton show_repeat_pass_btn;
    ImageButton hide_repeat_pass_btn;
    Button next_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_password);

        back_btn = findViewById(R.id.back_btn);
        pass_et = findViewById(R.id.pass_et);
        show_pass_btn = findViewById(R.id.show_pass_btn);
        hide_pass_btn = findViewById(R.id.hide_pass_btn);
        repeat_pass_et = findViewById(R.id.repeat_pass_et);
        show_repeat_pass_btn = findViewById(R.id.show_repeat_pass_btn);
        hide_repeat_pass_btn = findViewById(R.id.hide_repeat_pass_btn);
        next_btn = findViewById(R.id.next_btn);

        hide_pass_btn.setVisibility(View.GONE);
        hide_repeat_pass_btn.setVisibility(View.GONE);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//НЕ СОХРАНЯТЬ ДАННЫЕ
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

        show_repeat_pass_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repeat_pass_et.setTransformationMethod(null);
                show_repeat_pass_btn.setVisibility(View.GONE);
                hide_repeat_pass_btn.setVisibility(View.VISIBLE);
            }
        });

        hide_repeat_pass_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repeat_pass_et.setTransformationMethod(new PasswordTransformationMethod());
                hide_repeat_pass_btn.setVisibility(View.GONE);
                show_repeat_pass_btn.setVisibility(View.VISIBLE);
            }
        });

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!pass_et.getText().toString().equals("") && !repeat_pass_et.getText().toString().equals("")){
                    if (pass_et.getText().toString().equals(repeat_pass_et.getText().toString())){
// ДОБАВИТЬ СОХРАНЕНИЕ ДАННЫХ
                        changeActivity(".EnterActivity");
                        showToast("Вы успешно зарегистрировались!");
                    } else {
                        showToast("Повторите пароль правильно!");
                    }
                } else {
                    showToast("Введите все поля!");
                }
            }
        });
    }

    public void showToast(String toast_text){
        Toast showMyToast = Toast.makeText(getApplicationContext(),toast_text,Toast.LENGTH_SHORT);
        showMyToast.show();
    }

    public void changeActivity(String name_of_activity){
        Intent changeMyActivity = new Intent(name_of_activity);
        startActivity(changeMyActivity);
    }

}
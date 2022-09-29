package com.karapetyanarthur.canisapp.Activities.Registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.karapetyanarthur.canisapp.R;

public class RegLoginActivity extends AppCompatActivity {

    Button back_btn;
    EditText login_et;
    Button next_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_login);

        back_btn = findViewById(R.id.back_btn);
        login_et = findViewById(R.id.login_et);
        next_btn = findViewById(R.id.next_btn);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//НЕ СОХРАНЯТЬ ДАННЫЕ
                changeActivity(".EnterActivity");
            }
        });

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!login_et.getText().toString().equals("")) {
// ДОБАВИТЬ СОХРАНЕНИЕ ДАННЫХ
                    changeActivity(".RegPasswordActivity");
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
package com.karapetyanarthur.canisapp.Activities.Registration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.karapetyanarthur.canisapp.Data.Model.ProfileModel;
import com.karapetyanarthur.canisapp.R;
import com.karapetyanarthur.canisapp.ViewModel.ProfileViewModel;

public class RegNameSurnameActivity extends AppCompatActivity {

    Button back_btn;
    EditText name_et;
    EditText surname_et;
    Button next_btn;

    ProfileViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_name_surname);

        back_btn = findViewById(R.id.back_btn);
        name_et = findViewById(R.id.name_et);
        surname_et = findViewById(R.id. surname_et);
        next_btn = findViewById(R.id.next_btn);

        model = new ViewModelProvider(this).get(ProfileViewModel.class);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(".EnterActivity");
            }
        });

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!name_et.getText().toString().equals("") && !surname_et.getText().toString().equals("")){
// ДОБАВИТЬ СОХРАНЕНИЕ ДАННЫХ
                    ProfileModel profile = new ProfileModel();
                    profile.setId(0);
                    profile.setName(name_et.getText().toString());
                    profile.setSurname(surname_et.getText().toString());
                    model.insert(profile);

                    changeActivity(".RegPhoneActivity");
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
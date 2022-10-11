package com.karapetyanarthur.canisapp.Activities.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.karapetyanarthur.canisapp.Activities.NavigationActivity;
import com.karapetyanarthur.canisapp.R;

public class EditPetFragment extends Fragment {

    ImageView pet_image_iv;
    Button change_pet_image_btn;
    EditText nickname_pet_et;
    EditText breed_pet_et;
    EditText age_pet_et;
    Button save_changes_btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_pet, container, false);

        pet_image_iv = view.findViewById(R.id.pet_image_iv);
        change_pet_image_btn = view.findViewById(R.id.change_pet_image_btn);
        nickname_pet_et = view.findViewById(R.id.nickname_pet_et);
        breed_pet_et = view.findViewById(R.id.breed_pet_et);
        age_pet_et = view.findViewById(R.id.age_pet_et);
        save_changes_btn = view.findViewById(R.id.save_changes_btn);

        change_pet_image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent photoPick = new Intent(Intent.ACTION_GET_CONTENT);
                startActivity(photoPick);*/
            }
        });


        save_changes_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//ДОБАВИТЬ СОХРАНЕНИЕ ДАННЫХ
                NavigationActivity.changed_fragment = 1;
                changeActivity(".NavigationActivity");
            }
        });
        return view;
    }

    public void changeActivity(String name_of_activity){
        Intent changeMyActivity = new Intent(name_of_activity);
        changeMyActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(changeMyActivity);
    }
}
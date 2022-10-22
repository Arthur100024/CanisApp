package com.karapetyanarthur.canisapp.Activities.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.karapetyanarthur.canisapp.Activities.NavigationActivity;
import com.karapetyanarthur.canisapp.Data.DBPet;
import com.karapetyanarthur.canisapp.Data.DBProfile;
import com.karapetyanarthur.canisapp.Model.PetModel;
import com.karapetyanarthur.canisapp.R;
import com.karapetyanarthur.canisapp.ViewModel.AppViewModel;

import java.util.List;

public class EditPetFragment extends Fragment {

    ImageView pet_image_iv;
    Button change_pet_image_btn;
    EditText nickname_pet_et;
    EditText breed_pet_et;
    EditText age_pet_et;
    Button save_changes_btn;

    AppViewModel model;

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

        model = new ViewModelProvider(this).get(AppViewModel.class);

        model.getAllPet().observe(getViewLifecycleOwner(), new Observer<List<DBPet>>() {
            @Override
            public void onChanged(List<DBPet> dbPets) {
                if (dbPets.size() != 0){
                    nickname_pet_et.setText(dbPets.get(dbPets.size() - 1).getNickname());
                    breed_pet_et.setText(dbPets.get(dbPets.size() - 1).getBreed());
                    age_pet_et.setText(dbPets.get(dbPets.size() - 1).getAge());
                }

                Log.d("User_Data", String.valueOf(dbPets.size()));

            }
        });

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

                PetModel pet = new PetModel();
                pet.setId(0);
                pet.setNickname(nickname_pet_et.getText().toString());
                pet.setBreed(breed_pet_et.getText().toString());
                pet.setAge(age_pet_et.getText().toString());

                model.insert(pet);

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
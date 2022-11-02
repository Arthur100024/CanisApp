package com.karapetyanarthur.canisapp.Activities.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.karapetyanarthur.canisapp.Activities.NavigationActivity;
import com.karapetyanarthur.canisapp.Data.DBPet;
import com.karapetyanarthur.canisapp.Data.Model.PetModel;
import com.karapetyanarthur.canisapp.R;
import com.karapetyanarthur.canisapp.ViewModel.PetViewModel;
import com.karapetyanarthur.canisapp.ViewModel.ProfileViewModel;

import java.util.List;

public class PetFragment extends Fragment {

    ImageView pet_image_iv;
    TextView nickname_pet;
    TextView breed_pet;
    TextView age_pet;
    Button edit_pet_btn;

    PetViewModel model;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pet, container, false);

        pet_image_iv = view.findViewById(R.id.pet_image_iv);
        nickname_pet = view.findViewById(R.id.nickname_pet);
        breed_pet = view.findViewById(R.id.breed_pet);
        age_pet = view.findViewById(R.id.age_pet);
        edit_pet_btn = view.findViewById(R.id.edit_pet_btn);

        model = new ViewModelProvider(this).get(PetViewModel.class);

        model.getAllPet().observe(getViewLifecycleOwner(), new Observer<List<PetModel>>() {
            @Override
            public void onChanged(List<PetModel> petModels) {
                if (petModels.size() != 0){
                    nickname_pet.setText(petModels.get(petModels.size() - 1).getNickname());
                    breed_pet.setText(petModels.get(petModels.size() - 1).getBreed());
                    age_pet.setText(petModels.get(petModels.size() - 1).getAge());
                    if (petModels.get(petModels.size() - 1).getImage() != null){
                        pet_image_iv.setBackground(null);
                        pet_image_iv.setImageURI(Uri.parse(petModels.get(petModels.size() - 1).getImage()));
                    }
                }

                Log.d("User_Data", String.valueOf(petModels.size()));

            }
        });

        edit_pet_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationActivity.changed_fragment = 11;
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
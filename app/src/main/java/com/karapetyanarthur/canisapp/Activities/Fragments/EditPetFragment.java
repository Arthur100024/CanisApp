package com.karapetyanarthur.canisapp.Activities.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
import com.karapetyanarthur.canisapp.Data.Model.PetModel;
import com.karapetyanarthur.canisapp.R;
import com.karapetyanarthur.canisapp.ViewModel.EditPetViewModel;
import com.karapetyanarthur.canisapp.ViewModel.PetViewModel;
import com.karapetyanarthur.canisapp.ViewModel.ProfileViewModel;

import java.util.List;

public class EditPetFragment extends Fragment {

    ImageView pet_image_iv;
    Button change_pet_image_btn;
    EditText nickname_pet_et;
    EditText breed_pet_et;
    EditText age_pet_et;
    Button save_changes_btn;

    EditPetViewModel model;

    Uri uri_image;

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

        model = new ViewModelProvider(this).get(EditPetViewModel.class);

        model.getAllPet().observe(getViewLifecycleOwner(), new Observer<List<PetModel>>() {
            @Override
            public void onChanged(List<PetModel> petModels) {
                if (petModels.size() != 0){
                    nickname_pet_et.setText(petModels.get(petModels.size() - 1).getNickname());
                    breed_pet_et.setText(petModels.get(petModels.size() - 1).getBreed());
                    age_pet_et.setText(petModels.get(petModels.size() - 1).getAge());
                    if (petModels.get(petModels.size() - 1).getImage() != null){
                        uri_image = Uri.parse(petModels.get(petModels.size() - 1).getImage());
                        pet_image_iv.setBackground(null);
                        pet_image_iv.setImageURI(Uri.parse(petModels.get(petModels.size() - 1).getImage()));
                    }
                }

                Log.d("User_Data", String.valueOf(petModels.size()));
            }
        });

        ActivityResultLauncher<String[]> getContent = getActivity().getActivityResultRegistry().register("key", new ActivityResultContracts.OpenDocument(), result -> {
            getActivity().getContentResolver().takePersistableUriPermission(result, Intent.FLAG_GRANT_READ_URI_PERMISSION);

            pet_image_iv.setBackground(null);
            pet_image_iv.setImageURI(result);
            uri_image = result;
        });

        change_pet_image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContent.launch(new String[]{"image/*"});
            }
        });


        save_changes_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PetModel pet = new PetModel();
                pet.setId(0);
                pet.setNickname(nickname_pet_et.getText().toString());
                pet.setBreed(breed_pet_et.getText().toString());
                pet.setAge(age_pet_et.getText().toString());
                if (uri_image != null) {
                    pet.setImage(uri_image.toString());
                }
                model.insert(pet);
                replaceFragment(new PetFragment());
            }
        });
        return view;
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame, fragment);
        ft.commit();
    }
}
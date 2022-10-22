package com.karapetyanarthur.canisapp.Activities.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.karapetyanarthur.canisapp.Activities.NavigationActivity;
import com.karapetyanarthur.canisapp.Data.DBProfile;
import com.karapetyanarthur.canisapp.Model.ProfileModel;
import com.karapetyanarthur.canisapp.R;
import com.karapetyanarthur.canisapp.ViewModel.AppViewModel;

import java.util.List;

public class EditProfileFragment extends Fragment {

    ImageView profile_image_iv;
    Button change_profile_image_btn;
    EditText email_profile_et;
    EditText name_profile_et;
    EditText surname_profile_et;
    EditText phone_profile_et;
    EditText age_profile_et;
    Button save_changes_btn;

    AppViewModel model;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        profile_image_iv = view.findViewById(R.id.profile_image_iv);
        change_profile_image_btn = view.findViewById(R.id.change_profile_image_btn);
        email_profile_et = view.findViewById(R.id.email_profile_et);
        name_profile_et = view.findViewById(R.id.name_profile_et);
        surname_profile_et = view.findViewById(R.id.surname_profile_et);
        phone_profile_et = view.findViewById(R.id.phone_profile_et);
        age_profile_et = view.findViewById(R.id.age_profile_et);
        save_changes_btn = view.findViewById(R.id.save_changes_btn);

        model = new ViewModelProvider(this).get(AppViewModel.class);

        model.getAllProfile().observe(getViewLifecycleOwner(), new Observer<List<DBProfile>>() {
            @Override
            public void onChanged(List<DBProfile> dbProfiles) {
                if (dbProfiles.size() != 0){
                    email_profile_et.setText(dbProfiles.get(dbProfiles.size() - 1).getEmail());
                    name_profile_et.setText(dbProfiles.get(dbProfiles.size() - 1).getName());
                    surname_profile_et.setText(dbProfiles.get(dbProfiles.size() - 1).getSurname());
                    phone_profile_et.setText(dbProfiles.get(dbProfiles.size() - 1).getPhone());
                    age_profile_et.setText(dbProfiles.get(dbProfiles.size() - 1).getAge());
                }

                Log.d("User_Data", String.valueOf(dbProfiles.size()));

            }
        });

        change_profile_image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent photoPick = new Intent(Intent.ACTION_GET_CONTENT);
                startActivity(photoPick);*/
            }
        });


        save_changes_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

// Сохранение данных в Room

                ProfileModel profile = new ProfileModel();
                profile.setId(0);
                profile.setEmail(email_profile_et.getText().toString());
                profile.setName(name_profile_et.getText().toString());
                profile.setSurname(surname_profile_et.getText().toString());
                profile.setPhone(phone_profile_et.getText().toString());
                profile.setAge(age_profile_et.getText().toString());

                model.insert(profile);




                NavigationActivity.changed_fragment = 3;
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
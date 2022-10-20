package com.karapetyanarthur.canisapp.Activities.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.provider.ContactsContract;
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
                /*model.insert(new DBProfile(0,email_profile_et.getText().toString(),
                        name_profile_et.getText().toString(),
                        surname_profile_et.getText().toString(),
                        phone_profile_et.getText().toString(),
                        age_profile_et.getText().toString()));*/

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
package com.karapetyanarthur.canisapp.Activities.Fragments;

import android.content.Intent;
import android.net.Uri;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.karapetyanarthur.canisapp.Activities.NavigationActivity;
import com.karapetyanarthur.canisapp.Data.AppDatabase;
import com.karapetyanarthur.canisapp.Data.AppRepository;
import com.karapetyanarthur.canisapp.Data.DBProfile;
import com.karapetyanarthur.canisapp.Data.ProfileDAO;
import com.karapetyanarthur.canisapp.R;
import com.karapetyanarthur.canisapp.ViewModel.AppViewModel;

import java.util.List;

public class ProfileFragment extends Fragment {

    Button edit_profile_btn;
    ImageView profile_image_iv;

    TextView email_profile;
    TextView name_profile;
    TextView surname_profile;
    TextView phone_profile;
    TextView age_profile;

    Button look_for_client;
    Button look_for_cynologist;

    AppViewModel model;

    public static String look_for;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        edit_profile_btn = view.findViewById(R.id.edit_profile_btn);
        profile_image_iv = view.findViewById(R.id.profile_image_iv);

        email_profile = view.findViewById(R.id.email_profile);
        name_profile = view.findViewById(R.id.name_profile);
        surname_profile = view.findViewById(R.id.surname_profile);
        phone_profile = view.findViewById(R.id.phone_profile);
        age_profile = view.findViewById(R.id.age_profile);

        look_for_client = view.findViewById(R.id.look_for_client);
        look_for_cynologist = view.findViewById(R.id.look_for_cynologist);

        if (look_for != null){
            changeColorLookFor();
        }

        model = new ViewModelProvider(this).get(AppViewModel.class);
        model.getAllProfile().observe(getViewLifecycleOwner(), new Observer<List<DBProfile>>() {
            @Override
            public void onChanged(List<DBProfile> dbProfiles) {
                if (dbProfiles.size() != 0){
                    email_profile.setText(dbProfiles.get(dbProfiles.size() - 1).getEmail());
                    name_profile.setText(dbProfiles.get(dbProfiles.size() - 1).getName());
                    surname_profile.setText(dbProfiles.get(dbProfiles.size() - 1).getSurname());
                    phone_profile.setText(dbProfiles.get(dbProfiles.size() - 1).getPhone());
                    age_profile.setText(dbProfiles.get(dbProfiles.size() - 1).getAge());
                    if (dbProfiles.get(dbProfiles.size() - 1).getImage() != null){
                        profile_image_iv.setBackground(null);
                        profile_image_iv.setImageURI(Uri.parse(dbProfiles.get(dbProfiles.size() - 1).getImage()));
                    }
                }

                Log.d("User_Data", String.valueOf(dbProfiles.size()));

            }
        });

        edit_profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationActivity.changed_fragment = 31;
                changeActivity(".NavigationActivity");
            }
        });

        look_for_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                look_for_client.setBackgroundColor(look_for_client.getContext().getResources().getColor(R.color.purple_500));
                look_for_cynologist.setBackgroundColor(look_for_cynologist.getContext().getResources().getColor(R.color.purple_200));
                look_for = "client";
            }
        });

        look_for_cynologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                look_for_cynologist.setBackgroundColor(look_for_cynologist.getContext().getResources().getColor(R.color.purple_500));
                look_for_client.setBackgroundColor(look_for_client.getContext().getResources().getColor(R.color.purple_200));
                look_for = "cynologist";
            }
        });


        return view;
    }

    public void changeActivity(String name_of_activity){
        Intent changeMyActivity = new Intent(name_of_activity);
        changeMyActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(changeMyActivity);
    }

    public void changeColorLookFor(){
        if (look_for.equals("client")){
            look_for_client.setBackgroundColor(look_for_client.getContext().getResources().getColor(R.color.purple_500));
            look_for_cynologist.setBackgroundColor(look_for_cynologist.getContext().getResources().getColor(R.color.purple_200));
        } else if (look_for.equals("cynologist")){
            look_for_cynologist.setBackgroundColor(look_for_cynologist.getContext().getResources().getColor(R.color.purple_500));
            look_for_client.setBackgroundColor(look_for_client.getContext().getResources().getColor(R.color.purple_200));
        }
    }

}
package com.karapetyanarthur.canisapp.Activities.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.provider.ContactsContract;
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

    AppViewModel model;

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

        model = new ViewModelProvider(this).get(AppViewModel.class);


        model.getAllProfile().observe(getViewLifecycleOwner(), new Observer<List<DBProfile>>() {
            @Override
            public void onChanged(List<DBProfile> dbProfiles) {
                //email_profile.setText(dbProfiles.toString());
               //email_profile.setText(model.getEmailProfile());
                System.out.println(model.getEmailProfile());
                //email_profile.setText(model.getProfileMV().getEmail());
            }
        });





        edit_profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationActivity.changed_fragment = 31;
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
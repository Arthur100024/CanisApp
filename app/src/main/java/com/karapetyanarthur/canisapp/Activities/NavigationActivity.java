package com.karapetyanarthur.canisapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.karapetyanarthur.canisapp.Activities.Fragments.AllMapFragment;
import com.karapetyanarthur.canisapp.Activities.Fragments.ProfileFragment;
import com.karapetyanarthur.canisapp.R;

public class NavigationActivity extends AppCompatActivity {

    Button pet_btn;
    Button map_btn;
    Button profile_btn;

    public static int changed_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        pet_btn = findViewById(R.id.pet_btn);
        map_btn = findViewById(R.id.map_btn);
        profile_btn = findViewById(R.id.profile_btn);

        if (changed_fragment == 1){
            //replaceFragment(new PetFragment());
        } else if (changed_fragment == 2){
            replaceFragment(new AllMapFragment());
        } else if(changed_fragment == 3){
            replaceFragment(new ProfileFragment());
        }

        pet_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        map_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new AllMapFragment());
            }
        });

        profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new ProfileFragment());
            }
        });
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame, fragment);
        ft.commit();
    }
}
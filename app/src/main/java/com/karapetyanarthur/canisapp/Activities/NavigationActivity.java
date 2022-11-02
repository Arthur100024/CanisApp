package com.karapetyanarthur.canisapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.karapetyanarthur.canisapp.Activities.Fragments.EditMarkerMapFragment;
import com.karapetyanarthur.canisapp.Activities.Fragments.MapFragment;
import com.karapetyanarthur.canisapp.Activities.Fragments.ProfileFragment;
import com.karapetyanarthur.canisapp.Activities.Fragments.EditProfileFragment;
import com.karapetyanarthur.canisapp.Activities.Fragments.EditPetFragment;
import com.karapetyanarthur.canisapp.Activities.Fragments.PetFragment;
import com.karapetyanarthur.canisapp.R;
import com.yandex.mapkit.MapKitFactory;

public class NavigationActivity extends AppCompatActivity {

    Button pet_btn;
    Button map_btn;
    Button profile_btn;

    public static int api_is_initialized;
    public static int changed_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (api_is_initialized == 0){
            MapKitFactory.setApiKey("c79b2053-ca3c-453b-9709-fc9d680b8cf0");
            MapKitFactory.initialize(this);
            api_is_initialized = 1;
        }

        setContentView(R.layout.activity_navigation);

        pet_btn = findViewById(R.id.pet_btn);
        map_btn = findViewById(R.id.map_btn);
        profile_btn = findViewById(R.id.profile_btn);

        if (changed_fragment == 1){
            replaceFragment(new PetFragment());
        } else if(changed_fragment == 11){
            replaceFragment(new EditPetFragment());
        }else if (changed_fragment == 2){
            replaceFragment(new MapFragment());
        } else if (changed_fragment == 21){
            replaceFragment(new EditMarkerMapFragment());
        } else if(changed_fragment == 3){
            replaceFragment(new ProfileFragment());
        } else if(changed_fragment == 31){
            replaceFragment(new EditProfileFragment());
        }

        pet_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new PetFragment());
            }
        });

        map_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new MapFragment());
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

    public void changeActivity(String name_of_activity){
        Intent changeMyActivity = new Intent(name_of_activity);
        startActivity(changeMyActivity);
    }
}
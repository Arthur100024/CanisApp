package com.karapetyanarthur.canisapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.karapetyanarthur.canisapp.Activities.Fragments.EditMarkerMapFragment;
import com.karapetyanarthur.canisapp.Activities.Fragments.MapFragment;
import com.karapetyanarthur.canisapp.Activities.Fragments.ProfileFragment;
import com.karapetyanarthur.canisapp.Activities.Fragments.EditProfileFragment;
import com.karapetyanarthur.canisapp.Activities.Fragments.EditPetFragment;
import com.karapetyanarthur.canisapp.Activities.Fragments.PetFragment;
import com.karapetyanarthur.canisapp.R;
import com.karapetyanarthur.canisapp.databinding.ActivityNavigationBinding;
import com.yandex.mapkit.MapKitFactory;

public class NavigationActivity extends AppCompatActivity {

    ActivityNavigationBinding binding;
    BottomNavigationView bottom_nav_view;
    public static int api_is_initialized;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNavigationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        if (api_is_initialized == 0){
            MapKitFactory.setApiKey("c79b2053-ca3c-453b-9709-fc9d680b8cf0");
            MapKitFactory.initialize(this);
            api_is_initialized = 1;
        }
        setContentView(view);

        replaceFragment(new PetFragment());

        binding.bottomNavView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.pet:
                        replaceFragment(new PetFragment());
                        break;
                    case R.id.map:
                        replaceFragment(new MapFragment());
                        break;
                    case R.id.profile:
                        replaceFragment(new ProfileFragment());
                        break;
                }
                return true;
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
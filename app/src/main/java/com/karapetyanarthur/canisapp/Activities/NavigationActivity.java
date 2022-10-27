package com.karapetyanarthur.canisapp.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.karapetyanarthur.canisapp.Activities.Fragments.EditMarkerMapFragment;
import com.karapetyanarthur.canisapp.Activities.Fragments.MapFragment;
import com.karapetyanarthur.canisapp.Activities.Fragments.ProfileFragment;
import com.karapetyanarthur.canisapp.Activities.Fragments.EditProfileFragment;
import com.karapetyanarthur.canisapp.Activities.Fragments.EditPetFragment;
import com.karapetyanarthur.canisapp.Data.DBProfile;
import com.karapetyanarthur.canisapp.MyLocationListener;
import com.karapetyanarthur.canisapp.Activities.Fragments.PetFragment;
import com.karapetyanarthur.canisapp.R;
import com.karapetyanarthur.canisapp.ViewModel.AppViewModel;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Circle;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.CircleMapObject;
import com.yandex.mapkit.map.MapMode;
import com.yandex.mapkit.map.MapObject;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.map.MapObjectDragListener;
import com.yandex.mapkit.map.MapObjectTapListener;
import com.yandex.mapkit.map.MapType;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.mapkit.map.UserData;
import com.yandex.mapkit.map.internal.PlacemarkMapObjectBinding;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;

import java.util.List;

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
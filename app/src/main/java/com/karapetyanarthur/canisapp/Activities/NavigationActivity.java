package com.karapetyanarthur.canisapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.karapetyanarthur.canisapp.Activities.Fragments.ProfileFragment;
import com.karapetyanarthur.canisapp.Activities.Fragments.EditProfileFragment;
import com.karapetyanarthur.canisapp.MyLocationListener;
import com.karapetyanarthur.canisapp.R;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;

public class NavigationActivity extends AppCompatActivity {

    private MapView map_view;
    private final Point TARGET_LOCATION = new Point(MyLocationListener.my_latitude,MyLocationListener.my_longitude);

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

        map_view = (MapView) findViewById(R.id.mapview);

        map_view.setVisibility(View.GONE);


        if (changed_fragment == 1){
            map_view.setVisibility(View.GONE);
            //replaceFragment(new PetFragment());
        } else if (changed_fragment == 2){
            map_view.setVisibility(View.VISIBLE);
        } else if(changed_fragment == 3){
            map_view.setVisibility(View.GONE);
            replaceFragment(new ProfileFragment());
        } else if(changed_fragment == 31){
            map_view.setVisibility(View.GONE);
            replaceFragment(new EditProfileFragment());
        }

        pet_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map_view.setVisibility(View.GONE);
                //replaceFragment(new PetFragment());
            }
        });

        map_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map_view.setVisibility(View.VISIBLE);
                getAllMapMarkers();
            }
        });

        profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map_view.setVisibility(View.GONE);
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

    public void getAllMapMarkers(){
//ПЕРЕМЕЩЕНИЕ КАМЕРЫ
        map_view.getMap().move(
                new CameraPosition(TARGET_LOCATION, 18.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 8f),
                null);

//ДОБАВЛЕНИЕ МАРКЕРА
        map_view.getMap().getMapObjects().addPlacemark(TARGET_LOCATION, ImageProvider.fromBitmap(drawSimpleBitmap("Я")));

    }

    @Override
    public void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        map_view.onStart();
    }

    @Override
    public void onStop() {
        map_view.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    //БИТМАП ДЛЯ МАРКЕРА
    public Bitmap drawSimpleBitmap(String number) {
        int picSize = 100;
        Bitmap bitmap = Bitmap.createBitmap(picSize, picSize, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        // отрисовка плейсмарка
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.activity_background_clr));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(picSize / 2, picSize / 2, picSize / 2, paint);
        // отрисовка текста
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(number, picSize / 2,
                picSize / 2 - ((paint.descent() + paint.ascent()) / 2), paint);
        return bitmap;
    }
}
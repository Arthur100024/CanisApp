package com.karapetyanarthur.canisapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.karapetyanarthur.canisapp.MyLocationListener;
import com.karapetyanarthur.canisapp.R;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;

public class MapSetLocActivity extends AppCompatActivity {

    private MapView map_view;
    private final Point TARGET_LOCATION = new Point(MyLocationListener.my_latitude,MyLocationListener.my_longitude);

    Button apply_loc_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MapKitFactory.setApiKey("c79b2053-ca3c-453b-9709-fc9d680b8cf0");
        MapKitFactory.initialize(this);

        setContentView(R.layout.activity_map_set_loc);


        apply_loc_btn = findViewById(R.id.apply_loc_btn);

        map_view = (MapView)findViewById(R.id.mapview);

        map_view.getMap().move(
                new CameraPosition(TARGET_LOCATION, 18.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 10f),
                null);

        map_view.getMap().getMapObjects().addPlacemark(TARGET_LOCATION, ImageProvider.fromResource(R.drawable.hide_password_icon));


        apply_loc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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
}
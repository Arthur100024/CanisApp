package com.karapetyanarthur.canisapp.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
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
import com.karapetyanarthur.canisapp.Activities.Fragments.ProfileFragment;
import com.karapetyanarthur.canisapp.Activities.Fragments.EditProfileFragment;
import com.karapetyanarthur.canisapp.Activities.Fragments.EditPetFragment;
import com.karapetyanarthur.canisapp.MyLocationListener;
import com.karapetyanarthur.canisapp.Activities.Fragments.PetFragment;
import com.karapetyanarthur.canisapp.R;
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

public class NavigationActivity extends AppCompatActivity {

    private MapView map_view;
    private MapView edit_marker_mapview;
    private final Point TARGET_LOCATION = new Point(MyLocationListener.my_latitude,MyLocationListener.my_longitude);

    Button pet_btn;
    Button map_btn;
    Button profile_btn;

    Button edit_marker_btn;

    public static int api_is_initialized;
    public static int changed_fragment;


    private MapObjectCollection myMarkerMapObject;
    private MapObjectCollection clientCollection;
    private MapObjectCollection cynologistCollection;

    PlacemarkMapObject myPlacemark;
    PlacemarkMapObject clientPlacemark;
    PlacemarkMapObject cynologistPlacemark;

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

        edit_marker_btn = findViewById(R.id.edit_marker_btn);

        map_view = (MapView) findViewById(R.id.mapview);
        edit_marker_mapview = (MapView) findViewById(R.id.edit_marker_mapview);

        edit_marker_mapview.onStart();
        map_view.onStart();

        map_view.setVisibility(View.GONE);
        edit_marker_mapview.setVisibility(View.GONE);
        edit_marker_btn.setVisibility(View.GONE);



        if (changed_fragment == 1){
            map_view.setVisibility(View.GONE);
            edit_marker_btn.setVisibility(View.GONE);
            edit_marker_mapview.setVisibility(View.GONE);
            replaceFragment(new PetFragment());
        } else if(changed_fragment == 11){
            map_view.setVisibility(View.GONE);
            edit_marker_btn.setVisibility(View.GONE);
            edit_marker_mapview.setVisibility(View.GONE);
            replaceFragment(new EditPetFragment());
        }else if (changed_fragment == 2){
            map_view.setVisibility(View.VISIBLE);
            edit_marker_btn.setVisibility(View.VISIBLE);
            edit_marker_mapview.setVisibility(View.GONE);
        } else if (changed_fragment == 21){
            map_view.setVisibility(View.GONE);
            edit_marker_mapview.setVisibility(View.VISIBLE);
        } else if(changed_fragment == 3){
            map_view.setVisibility(View.GONE);
            edit_marker_btn.setVisibility(View.GONE);
            edit_marker_mapview.setVisibility(View.GONE);
            replaceFragment(new ProfileFragment());
        } else if(changed_fragment == 31){
            map_view.setVisibility(View.GONE);
            edit_marker_btn.setVisibility(View.GONE);
            edit_marker_mapview.setVisibility(View.GONE);
            replaceFragment(new EditProfileFragment());
        }

        pet_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map_view.setVisibility(View.GONE);
                edit_marker_btn.setVisibility(View.GONE);
                edit_marker_mapview.setVisibility(View.GONE);
                replaceFragment(new PetFragment());
            }
        });

        map_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map_view.setVisibility(View.VISIBLE);
                edit_marker_btn.setVisibility(View.VISIBLE);
                edit_marker_mapview.setVisibility(View.GONE);
                getAllMapMarkers();
            }
        });

        profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map_view.setVisibility(View.GONE);
                edit_marker_btn.setVisibility(View.GONE);
                edit_marker_mapview.setVisibility(View.GONE);
                replaceFragment(new ProfileFragment());
            }
        });

        edit_marker_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map_view.setVisibility(View.GONE);
                edit_marker_btn.setVisibility(View.GONE);
                edit_marker_mapview.setVisibility(View.VISIBLE);
                changeMapMarker();
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
//МОЙ МАРКЕР ___ НАЧАЛО
        myMarkerMapObject = map_view.getMap().getMapObjects().addCollection();
//ПЕРЕМЕЩЕНИЕ КАМЕРЫ
        map_view.getMap().move(
                new CameraPosition(TARGET_LOCATION, 18.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 8f),
                null);

//ДОБАВЛЕНИЕ МАРКЕРА
        myPlacemark = myMarkerMapObject.addPlacemark(TARGET_LOCATION);
        myPlacemark.setIcon(ImageProvider.fromBitmap(drawMyBitmap("Я")));
        myPlacemark.setUserData(new markerMapObjectUserData("Arthur", "Karapetyan","89167441755"));
        myPlacemark.addTapListener(markerMapObjectTapListener);
//МОЙ МАРКЕР ___ КОНЕЦ



//МАРКЕР КЛИЕНТОВ ___ НАЧАЛО
        clientCollection = map_view.getMap().getMapObjects().addCollection();

        clientPlacemark = clientCollection.addPlacemark(new Point(MyLocationListener.my_latitude+0.0003,MyLocationListener.my_longitude+0.0003));
        clientPlacemark.setIcon(ImageProvider.fromBitmap(drawClientBitmap()));
        clientPlacemark.setUserData(new markerMapObjectUserData(" ", " "," "));
        clientPlacemark.addTapListener(markerMapObjectTapListener);
//МАРКЕР КЛИЕНТОВ ___ НАЧАЛО



//МАРКЕР КИНОЛОГОВ ___ НАЧАЛО
        cynologistCollection = map_view.getMap().getMapObjects().addCollection();

        cynologistPlacemark = cynologistCollection.addPlacemark(new Point(12,12));
        cynologistPlacemark.setIcon(ImageProvider.fromBitmap(drawCynologistBitmap()));
        cynologistPlacemark.setUserData(new markerMapObjectUserData(" ", " "," "));
        cynologistPlacemark.addTapListener(markerMapObjectTapListener);
//МАРКЕР КИНОЛОГОВ ___ КОНЕЦ
    }


//ИЗМЕНЕНИЕ МЕСТОПОЛОЖЕНИЯ ___ НАЧАЛО
    public void changeMapMarker(){

        myMarkerMapObject = edit_marker_mapview.getMap().getMapObjects().addCollection();
        edit_marker_mapview.getMap().move(
                new CameraPosition(TARGET_LOCATION, 18.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 8f),
                null);

        myPlacemark = myMarkerMapObject.addPlacemark(TARGET_LOCATION);
        myPlacemark.setIcon(ImageProvider.fromBitmap(drawMyBitmap("Я")));
        myPlacemark.isDraggable();
        myPlacemark.setDraggable(true);
        myPlacemark.setDragListener(myMarkerDragListener);

    }


    public MapObjectDragListener myMarkerDragListener = new MapObjectDragListener() {
        @Override
        public void onMapObjectDragStart(@NonNull MapObject mapObject) {

        }

        @Override
        public void onMapObjectDrag(@NonNull MapObject mapObject, @NonNull Point point) {

        }

        @Override
        public void onMapObjectDragEnd(@NonNull MapObject mapObject) {
            MyLocationListener.my_latitude = 3;
            MyLocationListener.my_longitude = 3;
        }
    };
//ИЗМЕНЕНИЕ МЕСТОПОЛОЖЕНИЯ ___ КОНЕЦ

    @Override
    public void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
    }

    @Override
    public void onStop() {
        edit_marker_mapview.onStop();
        map_view.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    public class markerMapObjectUserData {
        final String profile_name;
        final String profile_surname;
        final String profile_phone;

        markerMapObjectUserData(String profile_name, String profile_surname, String profile_phone) {
            this.profile_name = profile_name;
            this.profile_surname = profile_surname;
            this.profile_phone = profile_phone;
        }
    }

    private MapObjectTapListener markerMapObjectTapListener = new MapObjectTapListener() {
        @Override
        public boolean onMapObjectTap(MapObject mapObject, Point point) {

            //String name_find_profile_str = ;

            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                    NavigationActivity.this,R.style.BottomSheetDialogTheme
            );

            View bottomSheetView = LayoutInflater.from(getApplicationContext())
                    .inflate(R.layout.layout_bottom_sheet, findViewById(R.id.bottom_sheet_container));

            TextView name_find_profile_tv = (TextView) bottomSheetView.findViewById(R.id.name_find_profile_tv);
            name_find_profile_tv.setText(" ");

            TextView surname_find_profile_tv = (TextView) bottomSheetView.findViewById(R.id.surname_find_profile_tv);
            surname_find_profile_tv.setText(" ");

            TextView number_find_profile_tv = (TextView) bottomSheetView.findViewById(R.id.number_find_profile_tv);
            number_find_profile_tv.setText(" ");

            ImageButton to_WhatsApp_btn = (ImageButton) bottomSheetView.findViewById(R.id.to_WhatsApp_btn);
            to_WhatsApp_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = "https://api.whatsapp.com/send?phone="+number_find_profile_tv.getText().toString();
                    Intent WhatsAppIntent = new Intent(Intent.ACTION_VIEW);
                    WhatsAppIntent.setData(Uri.parse(url));
                    startActivity(WhatsAppIntent);
                }
            });

            bottomSheetDialog.setContentView(bottomSheetView);
            bottomSheetDialog.show();

            return true;
        }
    };


//БИТМАП ДЛЯ МОЕГО МАРКЕРА
    public Bitmap drawMyBitmap(String number) {
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

//БИТМАП ДЛЯ МАРКЕРА ЗАКАЗЧИКА
    public Bitmap drawClientBitmap(){
        Bitmap clientBitmapFactory = BitmapFactory.decodeResource(getResources(), R.drawable.profile_icon);
        Bitmap clientBitmap = Bitmap.createScaledBitmap(clientBitmapFactory,80,80,false);
        return clientBitmap;
    }

//БИТМАП ДЛЯ МАРКЕРА КИНОЛОГА
    public Bitmap drawCynologistBitmap(){
        Bitmap clientBitmapFactory = BitmapFactory.decodeResource(getResources(), R.drawable.dog_icon);
        Bitmap clientBitmap = Bitmap.createScaledBitmap(clientBitmapFactory,80,80,false);
        return clientBitmap;
    }
}
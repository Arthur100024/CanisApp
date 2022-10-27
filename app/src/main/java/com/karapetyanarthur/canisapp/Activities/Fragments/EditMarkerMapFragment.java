package com.karapetyanarthur.canisapp.Activities.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.karapetyanarthur.canisapp.Activities.NavigationActivity;
import com.karapetyanarthur.canisapp.MyLocationListener;
import com.karapetyanarthur.canisapp.R;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.MapObject;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.map.MapObjectDragListener;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;

public class EditMarkerMapFragment extends Fragment {

    private MapView edit_marker_mapview;
    Button save_marker_change;
    private final Point TARGET_LOCATION = new Point(MyLocationListener.my_latitude,MyLocationListener.my_longitude);

    private MapObjectCollection myMarkerMapObject;
    PlacemarkMapObject myPlacemark;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (NavigationActivity.api_is_initialized == 0){
            MapKitFactory.setApiKey("c79b2053-ca3c-453b-9709-fc9d680b8cf0");
            MapKitFactory.initialize(this.getActivity());
            NavigationActivity.api_is_initialized = 1;
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_marker_map, container, false);

        edit_marker_mapview = (MapView) view.findViewById(R.id.edit_marker_mapview);

        changeMapMarker();

        save_marker_change = view.findViewById(R.id.save_marker_change);
        save_marker_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavigationActivity.changed_fragment = 2;
                changeActivity(".NavigationActivity");
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        edit_marker_mapview.onStart();
    }

    @Override
    public void onStop() {
        edit_marker_mapview.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    public void changeActivity(String name_of_activity){
        Intent changeMyActivity = new Intent(name_of_activity);
        changeMyActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(changeMyActivity);
    }

//ИЗМЕНЕНИЕ МЕСТОПОЛОЖЕНИЯ ___ НАЧАЛО
    public void changeMapMarker(){

        myMarkerMapObject = edit_marker_mapview.getMap().getMapObjects().addCollection();

        myPlacemark = myMarkerMapObject.addPlacemark(TARGET_LOCATION);
        myPlacemark.setIcon(ImageProvider.fromBitmap(drawMyBitmap("Я")));
        myPlacemark.isDraggable();
        myPlacemark.setDraggable(true);
        myPlacemark.setDragListener(myMarkerDragListener);



        edit_marker_mapview.getMap().move(
                new CameraPosition(myPlacemark.getGeometry(), 18.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 8f),
                null);

    }

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

    public MapObjectDragListener myMarkerDragListener = new MapObjectDragListener() {
        @Override
        public void onMapObjectDragStart(@NonNull MapObject mapObject) {

        }

        @Override
        public void onMapObjectDrag(@NonNull MapObject mapObject, @NonNull Point point) {

        }

        @Override
        public void onMapObjectDragEnd(@NonNull MapObject mapObject) {
            /*MyLocationListener.my_latitude = 3;
            MyLocationListener.my_longitude = 3;*/
            MyLocationListener.my_latitude = mapObject.getZIndex();
            //MyLocationListener.my_longitude =
        }
    };
//ИЗМЕНЕНИЕ МЕСТОПОЛОЖЕНИЯ ___ КОНЕЦ
}
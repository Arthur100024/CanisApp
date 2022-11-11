package com.karapetyanarthur.canisapp.Activities.Registration;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karapetyanarthur.canisapp.Activities.NavigationActivity;
import com.karapetyanarthur.canisapp.MyLocationListener;
import com.karapetyanarthur.canisapp.R;
import com.karapetyanarthur.canisapp.databinding.FragmentMapSetLocBinding;
import com.karapetyanarthur.canisapp.databinding.FragmentPermissionsBinding;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.runtime.image.ImageProvider;

public class MapSetLocFragment extends Fragment {

    FragmentMapSetLocBinding binding;
    private final Point TARGET_LOCATION = new Point(MyLocationListener.my_latitude,MyLocationListener.my_longitude);

    private SharedPreferences SharedPrefs;
    public static final String LOGGED = "IsUserLogged";

    public static MapSetLocFragment newInstance() {
        return new MapSetLocFragment();
    }

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
        binding = FragmentMapSetLocBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        SharedPrefs = getActivity().getSharedPreferences("prefs",MODE_PRIVATE);

        binding.mapView.getMap().move(
                new CameraPosition(TARGET_LOCATION, 18.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 8f),
                null);

//ДОБАВЛЕНИЕ МАРКЕРА
        binding.mapView.getMap().getMapObjects().addPlacemark(TARGET_LOCATION, ImageProvider.fromBitmap(drawSimpleBitmap("Я")));


        binding.applyLocBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editorLogged = SharedPrefs.edit();
                editorLogged.putString(LOGGED,"1");
                editorLogged.apply();
                NavigationActivity.api_is_initialized = 1;
                changeActivity(".NavigationActivity");
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        binding.mapView.onStart();
    }

    @Override
    public void onStop() {
        binding.mapView.onStop();
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

    public void changeActivity(String name_of_activity){
        Intent changeMyActivity = new Intent(name_of_activity);
        startActivity(changeMyActivity);
    }
}
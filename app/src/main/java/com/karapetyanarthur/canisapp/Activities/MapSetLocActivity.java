package com.karapetyanarthur.canisapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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

    private SharedPreferences SharedPrefs;
    public static final String LOGGED = "IsUserLogged";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MapKitFactory.setApiKey("c79b2053-ca3c-453b-9709-fc9d680b8cf0");
        MapKitFactory.initialize(this);

        setContentView(R.layout.activity_map_set_loc);

        {
            apply_loc_btn = findViewById(R.id.apply_loc_btn);
            map_view = (MapView) findViewById(R.id.mapview);
            SharedPrefs = getSharedPreferences("prefs",MODE_PRIVATE);
        }

        /*map_view.getMap().getMode().*/
//ПЕРЕМЕЩЕНИЕ КАМЕРЫ
        map_view.getMap().move(
                new CameraPosition(TARGET_LOCATION, 18.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 10f),
                null);

//ДОБАВЛЕНИЕ МАРКЕРА
        map_view.getMap().getMapObjects().addPlacemark(TARGET_LOCATION, ImageProvider.fromBitmap(drawSimpleBitmap("Я")));


        apply_loc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editorLogged = SharedPrefs.edit();
                editorLogged.putString(LOGGED,"1");
                editorLogged.apply();
                NavigationActivity.changed_fragment = 3;
                changeActivity(".NavigationActivity");
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
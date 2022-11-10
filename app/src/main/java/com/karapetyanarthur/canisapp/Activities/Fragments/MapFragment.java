package com.karapetyanarthur.canisapp.Activities.Fragments;

import static com.yandex.runtime.Runtime.getApplicationContext;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.karapetyanarthur.canisapp.Activities.NavigationActivity;
import com.karapetyanarthur.canisapp.Data.Model.PetModel;
import com.karapetyanarthur.canisapp.Data.Model.ProfileModel;
import com.karapetyanarthur.canisapp.MyLocationListener;
import com.karapetyanarthur.canisapp.R;
import com.karapetyanarthur.canisapp.ViewModel.MapViewModel;
import com.karapetyanarthur.canisapp.ViewModel.ProfileViewModel;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.MapObject;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.map.MapObjectTapListener;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;

import java.util.List;

public class MapFragment extends Fragment {

    private MapView map_view;
    Button edit_marker_btn;
    private final Point TARGET_LOCATION = new Point(MyLocationListener.my_latitude,MyLocationListener.my_longitude);

    private MapObjectCollection myMarkerMapObject;
    private MapObjectCollection clientCollection;
    private MapObjectCollection cynologistCollection;

    PlacemarkMapObject myPlacemark;
    Uri my_image_bottom_sheet;
    String my_name_bottom_sheet;
    String my_surname_bottom_sheet;
    String my_phone_bottom_sheet;
    Uri my_pet_image_bottom_sheet;
    String my_pet_nickname_bottom_sheet;
    String my_pet_breed_bottom_sheet;
    String my_pet_age_bottom_sheet;

    PlacemarkMapObject clientPlacemark;
    PlacemarkMapObject cynologistPlacemark;

    MapViewModel model;

    View bottomSheetView;
    BottomSheetDialog bottomSheetDialog;

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


        View view = inflater.inflate(R.layout.fragment_map, container, false);

        edit_marker_btn = view.findViewById(R.id.edit_marker_btn);
        map_view = (MapView) view.findViewById(R.id.mapview);


        model = new ViewModelProvider(this).get(MapViewModel.class);

        model.getAllProfile().observe(getViewLifecycleOwner(), new Observer<List<ProfileModel>>() {
            @Override
            public void onChanged(List<ProfileModel> profileModels) {
                if (profileModels.size() != 0){
                    my_name_bottom_sheet = profileModels.get(profileModels.size() - 1).getName();
                    my_surname_bottom_sheet = profileModels.get(profileModels.size() - 1).getSurname();
                    my_phone_bottom_sheet = profileModels.get(profileModels.size() - 1).getPhone();
                    if (profileModels.get(profileModels.size() - 1).getImage() != null){
                        my_image_bottom_sheet = Uri.parse(profileModels.get(profileModels.size() - 1).getImage());
                    }
                }
            }
        });

        model.getAllPet().observe(getViewLifecycleOwner(), new Observer<List<PetModel>>() {
            @Override
            public void onChanged(List<PetModel> petModels) {
                if (petModels.size() != 0){
                    my_pet_nickname_bottom_sheet = petModels.get(petModels.size() - 1).getNickname();
                    my_pet_breed_bottom_sheet = petModels.get(petModels.size() - 1).getBreed();
                    my_pet_age_bottom_sheet = petModels.get(petModels.size() - 1).getAge();
                    if (petModels.get(petModels.size() - 1).getImage() != null){
                        my_pet_image_bottom_sheet = Uri.parse(petModels.get(petModels.size() - 1).getImage());
                    }
                }
            }
        });


        getAllMapMarkers();

        bottomSheetView = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.layout_bottom_sheet, view.findViewById(R.id.bottom_sheet_container));

        bottomSheetDialog = new BottomSheetDialog(
                this.getActivity(), R.style.BottomSheetDialogTheme);

        edit_marker_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NavigationActivity.changed_fragment = 21;
                changeActivity(".NavigationActivity");
            }
        });

        return view;
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

    public void changeActivity(String name_of_activity){
        Intent changeMyActivity = new Intent(name_of_activity);
        changeMyActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(changeMyActivity);
    }

    public void getAllMapMarkers(){

//ПЕРЕМЕЩЕНИЕ КАМЕРЫ
        map_view.getMap().move(
                new CameraPosition(TARGET_LOCATION, 18.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 8f),
                null);


//МОЙ МАРКЕР ___ НАЧАЛО
        myMarkerMapObject = map_view.getMap().getMapObjects().addCollection();

        myPlacemark = myMarkerMapObject.addPlacemark(TARGET_LOCATION);
        myPlacemark.setIcon(ImageProvider.fromBitmap(drawMyBitmap("Я")));
        myPlacemark.setUserData(new markerMapObjectUserData("Arthur", "Karapetyan","89167441755"));
        myPlacemark.addTapListener(MyMarkerMapObjectTapListener);
//МОЙ МАРКЕР ___ КОНЕЦ



//МАРКЕР КЛИЕНТОВ ___ НАЧАЛО
        clientCollection = map_view.getMap().getMapObjects().addCollection();

        clientPlacemark = clientCollection.addPlacemark(new Point(10,10));
        clientPlacemark.setIcon(ImageProvider.fromBitmap(drawClientBitmap()));
        clientPlacemark.setUserData(new markerMapObjectUserData(" ", " "," "));
        //clientPlacemark.addTapListener(markerMapObjectTapListener);
//МАРКЕР КЛИЕНТОВ ___ НАЧАЛО



//МАРКЕР КИНОЛОГОВ ___ НАЧАЛО
        cynologistCollection = map_view.getMap().getMapObjects().addCollection();

        cynologistPlacemark = cynologistCollection.addPlacemark(new Point(12,12));
        cynologistPlacemark.setIcon(ImageProvider.fromBitmap(drawCynologistBitmap()));
        cynologistPlacemark.setUserData(new markerMapObjectUserData(" ", " "," "));
        //cynologistPlacemark.addTapListener(markerMapObjectTapListener);
//МАРКЕР КИНОЛОГОВ ___ КОНЕЦ
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

    private MapObjectTapListener MyMarkerMapObjectTapListener = new MapObjectTapListener() {
        @Override
        public boolean onMapObjectTap(MapObject mapObject, Point point) {

            ImageView image_profile_iv = (ImageView) bottomSheetView.findViewById(R.id.profile_image_iv);
            image_profile_iv.setBackground(null);
            image_profile_iv.setImageURI(my_image_bottom_sheet);

            TextView name_profile_tv = (TextView) bottomSheetView.findViewById(R.id.name_profile_tv);
            name_profile_tv.setText(my_name_bottom_sheet);

            TextView surname_profile_tv = (TextView) bottomSheetView.findViewById(R.id.surname_profile_tv);
            surname_profile_tv.setText(my_surname_bottom_sheet);

            TextView number_profile_tv = (TextView) bottomSheetView.findViewById(R.id.number_profile_tv);
            number_profile_tv.setText(my_phone_bottom_sheet);

            ImageButton to_WhatsApp_btn = (ImageButton) bottomSheetView.findViewById(R.id.to_WhatsApp_btn);
            to_WhatsApp_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri whatsapp_uri = Uri.parse("https://api.whatsapp.com/send?phone="+number_profile_tv.getText().toString());
                    Intent WhatsAppIntent = new Intent(Intent.ACTION_VIEW, whatsapp_uri);
                    startActivity(WhatsAppIntent);
                }
            });

            ImageButton to_Telegram_btn = (ImageButton) bottomSheetView.findViewById(R.id.to_Telegram_btn);
            to_Telegram_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri telegram_uri = Uri.parse("https://telegram.me/"+number_profile_tv.getText().toString());
                    Intent TelegramIntent = new Intent(Intent.ACTION_VIEW, telegram_uri).setPackage("org.telegram.messenger");
                    startActivity(TelegramIntent);
                }
            });

            ImageView image_pet_iv = (ImageView) bottomSheetView.findViewById(R.id.pet_image_iv);
            image_pet_iv.setBackground(null);
            image_pet_iv.setImageURI(my_pet_image_bottom_sheet);

            TextView nickname_pet_tv = (TextView) bottomSheetView.findViewById(R.id.nickname_pet_tv);
            nickname_pet_tv.setText(my_pet_nickname_bottom_sheet);

            TextView breed_pet_tv = (TextView) bottomSheetView.findViewById(R.id.breed_pet_tv);
            breed_pet_tv.setText(my_pet_breed_bottom_sheet);

            TextView age_pet_tv = (TextView) bottomSheetView.findViewById(R.id.age_pet_tv);
            age_pet_tv.setText(my_pet_age_bottom_sheet);

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
package com.karapetyanarthur.canisapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.karapetyanarthur.canisapp.MyLocationListener;
import com.karapetyanarthur.canisapp.R;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences SharedPrefs;
    public static final String LOGGED = "IsUserLogged";

    //AppViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPrefs = getSharedPreferences("prefs",MODE_PRIVATE);

        /*model = new ViewModelProvider(this).get(AppViewModel.class);

        model.insert(new DBProfile(0,"justfrog44@gmail.com",
                "Arthur","Karapetyan",
                "89167441755","20"));*/

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (SharedPrefs.getString(LOGGED, "0").equals("0")){
            changeActivity(".EnterActivity");
        } else if (SharedPrefs.getString(LOGGED, "0").equals("1")){
            MyLocationListener.SetUpLocationListener(this);
            NavigationActivity.changed_fragment = 3;
            NavigationActivity.api_is_initialized = 0;
            changeActivity(".NavigationActivity");
        }

    }

    public void changeActivity(String name_of_activity){
        Intent changeMyActivity = new Intent(name_of_activity);
        startActivity(changeMyActivity);
    }
}
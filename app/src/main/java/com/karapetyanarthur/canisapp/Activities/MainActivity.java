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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPrefs = getSharedPreferences("prefs",MODE_PRIVATE);



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
            changeActivity(".NavigationActivity");
        }

    }

    public void changeActivity(String name_of_activity){
        Intent changeMyActivity = new Intent(name_of_activity);
        startActivity(changeMyActivity);
    }
}
package com.karapetyanarthur.canisapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.karapetyanarthur.canisapp.MyLocationListener;
import com.karapetyanarthur.canisapp.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences SharedPrefs;
    public static final String LOGGED = "IsUserLogged";

    //AppViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri uri = getIntent().getData();
        if(uri != null) {
            List<String> params = uri.getPathSegments();
            String id = params.get(params.size() - 1);
            Toast.makeText(this, "id="+id,Toast.LENGTH_SHORT).show();
        }
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
            NavigationActivity.api_is_initialized = 0;
            changeActivity(".NavigationActivity");
        }

    }

    public void changeActivity(String name_of_activity){
        Intent changeMyActivity = new Intent(name_of_activity);
        startActivity(changeMyActivity);
    }
}
package com.karapetyanarthur.canisapp.Data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AppRepository {
    private ProfileDAO profileDAO;
    private LiveData<List<DBProfile>> allProfile;

    public AppRepository(Application application){
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        profileDAO = appDatabase.getProfileDao();
        allProfile = profileDAO.getAllProfileLive();
    }

    // Methods for the local database
    public void insert(DBProfile profile){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            profileDAO.insertProfile(profile);
        });
    }

    public void update(DBProfile profile){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            profileDAO.updateProfile(profile);
        });
    }

    public void delete(DBProfile profile){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            profileDAO.deleteProfile(profile);
        });
    }

    public LiveData<List<DBProfile>> getAllProfile(){
        return allProfile;
    }

}

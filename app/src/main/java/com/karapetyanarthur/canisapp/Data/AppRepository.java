package com.karapetyanarthur.canisapp.Data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.karapetyanarthur.canisapp.Model.ProfileModel;

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
    public void insert(ProfileModel profile){
        DBProfile dbProfile = DBProfile.convertFromProfile(profile);
        AppDatabase.databaseWriteExecutor.execute(() -> {
            profileDAO.insertProfile(dbProfile);
        });
    }

    public void update(ProfileModel profile){
        DBProfile dbProfile = DBProfile.convertFromProfile(profile);
        AppDatabase.databaseWriteExecutor.execute(() -> {
            profileDAO.updateProfile(dbProfile);
        });
    }

    public void delete(ProfileModel profile){
        DBProfile dbProfile = DBProfile.convertFromProfile(profile);
        AppDatabase.databaseWriteExecutor.execute(() -> {
            profileDAO.deleteProfile(dbProfile);
        });
    }

    public LiveData<List<DBProfile>> getAllProfile(){
        return allProfile;
    }


}

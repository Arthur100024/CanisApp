package com.karapetyanarthur.canisapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.karapetyanarthur.canisapp.Data.AppRepository;
import com.karapetyanarthur.canisapp.Data.DBProfile;
import com.karapetyanarthur.canisapp.Data.ProfileDAO;

import java.util.List;

public class AppViewModel extends AndroidViewModel {

    private final AppRepository appRepository;
    private LiveData<List<DBProfile>> getAllProfile;
    private DBProfile dbProfileMV;

    public AppViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
        getAllProfile = appRepository.getAllProfile();
    }

    // Methods ViewModel for the local database
    public void insert(DBProfile profile){
        appRepository.insert(profile);
    }

    public void update(DBProfile profile){
        appRepository.update(profile);
    }

    public void delete(DBProfile profile){
        appRepository.delete(profile);
    }

    public LiveData<List<DBProfile>> getAllProfile(){
        return getAllProfile;
    }



    public DBProfile getProfileMV() {
        return dbProfileMV;
    }

    public void setProfileMV(DBProfile dbProfile) {
        dbProfileMV = dbProfile;
    }

}

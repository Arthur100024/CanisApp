package com.karapetyanarthur.canisapp.Data.Repository;

import android.app.Application;
import android.os.Build;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.karapetyanarthur.canisapp.Data.AppDatabase;
import com.karapetyanarthur.canisapp.Data.DBPet;
import com.karapetyanarthur.canisapp.Data.DBProfile;
import com.karapetyanarthur.canisapp.Data.Model.PetModel;
import com.karapetyanarthur.canisapp.Data.Model.ProfileModel;
import com.karapetyanarthur.canisapp.Data.PetDAO;
import com.karapetyanarthur.canisapp.Data.ProfileDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProfileRepository {
    private ProfileDAO profileDAO;
    private LiveData<List<DBProfile>> allProfile;

    public ProfileRepository(Application application){
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        profileDAO = appDatabase.getProfileDao();
    }

    // ProfileROOM
    public void insertProfile(ProfileModel profile){
        DBProfile dbProfile = DBProfile.convertToDBProfile(profile);
        AppDatabase.databaseWriteExecutor.execute(() -> {
            profileDAO.insertProfile(dbProfile);
        });
    }

    public void updateProfile(ProfileModel profile){
        DBProfile dbProfile = DBProfile.convertToDBProfile(profile);
        AppDatabase.databaseWriteExecutor.execute(() -> {
            profileDAO.updateProfile(dbProfile);
        });
    }

    public void deleteProfile(ProfileModel profile){
        DBProfile dbProfile = DBProfile.convertToDBProfile(profile);
        AppDatabase.databaseWriteExecutor.execute(() -> {
            profileDAO.deleteProfile(dbProfile);
        });
    }

    public LiveData<List<ProfileModel>> getAllProfile(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Transformations.map(profileDAO.getAllProfileLive(), dbProfiles -> dbProfiles.stream().map(dbProfile ->
                    dbProfile.convertToProfile()).collect(Collectors.toList()));
        }
        return new MutableLiveData<List<ProfileModel>>(new ArrayList<>());
    }

}

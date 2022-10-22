package com.karapetyanarthur.canisapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.karapetyanarthur.canisapp.Data.AppRepository;
import com.karapetyanarthur.canisapp.Data.DBPet;
import com.karapetyanarthur.canisapp.Data.DBProfile;
import com.karapetyanarthur.canisapp.Data.ProfileDAO;
import com.karapetyanarthur.canisapp.Model.PetModel;
import com.karapetyanarthur.canisapp.Model.ProfileModel;

import java.util.List;

public class AppViewModel extends AndroidViewModel {

    private final AppRepository appRepository;
    private LiveData<List<DBProfile>> getAllProfile;
    private DBProfile dbProfileMV;

    private LiveData<List<DBPet>> getAllPet;
    private DBPet dbPetMV;

    public AppViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
        getAllProfile = appRepository.getAllProfile();
        getAllPet = appRepository.getAllPet();
    }

    // Profile ViewModel
    public void insert(ProfileModel profile){
        appRepository.insert(profile);
    }

    public void update(ProfileModel profile){
        appRepository.update(profile);
    }

    public void delete(ProfileModel profile){
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



    // Profile ViewModel

    public void insert(PetModel pet){
        appRepository.insert(pet);
    }

    public void update(PetModel pet){
        appRepository.update(pet);
    }

    public void delete(PetModel pet){
        appRepository.delete(pet);
    }

    public LiveData<List<DBPet>> getAllPet(){
        return getAllPet;
    }
}

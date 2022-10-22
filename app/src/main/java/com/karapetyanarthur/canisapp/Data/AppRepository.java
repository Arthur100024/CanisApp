package com.karapetyanarthur.canisapp.Data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.karapetyanarthur.canisapp.Model.PetModel;
import com.karapetyanarthur.canisapp.Model.ProfileModel;

import java.util.List;

public class AppRepository {
    private ProfileDAO profileDAO;
    private PetDAO petDAO;
    private LiveData<List<DBProfile>> allProfile;

    public AppRepository(Application application){
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        profileDAO = appDatabase.getProfileDao();
        petDAO = appDatabase.getPetDAO();
    }

    // ProfileROOM
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
        return profileDAO.getAllProfileLive();
    }



    // PetROOM
    public void insert(PetModel pet){
        DBPet dbPet = DBPet.convertFromPet(pet);
        AppDatabase.databaseWriteExecutor.execute(() -> {
            petDAO.insertPet(dbPet);
        });
    }

    public void update(PetModel pet){
        DBPet dbPet = DBPet.convertFromPet(pet);
        AppDatabase.databaseWriteExecutor.execute(() -> {
            petDAO.updatePet(dbPet);
        });
    }

    public void delete(PetModel pet){
        DBPet dbPet = DBPet.convertFromPet(pet);
        AppDatabase.databaseWriteExecutor.execute(() -> {
            petDAO.deletePet(dbPet);
        });
    }

    public LiveData<List<DBPet>> getAllPet(){
        return petDAO.getAllPetLive();
    }

}

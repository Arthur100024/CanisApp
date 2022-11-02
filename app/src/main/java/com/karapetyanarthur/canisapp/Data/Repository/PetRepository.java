package com.karapetyanarthur.canisapp.Data.Repository;

import android.app.Application;
import android.os.Build;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.karapetyanarthur.canisapp.Data.AppDatabase;
import com.karapetyanarthur.canisapp.Data.DBPet;
import com.karapetyanarthur.canisapp.Data.Model.PetModel;
import com.karapetyanarthur.canisapp.Data.Model.ProfileModel;
import com.karapetyanarthur.canisapp.Data.PetDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PetRepository {
    private PetDAO petDAO;

    public PetRepository(Application application){
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        petDAO = appDatabase.getPetDAO();
    }

    public void insertPet(PetModel pet){
        DBPet dbPet = DBPet.convertToDBPet(pet);
        AppDatabase.databaseWriteExecutor.execute(() -> {
            petDAO.insertPet(dbPet);
        });
    }

    public void updatePet(PetModel pet){
        DBPet dbPet = DBPet.convertToDBPet(pet);
        AppDatabase.databaseWriteExecutor.execute(() -> {
            petDAO.updatePet(dbPet);
        });
    }

    public void deletePet(PetModel pet){
        DBPet dbPet = DBPet.convertToDBPet(pet);
        AppDatabase.databaseWriteExecutor.execute(() -> {
            petDAO.deletePet(dbPet);
        });
    }

    public LiveData<List<PetModel>> getAllPet(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Transformations.map(petDAO.getAllPetLive(), dbPets -> dbPets.stream().map(dbPet ->
                    dbPet.convertToPet()).collect(Collectors.toList()));
        }
        return new MutableLiveData<List<PetModel>>(new ArrayList<>());
    }
}

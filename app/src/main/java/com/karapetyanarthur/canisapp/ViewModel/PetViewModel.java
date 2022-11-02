package com.karapetyanarthur.canisapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.karapetyanarthur.canisapp.Data.DBPet;
import com.karapetyanarthur.canisapp.Data.Model.PetModel;
import com.karapetyanarthur.canisapp.Data.Model.ProfileModel;
import com.karapetyanarthur.canisapp.Data.Repository.PetRepository;
import com.karapetyanarthur.canisapp.Data.Repository.ProfileRepository;

import java.util.List;

public class PetViewModel extends AndroidViewModel {

    private final PetRepository petRepository;
    private LiveData<List<PetModel>> getAllPet;

    public PetViewModel(@NonNull Application application) {
        super(application);
        petRepository = new PetRepository(application);
        getAllPet = petRepository.getAllPet();
    }

    public void insert(PetModel pet){
        petRepository.insertPet(pet);
    }

    public void update(PetModel pet){
        petRepository.updatePet(pet);
    }

    public void delete(PetModel pet){
        petRepository.deletePet(pet);
    }

    public LiveData<List<PetModel>> getAllPet(){
        return getAllPet;
    }
}

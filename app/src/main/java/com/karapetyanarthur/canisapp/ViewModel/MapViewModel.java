package com.karapetyanarthur.canisapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.karapetyanarthur.canisapp.Data.Model.PetModel;
import com.karapetyanarthur.canisapp.Data.Model.ProfileModel;
import com.karapetyanarthur.canisapp.Data.Repository.PetRepository;
import com.karapetyanarthur.canisapp.Data.Repository.ProfileRepository;

import java.util.List;

public class MapViewModel extends AndroidViewModel {

    private final ProfileRepository profileRepository;
    private LiveData<List<ProfileModel>> getAllProfile;
    private final PetRepository petRepository;
    private LiveData<List<PetModel>> getAllPet;

    public MapViewModel(@NonNull Application application) {
        super(application);
        profileRepository = new ProfileRepository(application);
        getAllProfile = profileRepository.getAllProfile();
        petRepository = new PetRepository(application);
        getAllPet = petRepository.getAllPet();
    }


    public void insert(ProfileModel profile){
        profileRepository.insertProfile(profile);
    }

    public void update(ProfileModel profile){
        profileRepository.updateProfile(profile);
    }

    public void delete(ProfileModel profile){
        profileRepository.deleteProfile(profile);
    }

    public LiveData<List<ProfileModel>> getAllProfile(){
        return getAllProfile;
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

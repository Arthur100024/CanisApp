package com.karapetyanarthur.canisapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.karapetyanarthur.canisapp.Data.Repository.ProfileRepository;
import com.karapetyanarthur.canisapp.Data.Model.ProfileModel;

import java.util.List;

public class EditProfileViewModel extends AndroidViewModel {
    private final ProfileRepository profileRepository;
    private LiveData<List<ProfileModel>> getAllProfile;

    public EditProfileViewModel(@NonNull Application application) {
        super(application);
        profileRepository = new ProfileRepository(application);
        getAllProfile = profileRepository.getAllProfile();
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
}

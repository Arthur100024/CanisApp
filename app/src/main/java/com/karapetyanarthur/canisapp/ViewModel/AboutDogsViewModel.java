package com.karapetyanarthur.canisapp.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.karapetyanarthur.canisapp.Data.Model.AboutDogsModel;
import com.karapetyanarthur.canisapp.Data.Repository.AboutDogsRepository;

import java.util.List;

public class AboutDogsViewModel extends ViewModel {
    private final AboutDogsRepository aboutDogsRepository = new AboutDogsRepository();

    public LiveData<List<AboutDogsModel>> getInfo(String breed){
        return aboutDogsRepository.getAllInfo(breed);
    }
}

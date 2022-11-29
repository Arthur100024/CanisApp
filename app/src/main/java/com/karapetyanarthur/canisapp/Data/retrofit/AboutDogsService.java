package com.karapetyanarthur.canisapp.Data.retrofit;

import android.provider.DocumentsContract;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AboutDogsService {

    @GET("v1/images/search?q=")
    Call<List<DogClass>> listAboutDogs(@Query("breed") String breed);

}

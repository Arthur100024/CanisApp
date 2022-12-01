package com.karapetyanarthur.canisapp.Data.retrofit;

import android.provider.DocumentsContract;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AboutDogsService {

    @GET("v1/breeds/search")
    Call<List<DogClass>> listAboutDogs(@Query("q") String breed);

}

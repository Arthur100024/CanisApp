package com.karapetyanarthur.canisapp.Data.Repository;

import android.os.Build;
import android.provider.DocumentsContract;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.karapetyanarthur.canisapp.Data.Model.AboutDogsModel;
import com.karapetyanarthur.canisapp.Data.retrofit.AboutDogsService;
import com.karapetyanarthur.canisapp.Data.retrofit.DogClass;

import java.util.List;
import java.util.stream.Collectors;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AboutDogsRepository {

    //Retrofit
    MutableLiveData<List<AboutDogsModel>> list = new MutableLiveData<>();
    private Gson gson = new GsonBuilder().create();
    private Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://api.thedogapi.com/")
            .build();
    private AboutDogsService aboutDogsService = retrofit.create(AboutDogsService.class);

    public LiveData<List<AboutDogsModel>> getAllInfo(String breed){

        MutableLiveData<List<AboutDogsModel>> result = new MutableLiveData<>();

        aboutDogsService.listAboutDogs(breed).enqueue(new Callback<List<DogClass>>() {
            @Override
            public void onResponse(Call<List<DogClass>> call, Response<List<DogClass>> response) {
                if (response.isSuccessful()){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        result.setValue(response.body().stream().map(constr -> {
                            AboutDogsModel aboutDogsModel = new AboutDogsModel();
                            if (constr.name == null){constr.name = "";}
                            if (constr.description == null){constr.description = "";}
                            if (constr.bred_for == null){constr.bred_for = "";}
                            if (constr.life_span == null){constr.life_span = "";}
                            if (constr.height.metric == null){constr.height.metric = "";}
                            if (constr.weight.metric == null){constr.weight.metric = "";}
                            aboutDogsModel.setDescription(constr.name + "\n"
                                    + constr.description + "\n"
                                    + constr.bred_for);
                            aboutDogsModel.setLifeSpan("living " + constr.life_span);
                            aboutDogsModel.setHeightAndWeight("height = " + constr.height.metric + " cm" + "\n"
                                    + "weight = " + constr.weight.metric + " cm");
                            return aboutDogsModel;
                        }).collect(Collectors.toList()));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<DogClass>> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return result;

    }

    public static void infoChecking(){
    }
}

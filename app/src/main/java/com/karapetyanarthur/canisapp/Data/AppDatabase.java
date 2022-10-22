package com.karapetyanarthur.canisapp.Data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {DBProfile.class, DBPet.class}, version = 1)
public abstract  class AppDatabase extends RoomDatabase {

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public abstract ProfileDAO getProfileDao();
    public abstract PetDAO getPetDAO();

    private static volatile AppDatabase INSTANCE;
    public static AppDatabase getInstance(Context context){
        if (INSTANCE == null){
            synchronized (AppDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class,"CanisApp_database").allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }



}

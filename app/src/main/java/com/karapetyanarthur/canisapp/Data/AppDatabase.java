package com.karapetyanarthur.canisapp.Data;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

public abstract  class AppDatabase extends RoomDatabase {

    public abstract ProfileDAO getProfileDao();

    private static volatile AppDatabase INSTANCE;
    public static AppDatabase getInstance(Context context){
        if (INSTANCE == null){
            synchronized (AppDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class,"ProfileDB").fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }

}

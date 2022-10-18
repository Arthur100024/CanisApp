package com.karapetyanarthur.canisapp.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProfileDAO {


    @Insert
    public void insertProfile(DBProfile dbProfile);

    @Update
    public void updateProfile(DBProfile dbProfile);

    @Delete
    public void deleteProfile(DBProfile dbProfile);

    @Query("select * from profileTable")
    public LiveData<List<DBProfile>> getAllProfileLive();

    @Query("select * from profileTable where profile_id ==:profile_id ")
    public LiveData<DBProfile> getProfile(long profile_id);


    @Query("SELECT profile_email FROM profileTable WHERE profile_id ==:profile_id")
    public String getEmailProfile(long profile_id);

 /*   @Query("SELECT profile_email FROM profileTable")
    public LiveData<List<DBProfile>> getEmailProfile();*/

}

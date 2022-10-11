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

    @Query("select * from profile")
    public LiveData<List<DBProfile>> getAllProfileLive();

    @Query("select * from profile where profile_id ==:profile_id ")
    public DBProfile getProfile(long profile_id);

}

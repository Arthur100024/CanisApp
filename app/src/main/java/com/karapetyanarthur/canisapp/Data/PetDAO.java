package com.karapetyanarthur.canisapp.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PetDAO {

    @Insert
    public void insertPet(DBPet dbPet);

    @Update
    public void updatePet(DBPet dbPet);

    @Delete
    public void deletePet(DBPet dbPet);

    @Query("select * from petTable")
    public LiveData<List<DBPet>> getAllPetLive();

    @Query("select * from petTable where pet_id ==:pet_id ")
    public LiveData<DBPet> getPet(long pet_id);

}

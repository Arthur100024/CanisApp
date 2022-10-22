package com.karapetyanarthur.canisapp.Data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.karapetyanarthur.canisapp.Model.PetModel;
import com.karapetyanarthur.canisapp.Model.ProfileModel;

@Entity(tableName = "petTable")
public class DBPet {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pet_id")
    private long id;
    @ColumnInfo(name = "pet_nickname")
    private String nickname;
    @ColumnInfo(name = "pet_breed")
    private String breed;
    @ColumnInfo(name = "pet_age")
    private String age;

    @Ignore
    public DBPet(){}

    public DBPet(long id, String nickname, String breed, String age){
        this.id = id;
        this.nickname = nickname;
        this.breed = breed;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public static DBPet convertFromPet(PetModel petModel) {
        DBPet dbPet = new DBPet();

        dbPet.setId(petModel.getId());
        dbPet.setNickname(petModel.getNickname());
        dbPet.setBreed(petModel.getBreed());
        dbPet.setAge(petModel.getAge());

        return dbPet;
    }
}

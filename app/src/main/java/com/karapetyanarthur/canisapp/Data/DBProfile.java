package com.karapetyanarthur.canisapp.Data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.karapetyanarthur.canisapp.Model.ProfileModel;

@Entity(tableName = "profileTable")
public class DBProfile {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "profile_id")
    private long id;
    @ColumnInfo(name = "profile_email")
    private String email;
    @ColumnInfo(name = "profile_name")
    private String name;
    @ColumnInfo(name = "profile_surname")
    private String surname;
    @ColumnInfo(name = "profile_phone")
    private String phone;
    @ColumnInfo(name = "profile_age")
    private String age;

    @Ignore
    public DBProfile(){}

    public DBProfile(long id, String email, String name, String surname, String phone, String age){
        this.id = id;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public static DBProfile convertFromProfile(ProfileModel profileModel) {
        DBProfile dbProfile = new DBProfile();

        dbProfile.setId(profileModel.getId());
        dbProfile.setName(profileModel.getName());
        dbProfile.setEmail(profileModel.getEmail());
        dbProfile.setSurname(profileModel.getSurname());
        dbProfile.setPhone(profileModel.getPhone());
        dbProfile.setAge(profileModel.getAge());


        return dbProfile;
    }
}

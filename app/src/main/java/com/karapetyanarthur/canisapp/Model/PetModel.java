package com.karapetyanarthur.canisapp.Model;

public class PetModel {

    private long id;

    private String nickname;

    private String breed;

    private String age;

    private String image;

    public PetModel(){}

    public PetModel(long id, String nickname, String breed, String age, String image){
        this.id = id;
        this.nickname = nickname;
        this.breed = breed;
        this.age = age;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

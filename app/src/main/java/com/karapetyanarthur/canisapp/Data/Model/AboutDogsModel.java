package com.karapetyanarthur.canisapp.Data.Model;

public class AboutDogsModel {

    private long id;
    private String life_span;
    private String description;
    private String height_and_weight;

    public AboutDogsModel() {
    }

    public AboutDogsModel(long id, String description, String life_span, String height_and_weight) {
        this.id = id;
        this.description = description;
        this.life_span = life_span;
        this.height_and_weight = height_and_weight;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLifeSpan() {
        return life_span;
    }

    public void setLifeSpan(String life_span) {
        this.life_span = life_span;
    }

    public String getHeightAndWeight() {
        return height_and_weight;
    }

    public void setHeightAndWeight(String height_and_weight) {
        this.height_and_weight = height_and_weight;
    }

}

package com.example.figurines;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Character {
    private final String TAG = "tag " + getClass().getSimpleName();

    private String name;
    private String gender;
    private String height;
    private String mass;
    private String skinColor;
    private String hairColor;
    private String eyeColor;
    private String species;

    //TODO some methods

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public void setSpecies(String species) { this.species = species; }

    public String getName() {return name;}

    public String getGender() {return gender;}

    public String getHeight() { return height; }

    public String getMass() { return mass; }

    public String getSkinColor() { return skinColor; }

    public String getHairColor() { return hairColor; }

    public String getEyeColor() { return eyeColor; }

    public String getSpecies() { return species; }


    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", height='" + height + '\'' +
                ", mass='" + mass + '\'' +
                ", skinColor='" + skinColor + '\'' +
                ", hairColor='" + hairColor + '\'' +
                ", eyeColor='" + eyeColor + '\'' +
                ", species='" + species + '\'' +
                '}';
    }


}


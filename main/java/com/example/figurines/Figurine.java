package com.example.figurines;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

// Définition de la classe Figurine qui implémente Parcelable et Comparable<Figurine>
public class Figurine implements Parcelable,Comparable<Figurine> {


    // Déclaration des variables membres de la classe Figurine
    private String name;            // Nom de la figurine
    private String gender;          // Genre de la figurine (masculin, féminin, inconnu...)
    private String height;          // Taille de la figurine
    private String mass;            // Poids de la figurine
    private String skinColor;       // Couleur de peau de la figurine
    private String hairColor;       // Couleur de cheveux de la figurine
    private String eyeColor;        // Couleur des yeux de la figurine
    private String species;         // Espèce de la figurine
    private float prix;             // Prix de la figurine


    // Constructeur de la classe Figurine
    public Figurine(String n, String g, String h, String m, String sc, String hc, String ec,String s){
        name = n;
        gender = g;
        height = h;
        mass = m;
        skinColor = sc;
        hairColor = hc;
        eyeColor = ec;
        species = s;
    }

    // Constructeur utilisé lors de la création d'un objet Figurine à partir d'un Parcel
    protected Figurine(Parcel in) {
        name = in.readString();
        gender = in.readString();
        height = in.readString();
        mass = in.readString();
        skinColor = in.readString();
        hairColor = in.readString();
        eyeColor = in.readString();
        species = in.readString();
        prix = in.readFloat();
    }

    // Définition de CREATOR qui permet de créer un objet Figurine à partir d'un Parcel
    public static final Creator<Figurine> CREATOR = new Creator<Figurine>() {
        @Override
        public Figurine createFromParcel(Parcel in) {
            return new Figurine(in);
        }

        @Override
        public Figurine[] newArray(int size) {
            return new Figurine[size];
        }
    };

    // Définition des méthodes set pour les variables membres de la classe Figurine
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
    public void setPrix(float prix) { this.prix = prix;}


    // Définition des méthodes get pour les variables membres de la classe Figurine
    public String getName() {return name;}

    public String getGender() {return gender;}

    public String getHeight() { return height; }

    public String getMass() { return mass; }

    public String getSkinColor() { return skinColor; }

    public String getHairColor() { return hairColor; }

    public String getEyeColor() { return eyeColor; }

    public String getSpecies() { return species; }

    public float getPrix() { return  prix;}

    // On implémente la méthode describeContents() de l'interface Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    // On implémente la méthode writeToParcel() de l'interface Parcelable
    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(gender);
        parcel.writeString(height);
        parcel.writeString(mass);
        parcel.writeString(skinColor);
        parcel.writeString(hairColor);
        parcel.writeString(eyeColor);
        parcel.writeString(species);
        parcel.writeFloat(prix);
    }


    @Override
    public int compareTo(Figurine f) {
        return this.name.compareTo(f.name);    }
}

package com.example.figurines;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class ListFigurine {

    ArrayList<Figurine> listF;

    public ListFigurine() {
        listF = new ArrayList<Figurine>(); // Initialisation d'une liste vide
    }

    public int size(){
        return listF.size(); // Retourne le nombre d'éléments dans la liste
    }

    public Figurine get(int pos){
        return  listF.get(pos); // Retourne l'élément à la position donnée dans la liste
    }

    public void construireListe(List<Character> itemList){

        ArrayList<String> personnages = construireArrayListePersonnage(); // Initialisation d'une liste de personnages
        for(Character c : itemList){ // Pour chaque personnage dans la liste donnée
            for(String n : personnages ){ // Pour chaque nom de personnage dans la liste de personnages
                if(c.getName().equals(n)){ // Si le nom du personnage correspond à un nom de personnage de la liste de personnages
                    listF.add(new Figurine(c.getName(),c.getGender(),c.getHeight(),c.getMass(),c.getSkinColor(),c.getHairColor(),c.getEyeColor(),c.getSpecies()));// Ajoute une figurine avec les caractéristiques du personnage correspondant à la liste de figurines
                }
            }
        }
        ajoutPrix(); // Ajoute un prix à chaque figurine de la liste
    }

    public ArrayList<String> construireArrayListePersonnage(){
        ArrayList<String> p = new ArrayList<String>(); // Initialisation d'une liste de personnages
        p.add("Anakin Skywalker");
        p.add("Boba Fett");
        p.add("C-3PO");
        p.add("Chewbacca");
        p.add("Darth Maul");
        p.add("Darth Vader");
        p.add("Grievous");
        p.add("Luke Skywalker");
        p.add("Obi-Wan Kenobi");
        p.add("R2-D2");
        p.add("Yoda");

        return p; // Retourne la liste de personnages
    }

    public void ajoutPrix(){
        for(Figurine f: listF){ // Pour chaque figurine dans la liste de figurines
            switch (f.getName()){ // Selon le nom de la figurine
                case "Anakin Skywalker":
                    f.setPrix(30.5F); // Ajoute le prix correspondant
                    break;

                case "Boba Fett":
                    f.setPrix(40.6F);
                    break;

                case "C-3PO":
                    f.setPrix(20.5F);
                    break;

                case "Chewbacca":
                    f.setPrix(18);
                    break;

                case "Darth Maul":
                    f.setPrix(35.8F);
                    break;

                case "Darth Vader":
                    f.setPrix(50);
                    break;

                case "Grievous":
                    f.setPrix(48);
                    break;

                case "Luke Skywalker":
                    f.setPrix(55);
                    break;

                case "Obi-Wan Kenobi":
                    f.setPrix(46.55F);
                    break;

                case "R2-D2":
                    f.setPrix(199);
                    break;

                case "Yoda":
                    f.setPrix(5.5F);
                    break;
            }
        }
    }

}

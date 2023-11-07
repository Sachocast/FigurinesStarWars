package com.example.figurines;

import java.util.Comparator;

public class ComparateurFigurineParPrixInverse implements Comparator<Figurine> {
    @Override
    public int compare(Figurine p1, Figurine p2) {
        return Float.compare(p2.getPrix(), p1.getPrix());
    }
}
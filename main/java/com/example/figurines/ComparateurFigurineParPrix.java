package com.example.figurines;

import java.util.Comparator;

public class ComparateurFigurineParPrix  implements Comparator<Figurine> {
    @Override
    public int compare(Figurine p1, Figurine p2) {
        return Float.compare(p1.getPrix(), p2.getPrix());
    }
}
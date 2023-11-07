package com.example.figurines;

import java.util.Comparator;

public class ComparateurFigurineNomInverse  implements Comparator<Figurine> {
    @Override
    public int compare(Figurine p1, Figurine p2) {
        return p2.getName().compareTo(p1.getName());
    }
}
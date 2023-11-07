package com.example.figurines;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProduitAdapter extends BaseAdapter {

    private ArrayList<Figurine> figurines; // Liste des figurines
    private Context mContext; // Contexte de l'application
    private LayoutInflater mInflater; // Utilisé pour instancier le layout XML en tant que vue
    private ClickableActivity activity; // Activité associée à l'adapter

    public ProduitAdapter(ClickableActivity activity, ArrayList<Figurine> items) {
        this.activity = activity;
        this.figurines = items;
        mContext = activity.getContext();
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        // Renvoie le nombre d'éléments dans la liste
        return figurines.size();
    }

    @Override
    public Object getItem(int i) {
        // Renvoie l'élément à l'indice i
        return figurines.get(i);
    }

    @Override
    public long getItemId(int i) {
        // Renvoie l'identifiant de l'élément à l'indice i
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View layoutItem;

        //(1) : Réutilisation des layouts
        // Vérifie si la vue est nulle, sinon la réutilise
        layoutItem = view == null ? mInflater.inflate(R.layout.produit_layout, viewGroup, false) : view;

        //(2) : Récupération des TextView de notre layout
        final TextView displayNom = layoutItem.findViewById(R.id.panier_nom);
        ImageView displayIMG = layoutItem.findViewById(R.id.panier_imageview);
        TextView displayPrix = layoutItem.findViewById(R.id.panier_prix);
        int resId = mContext.getResources().getIdentifier(figurines.get(i).getName().replaceAll("[\\s-]+", "_").toLowerCase(), "drawable", mContext.getPackageName());

        TextView displayQte = layoutItem.findViewById(R.id.panier_quantite);
        Button moins = layoutItem.findViewById(R.id.panier_moins);
        Button plus = layoutItem.findViewById(R.id.panier_plus);

        //(3) : Renseignement des valeurs
        // Affiche le nom de la figurine, la taille du texte est définie à 20
        displayNom.setText(figurines.get(i).getName());
        displayNom.setTextSize(20);
        // Affiche l'image de la figurine correspondante
        displayIMG.setImageResource(resId);

        // Ajoute un événement de clic sur le bouton "+" pour augmenter la quantité de la figurine dans le panier
        plus.setOnClickListener(click -> {
            int qte = Integer.parseInt((String) displayQte.getText())+1;
            displayQte.setText(qte+"");
            Float prix = Float.valueOf(displayPrix.getText().toString().replace("€",""));
            prix += figurines.get(i).getPrix();
            displayPrix.setText( String.format("%.2f",prix)+"€");
        });

        moins.setOnClickListener(click -> {
            int qte = Integer.parseInt((String) displayQte.getText())-1;
            if(qte>=1){
                displayQte.setText(qte+"");
                Float prix = Float.valueOf(displayPrix.getText().toString().replace("€",""));
                prix -= figurines.get(i).getPrix();
                displayPrix.setText( String.format("%.2f",prix)+"€");

            }
        });

        displayPrix.setText(figurines.get(i).getPrix()+"€");

        displayNom.setTag(i);

        return layoutItem; //On retourne l'item créé.
    }
}

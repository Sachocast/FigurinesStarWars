package com.example.figurines;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;

public class FigurineAdapter extends BaseAdapter {
    private ListFigurine figurines;

    private Context mContext;

    private LayoutInflater mInflater;

    private ClickableActivity activity;


    public FigurineAdapter(ClickableActivity activity, ListFigurine items) {
        this.activity = activity;
        this.figurines = items;
        mContext = activity.getContext();
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return figurines.size();
    }

    @Override
    public Object getItem(int i) {
        return figurines.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View layoutItem;

        //(1) : Réutilisation des layouts
        layoutItem = view == null ? mInflater.inflate(R.layout.character_layout, viewGroup, false) : view;

        //(2) : Récupération des TextView de notre layout
        final TextView displayNom = layoutItem.findViewById(R.id.nom);
        ImageView displayIMG = layoutItem.findViewById(R.id.img);
        TextView displayPrix = layoutItem.findViewById(R.id.prix);
        int resId = mContext.getResources().getIdentifier(figurines.get(i).getName().replaceAll("[\\s-]+", "_").toLowerCase(), "drawable", mContext.getPackageName());

        //(3) : Renseignement des valeurs
        displayNom.setText(figurines.get(i).getName());
        displayNom.setTextSize(20);
        displayIMG.setImageResource(resId);

        displayPrix.setText(figurines.get(i).getPrix()+"€");

        displayNom.setTag(i);
        layoutItem.setOnClickListener(v -> {
            activity.onClickNom(figurines.get(i));
        } );

        return layoutItem; //On retourne l'item créé.
    }


}

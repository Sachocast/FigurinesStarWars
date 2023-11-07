package com.example.figurines;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;


public class SecondActivity extends AppCompatActivity implements ClickableActivity {

    // Liste de figurines ajoutées au panier
    ArrayList<Figurine> listProduit = new ArrayList<Figurine>();

    private ViewPager viewPager;
    private CustomPagerAdapter adapter;

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description_layout);

        // Récupération des éléments de la vue
        TextView name = (TextView) findViewById(R.id.name);
        TextView prix = (TextView) findViewById(R.id.prix);
        TextView desc = (TextView) findViewById(R.id.description);
        Button panier = (Button) findViewById(R.id.panier);
        Button ajout = (Button) findViewById(R.id.ajout);
        viewPager = findViewById(R.id.view_pager);
        intent = new Intent(getApplicationContext(), ThirdActivity.class);

        // Gestion du clic sur le bouton panier
        panier.setOnClickListener(click -> {
            // Ajout des figurines au panier
            intent.putExtra(getString(R.string.VAR_KEY2), listProduit);
            // Animation de rotation du bouton
            RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotateAnimation.setDuration(600);
            panier.startAnimation(rotateAnimation);

            // Lancement de l'activité ThirdActivity après la fin de l'animation
            rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    // Code à exécuter lorsque l'animation commence
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    // Code à exécuter lorsque l'animation est répétée
                }
            });
        });


        // Récupération de la figurine sélectionnée dans l'activité précédente
        Figurine figurine = getIntent().getExtras().getParcelable(getString(R.string.VAR_KEY1));

        // Gestion du clic sur le bouton ajout au panier
        ajout.setOnClickListener(click -> {
            // Vérification si la figurine est déjà dans le panier
            boolean dejaDansPanier = false;
            for (Figurine f: listProduit){
                if(f.getName().equals(figurine.getName())){ dejaDansPanier = true;}
            }
            // Ajout de la figurine au panier s'il n'est pas déjà dans le panier
            if(!dejaDansPanier){
                listProduit.add(figurine);
                // Animation de rotation du bouton
                RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setDuration(600);
                ajout.startAnimation(rotateAnimation);
                LayoutInflater inflater = getLayoutInflater();
                View customView = inflater.inflate(R.layout.custom_alert_dialog, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setView(customView);
                AlertDialog alertDialog = builder.create();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (alertDialog != null && alertDialog.isShowing()) {
                            alertDialog.dismiss();
                        }
                    }
                }, 3000);
                alertDialog.show();
            }
        });


        name.setText(figurine.getName());

        List<Integer> images = new ArrayList<>();
        int resId = getContext().getResources().getIdentifier(figurine.getName().replaceAll("[\\s-]+", "_").toLowerCase(), "drawable", getContext().getPackageName());
        int resId2 = getContext().getResources().getIdentifier(figurine.getName().replaceAll("[\\s-]+", "_").toLowerCase()+"_2", "drawable", getContext().getPackageName());
        int resId3 = getContext().getResources().getIdentifier(figurine.getName().replaceAll("[\\s-]+", "_").toLowerCase()+"_3", "drawable", getContext().getPackageName());

        images.add(resId);
        if(resId2 != 0){images.add(resId2);}
        if(resId3 != 0){images.add(resId3);}

        prix.setText(figurine.getPrix()+ "€");


        Float tailleF = Float.valueOf(Integer.parseInt(figurine.getHeight().replace(".", ""))/4);
        desc.setText("Voici la figurine de "+ figurine.getName() + " !!!\n"
                + "genre : "+ figurine.getGender()+"\n"
                + "taille du personnage : "+ figurine.getHeight()+"\n"
                + "taille de la figurine : "+ tailleF+"\n"
                + "couleur de peau : "+ figurine.getSkinColor()+"\n"
                + "couleur de cheveux : "+ figurine.getHairColor()+"\n"
                + "couleur des yeux : "+ figurine.getEyeColor()+"\n"
                + "espece : "+ figurine.getSpecies()+"\n");

        adapter = new CustomPagerAdapter(this, images);

        viewPager.setAdapter(adapter);


    }

    @Override
    public void onClickNom(Figurine item) {

    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

}

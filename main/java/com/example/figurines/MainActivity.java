package com.example.figurines;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.GridView;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PostExecuteActivity<Character>, ClickableActivity{

    // On initialise le tag pour le log
    private final String TAG = "tag "+getClass().getSimpleName();

    // On déclare les variables dont on aura besoin pour afficher la liste de figurines
    private FigurineAdapter adapter;
    private GridView gridView;

    Intent intent;

    ListFigurine listP;

    ViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // On associe la vue correspondante à cette activité
        setContentView(R.layout.activity_main);
        // On définit la langue de l'application
        Settings.language = getString(R.string.language);

        // On récupère les données à partir de l'API à travers une requête HTTP asynchrone
        String url = "https://raw.githubusercontent.com/akabab/starwars-api/0.2.1/api/all.json";
        new HttpAsyncGet<>(url, Character.class, this);
        // On prépare l'intent pour la seconde activité
        intent = new Intent(getApplicationContext(), SecondActivity.class);

        // On associe les boutons pour trier la liste selon différents critères
        Button bAZ = (Button) findViewById(R.id.az);
         bAZ.setOnClickListener(click -> {
             Collections.sort(listP.listF);
             adapter.notifyDataSetChanged();
         });
        Button bZA = (Button) findViewById(R.id.za);
        bZA.setOnClickListener(click -> {
            Collections.sort(listP.listF, new ComparateurFigurineNomInverse());
            adapter.notifyDataSetChanged();
        });
        Button bPrixCroiss = (Button) findViewById(R.id.prixCroiss);
        bPrixCroiss.setOnClickListener(click -> {
            Collections.sort(listP.listF, new ComparateurFigurineParPrix());
            adapter.notifyDataSetChanged();
        });
        Button bPrixDesc = (Button) findViewById(R.id.prixDesc);
        bPrixDesc.setOnClickListener(click -> {
            Collections.sort(listP.listF, new ComparateurFigurineParPrixInverse());
            adapter.notifyDataSetChanged();
        });

        // On ajoute une musique de fond à l'application
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.themedv);
        mediaPlayer.start();

    }

    @Override
    public void onClickNom(Figurine item) {
        // On lance la seconde activité pour afficher les détails de la figurine sélectionnée
        intent.putExtra(getString(R.string.VAR_KEY1), item);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public Context getContext() {
        // On retourne le contexte de l'application
        return getApplicationContext();
    }

    @Override
    public void onPostExecuteCharacter(List<Character> itemList) {
        // On construit la liste de figurines à partir de la liste de personnages récupérée de l'API
        listP = new ListFigurine();
        listP.construireListe(itemList);
        // On vérifie que la première figurine a bien été ajoutée à la liste
        Log.d(TAG,"First character = " + listP.get(0).toString());
        // On initialise l'adapter et on l'associe à la GridView
        adapter = new FigurineAdapter(this,listP);
        gridView = findViewById(R.id.grd);
        gridView.setAdapter(adapter);

    }


}
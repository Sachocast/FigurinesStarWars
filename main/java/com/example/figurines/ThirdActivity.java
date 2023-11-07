package com.example.figurines;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity implements ClickableActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.panier_layout);

        ArrayList<Figurine> listF = getIntent().getExtras().getParcelableArrayList(getString(R.string.VAR_KEY2));
        ProduitAdapter adapter = new ProduitAdapter(this,listF);
        ListView listView = findViewById(R.id.lst);
        listView.setAdapter(adapter);

        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        // Récupérer la référence du bouton "valider le panier"
        Button btnValider = findViewById(R.id.panier_valider);

        // Ajouter un événement de clic sur le bouton "valider le panier"
        btnValider.setOnClickListener(Click ->{
            LayoutInflater inflater = getLayoutInflater();
            View customView = inflater.inflate(R.layout.valider_panier, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(customView);
            AlertDialog alertDialog = builder.create();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (alertDialog != null && alertDialog.isShowing()) {
                        alertDialog.dismiss();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                }
            }, 1500);
            alertDialog.show();
        });
    }

    // Implémenter les méthodes de l'interface ClickableActivity
    @Override
    public void onClickNom(Figurine item) {

    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }
}

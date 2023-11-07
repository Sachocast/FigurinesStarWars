package com.example.figurines;

import android.app.ProgressDialog;
import android.util.Log;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * construit un Objet T depuis un fichier json dont l'adress URL est passé en paramètre
 * Tache asynchrone
 * @author Frédéric RALLO - March 2023
 */

        // Construit un objet T depuis un fichier JSON dont l'adresse URL est passée en paramètre.
        // Tache asynchrone.
public class HttpAsyncGet<T>{
    private static final String TAG = "tag " + HttpAsyncGet.class.getSimpleName();    // Tag utilisé pour l'affichage des erreurs
    private final Class<T> clazz; // La classe de l'objet à construire
    private List<T> itemList; // La liste d'objets construits à partir du fichier JSON
    private final HttpHandler webService; // La classe interne pour la communication HTTP


    /**
     * Constructeur de la classe HttpAsyncGet
     * @param url L'adresse URL du fichier JSON
     * @param clazz La classe de l'objet à construire à partir du fichier JSON
     * @param postExecuteActivity L'activité qui gère l'exécution de la tâche asynchrone
     */
    public HttpAsyncGet(String url, Class<T> clazz, PostExecuteActivity postExecuteActivity) {
        super();
        webService = new HttpHandler();
        this.clazz = clazz;
        Runnable runnable = ()->{
            doInBackGround(url);
            postExecuteActivity.runOnUiThread( ()-> {
                postExecuteActivity.onPostExecuteCharacter(getItemResult());
            } );
        };
        Executors.newSingleThreadExecutor().execute( runnable );
    }


    /**
     * Exécute la tâche asynchrone pour construire les objets à partir du fichier JSON
     * @param urlAddress L'adresse URL du fichier JSON
     */
    public void doInBackGround(String urlAddress){
        // Récupère le JSON à partir de l'URL
        String jsonStr = webService.makeServiceCall(urlAddress);
        // Construit la liste d'objets à partir du JSON
        ObjectMapper mapper = new ObjectMapper();
        try {
            itemList = mapper.readValue(jsonStr, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Renvoie la liste d'objets construits à partir du fichier JSON
     * @return La liste d'objets construits à partir du fichier JSON
     */
    public List<T> getItemResult() {
        return itemList;
    }


    /**
     * Classe interne pour gérer la communication HTTP avec le serveur
     */
    static class HttpHandler { //innerClass

        /**
         * Effectue l'appel au service Web pour récupérer le fichier JSON
         * @param reqUrl L'adresse URL du fichier JSON
         * @return Le JSON sous forme de chaîne de caractères
         */
        public String makeServiceCall(String reqUrl) {
            String response = null;
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(reqUrl).openConnection();
                connection.setRequestMethod("GET");
                // lecture du fichier
                InputStream inputStream = new BufferedInputStream(connection.getInputStream());
                response = convertStreamToString(inputStream);
            } catch (MalformedURLException e) {
                Log.e(TAG, "MalformedURLException: " + e.getMessage());
            } catch (ProtocolException e) {
                Log.e(TAG, "ProtocolException: " + e.getMessage());
            } catch (IOException e) {
                Log.e(TAG, "IOException: " + e.getMessage());
            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }
            return response;
        }

        //Conversion flux en String
        private String convertStreamToString(InputStream inputStream) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line).append('\n');
                    Log.e(TAG,line);
                }
            }
            catch (IOException e) {  e.printStackTrace();   }
            finally {
                try { inputStream.close(); } catch (IOException e) { e.printStackTrace();  }
            }
            return stringBuilder.toString();
        }
    }
}
package com.example.ejercicio3.core;


import android.content.Intent;
import android.util.Log;

import com.example.ejercicio3.view.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/** ResponseParser interprets the answer obtained from the REST API */
public class ResponseParser {
    private static final String LOG_TAG = "ResponseParser";
    private static final String ARRAY_TAG = "postalCodes";
    private static final String ADMINCODE2_TAG = "adminCode2";
    private static final String ADMINCODE1_TAG = "adminCode1";
    private static final String ADMINNAME3_TAG = "adminName3";
    private static final String ADMINNAME2_TAG = "adminName2";
    private static final String COUNTRYCODE_TAG = "countryCode";
    private static final String LAT_TAG = "lat";
    private static final String LNG_TAG = "lng";
    private static final String POSTALCODE_TAG = "postalCode";
    private static final String ADMINNAME1_TAG = "adminName1";
    private static final String ISO_TAG = "ISO3166-2";
    private static final String PLACENAME_TAG="placeName";
    private static ArrayList<Ayuntamiento> lista;
    private static List<String> paises;

    /** Creates a new ResponseParser, given the InputStream from the connection */
    public ResponseParser(InputStream is) throws JSONException {
        this.parse( is );
    }

    public static List<Ayuntamiento> getAyuntamientos(){
        if (lista==null){
            lista = new ArrayList<>();
        }
        return lista;
    }

    public static ArrayList<Ayuntamiento> selectAyunta(List<Ayuntamiento> listaAyunta, String[] strings, boolean[] booleans){

        ArrayList<Ayuntamiento> aux = new ArrayList<Ayuntamiento>();

        for (int i = 0;i<strings.length;i++){
            if (booleans[i]){
               for (int j=0;j<listaAyunta.size();j++){
                   if (listaAyunta.get(j).getCountryCode().equalsIgnoreCase(strings[i])){
                       aux.add(listaAyunta.get(j));
                   }
               }
            }
        }

        return aux;
    }

    public static boolean[] getBooleans(){
        boolean[] booleans = new boolean[paises.size()];

        for (int i=0;i<paises.size();i++){
            booleans[i]=false;
        }

        return booleans;
    }

    public static String[] getPaises(){
        if (paises==null){
            paises= new ArrayList<>();
        }
        String[] strings = new String[paises.size()];

        for (int i=0;i<paises.size();i++){
            strings[i]=paises.get(i);
        }

        System.out.println("+++++++++" + strings.toString() + "--------------------");

        return strings;
    }

    /** Parses the info from the given input stream. */
    private void parse(InputStream is) throws JSONException {
         String adminCode2;
         String adminCode1;
         String adminName2;
         String lng;
         String countryCode;
         String postalCode;
         String adminName1;
         String iso;
         String placeName;
         String lat;
         String adminName3;
       JSONArray listajson=null;

        lista = new ArrayList<>();
        paises= new ArrayList<>();

        try {
            Log.d(LOG_TAG, " in doInBackground(): querying");
            JSONObject json = new JSONObject( readAllStream( is ) );
            Log.d(LOG_TAG, " in doInBackground(): content fetched: " + json.toString( 4 ));

            // Get basic time info

            listajson = json.getJSONArray(ARRAY_TAG);

        } catch(JSONException exc) {
            Log.e( LOG_TAG, " in parse(): " + exc.getMessage() );
        }

        for (int i=0;i<listajson.length();i++){

            JSONObject aux = listajson.getJSONObject(i);

            try{
                adminCode2 = aux.getString(ADMINCODE2_TAG);
            } catch (JSONException exc){
                adminCode2 ="N/A";
            }
            try{
                adminCode1 = aux.getString(ADMINCODE1_TAG);
            } catch (JSONException exc){
                adminCode1 ="N/A";
            }
            try {
                adminName3 = aux.getString(ADMINNAME3_TAG);
            } catch (JSONException exc){
                adminName3 = "N/A";
            }
            try {
                adminName2 = aux.getString(ADMINNAME2_TAG);
            } catch (JSONException exc){
                adminName2 = "N/A";
            }
            try {
                lng = aux.getString(LNG_TAG);
            } catch (JSONException exc){
                lng = "N/A";
            }
            try {
                countryCode = aux.getString(COUNTRYCODE_TAG);
            } catch (JSONException exc){
                countryCode = "N/A";
            }
            try {
                postalCode = aux.getString(POSTALCODE_TAG);
            } catch (JSONException exc){
                postalCode = "N/A";
            }
            try {
                adminName1 = aux.getString(ADMINNAME1_TAG);
            } catch (JSONException exc){
                adminName1 = "N/A";
            }
            try {
                iso = aux.getString(ISO_TAG);
            } catch (JSONException exc){
                iso = "N/A";
            }
            try {
                placeName = aux.getString(PLACENAME_TAG);
            } catch (JSONException exc){
                placeName = "N/A";
            }
            try {
                lat = aux.getString(LAT_TAG);
            } catch (JSONException exc){
                lat = "N/A36350";
            }

            Ayuntamiento ayuntamiento = new Ayuntamiento(adminCode2, adminCode1, adminName3, adminName2, lng, countryCode, postalCode, adminName1, iso, placeName, lat);
            lista.add(ayuntamiento);
            boolean aux2 = false;
            for (int j=0;j<paises.size();j++){
                if (paises.get(j).equalsIgnoreCase(countryCode)){
                    aux2=true;
                }

            }
            if (aux2==false){
                paises.add(countryCode);
            }

        }

        System.out.println(lista);

    }

    /** @return the whole stream contents in a single string. */
    private static String readAllStream(InputStream is)
    {
        BufferedReader reader = null;
        StringBuilder toret = new StringBuilder();
        String line;

        try {
            reader = new BufferedReader( new InputStreamReader( is ) );

            while( ( line = reader.readLine() ) != null ) {
                toret.append( line );
            }
        } catch (IOException e) {
            Log.e( LOG_TAG, " in getStringFromString(): error converting net input to string"  );
        } finally {
            Util.close( is, LOG_TAG, "readAllStream" );
        }

        return toret.toString();
    }

}
package com.example.ejercicio3.core;


import android.os.AsyncTask;
import android.util.Log;

import com.resuadam.androidtime.R;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Fetches the time from GeoNames
 * Created by baltasarq on 4/12/15.
 */
public class HttpFetcher extends AsyncTask<URL, Void, Boolean> {
    public static final String LOG_TAG = "HttpFetcher";
    public static String TIME_URL;

    public HttpFetcher(Observer activity, String codigoPostal)
    {

        this.TIME_URL = " http://api.geonames.org/postalCodeSearchJSON?postalcode=" + codigoPostal + "&maxRows=100&username=dispositivos_moviles";
        this.activity=activity;
    }

    @Override
    protected Boolean doInBackground(URL... urls)
    {
        InputStream is = null;
        boolean toret = false;

        try {
            // Connection
            Log.d( LOG_TAG, " in doInBackground(): connecting" );
            HttpURLConnection conn = (HttpURLConnection) urls[ 0 ].openConnection();
            conn.setReadTimeout( 1000 /* milliseconds */ );
            conn.setConnectTimeout( 5000 /* milliseconds */ );
            conn.setRequestMethod( "GET" );
            conn.setDoInput( true );

            // Obtain the answer
            conn.connect();
            int responseCode = conn.getResponseCode();

            if ( responseCode == 200 ) {
                this.responseParser = new ResponseParser( conn.getInputStream() );
                toret = true;

            }
        }
        catch(IOException exc) {
            Log.e( LOG_TAG, " in doInBackground(), connecting: " + exc.getMessage() );
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            Util.close( is, LOG_TAG, "doInBackground" );
    }

        return toret;
    }

    @Override
    public void onPostExecute(Boolean result)
    {
        this.activity.alertPaises();
        return;
    }

    private ResponseParser responseParser;
    private Observer activity;
}
package com.example.ejerciciowifi.core;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class MetodosConexiones {

    Context context;

    public MetodosConexiones(Context context){
        this.context=context;
    }

    public boolean isConnected(){

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean aux = networkInfo != null && networkInfo.isConnectedOrConnecting();

        return aux;
    }

    public boolean tipoConexion(){

        boolean wifi = false;

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo ni = manager.getActiveNetworkInfo();

        wifi = ni.getType() == ConnectivityManager.TYPE_WIFI;

        return wifi;
    }
}

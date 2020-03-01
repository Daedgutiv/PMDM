package com.example.ejercicio3.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.ejercicio3.core.Ayuntamiento;
import com.example.ejercicio3.core.HttpFetcher;
import com.example.ejercicio3.core.ListaAyunta;
import com.example.ejercicio3.core.Observer;
import com.example.ejercicio3.core.ResponseParser;
import com.resuadam.androidtime.R;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends Activity implements Observer {
    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    private ArrayAdapter<Ayuntamiento> adapter;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        this.requestWindowFeature( Window.FEATURE_NO_TITLE );
        setContentView( R.layout.activity_main );

        ImageButton btSearch = (ImageButton) this.findViewById(R.id.search);
        final EditText edSearch = (EditText) this.findViewById(R.id.edCodigo);
        ListView lista = (ListView) this.findViewById(R.id.listaAyuntamientos);
        final ListaAyunta listaAyunta = (ListaAyunta) this.getApplication();

        this.registerForContextMenu(lista);

        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codigoPostal= edSearch.getText().toString();
                if (codigoPostal.trim().isEmpty()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Error");
                    builder.setMessage("Error no se introdujo un código postal");
                    builder.setPositiveButton("Aceptar", null);
                    builder.setNegativeButton("Cancelar", null);
                    builder.create().show();
                } else if (!isNum(codigoPostal)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Error");
                    builder.setMessage("Error no se introdujo un código postal válido");
                    builder.setPositiveButton("Aceptar", null);
                    builder.setNegativeButton("Cancelar", null);
                    builder.create().show();
                } else {
                    try {
                        new HttpFetcher(MainActivity.this, codigoPostal ).execute( new URL( HttpFetcher.TIME_URL ) );
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }

            }
        });



        this.adapter = new ArrayAdapter<Ayuntamiento>(this, android.R.layout.simple_selectable_list_item, listaAyunta.getLista());
        lista.setAdapter(this.adapter);

    }

    public boolean isNum(String num){
        try {
            Integer.valueOf(num);
            return true;
        }catch (NumberFormatException exc){
            return false;
        }
    }

    public void alertPaises(){

        final boolean[] listaAux = ResponseParser.getBooleans();

        final ListaAyunta listaAyunta = (ListaAyunta) this.getApplication();

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Países");
        builder.setNegativeButton("Cancelar", null);
        builder.setMultiChoiceItems(ResponseParser.getPaises(), ResponseParser.getBooleans(), new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked){
                    listaAux[which] = true;
                } else {
                    listaAux[which] =false;
                }
            }
        });
        builder.setPositiveButton("Seleccionar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                ArrayList<Ayuntamiento> auxLista = ResponseParser.selectAyunta(ResponseParser.getAyuntamientos() ,ResponseParser.getPaises(), listaAux);



                listaAyunta.addLista(auxLista);
                MainActivity.this.adapter.notifyDataSetChanged();
            }
        });

        builder.create().show();
    }

    private boolean chkConnectivity()
    {
        Log.d( LOG_TAG, "checking connectivity" );
        ConnectivityManager connMgr =
                (ConnectivityManager)  this.getSystemService( Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        boolean connected = ( networkInfo != null && networkInfo.isConnected() );

        return connected;
    }

    private String codigoPostal;
}
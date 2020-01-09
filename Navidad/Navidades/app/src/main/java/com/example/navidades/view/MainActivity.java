package com.example.navidades.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.navidades.R;
import com.example.navidades.core.Partido;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_CODE=1;
    private final int REQUEST_CODE2=2;
    private final int REQUEST_CODE3=3;
    private ArrayList<Partido> partidos;
    private ArrayList<String> lista;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.partidos= new ArrayList<Partido>();
        this.lista = new ArrayList<String>();



        TextView media = (TextView) this.findViewById(R.id.media);
        media.setText("Valoración media: 0");
        Button add = (Button) this.findViewById(R.id.add);
        Button partidos = (Button) this.findViewById(R.id.viewPartidos);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityAdd();
            }
        });
        partidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityPartidos();
            }
        });

        ListView list = (ListView) this.findViewById(R.id.lista);

        this.registerForContextMenu(list);
        this.adapter = new ArrayAdapter<String>(this.getApplicationContext(),android.R.layout.simple_selectable_list_item, this.lista);
        list.setAdapter(this.adapter);
        leer();
        stats();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        this.getMenuInflater().inflate(R.menu.anhadir, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        boolean toret = false;

        //Opciones del menu
        switch (menuItem.getItemId()) {
            case R.id.anhadir:
               activityAdd();
                toret = true;
                break;
            case R.id.partidos:
                activityPartidos();
                toret = true;
                break;
        }
        return toret;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode==REQUEST_CODE && resultCode==RESULT_OK){
            leer();
            stats();
        } else if (requestCode==REQUEST_CODE && resultCode==1){
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
            builder.setTitle("ERROR al añadir");
            builder.setMessage("No se ha podido añadir el partido porque existían campos vacíos.");
            builder.setPositiveButton("Aceptar", null);
            builder.setNegativeButton("Reintentar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    activityAdd();
                }
            });
            builder.create().show();
        } else if (requestCode==REQUEST_CODE && resultCode==RESULT_CANCELED){
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Cancelado");
            builder.setMessage("Se ha cancelado la operación.");
            builder.setPositiveButton("Aceptar", null);
            builder.setNegativeButton("Reintentar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    activityAdd();
                }
            });
            builder.create().show();
        } else if (requestCode==REQUEST_CODE2 && resultCode==RESULT_OK){
            leer();
            stats();
        } else if (requestCode==REQUEST_CODE2 && resultCode==1){
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
            builder.setTitle("ERROR al añadir");
            builder.setMessage("No se ha podido modificar el partido porque existían campos vacíos.");
            builder.setPositiveButton("Aceptar", null);
            builder.create().show();
        } else if (requestCode==REQUEST_CODE2 && resultCode==RESULT_CANCELED){
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Cancelado");
            builder.setMessage("No se han guardado los cambios.");
            builder.setPositiveButton("Aceptar", null);
            builder.create().show();
        } else if (requestCode==REQUEST_CODE3){
            leer();
            stats();
        }
    }

    public void activityPartidos(){
        MainActivity.this.startActivityForResult(new Intent(this, Partidos.class),REQUEST_CODE3);
    }

    public void activityAdd(){
        MainActivity.this.startActivityForResult(new Intent(this,Anhadir.class), REQUEST_CODE);
    }

    public void leer(){
        SharedPreferences prefs = getSharedPreferences("partidos", Context.MODE_PRIVATE);
        int tamanho = Integer.valueOf(prefs.getString("tamanho","0"));

        MainActivity.this.partidos.clear();
        for(int i=0;i<tamanho;i++){
            String oponente = prefs.getString("oponente"+i,"");
            int puntos = Integer.valueOf(prefs.getString("puntos"+i,"0"));
            int rebotes = Integer.valueOf(prefs.getString("rebotes"+i,"0"));
            int asistencias = Integer.valueOf(prefs.getString("asistencias"+i,"0"));
            int robos = Integer.valueOf(prefs.getString("robos"+i,"0"));
            int tapones = Integer.valueOf(prefs.getString("tapones"+i,"0"));
            int faltasRecibidas = Integer.valueOf(prefs.getString("faltasRecibidas"+i,"0"));
            int faltasRealizadas = Integer.valueOf(prefs.getString("faltasRealizadas"+i,"0"));
            int tirosFallados = Integer.valueOf(prefs.getString("tirosFallados"+i,"0"));
            int libresFallados = Integer.valueOf(prefs.getString("libresFallados"+i,"0"));
            int taponesRecibidos = Integer.valueOf(prefs.getString("taponesRecibidos"+i,"0"));
            int perdidas = Integer.valueOf(prefs.getString("perdidas"+i,"0"));

            Partido partido = new Partido(oponente,puntos,rebotes,asistencias,robos,tapones,faltasRecibidas,faltasRealizadas,tirosFallados,libresFallados,taponesRecibidos,perdidas);

            MainActivity.this.partidos.add(partido);
        }
    }

    public void stats(){
        MainActivity.this.adapter.clear();
        puntos();
        asistencias();
        robos();
        rebotes();
        tapones();
        faltasRealizadas();
        faltasRecibidas();
        tirosFallados();
        libresFallados();
        taponesRecibidos();
        perdidas();
        media();
    }

    public void media(){
        int media=0;
        for (int i =0;i<MainActivity.this.partidos.size();i++){
            media = media + partidos.get(i).valoracion();
        }
        TextView val = (TextView) this.findViewById(R.id.media);
        if (MainActivity.this.partidos.size()==0){
            val.setText("Valoración media: 0");
        } else {
            val.setText("Valoración media: " + media/MainActivity.this.partidos.size());
        }

    }

    public void puntos(){
        int puntosTotales=0;
        for (int i =0;i<MainActivity.this.partidos.size();i++){
           puntosTotales = puntosTotales + partidos.get(i).getPuntos();
        }
        if (MainActivity.this.partidos.size()!=0){
            adapter.add("Puntos por partido: " + puntosTotales/MainActivity.this.partidos.size());
        }

    }
    public void asistencias(){
        int asistenciasTotales=0;
        for (int i =0;i<MainActivity.this.partidos.size();i++){
            asistenciasTotales = asistenciasTotales + partidos.get(i).getAsistencias();
        }
        if (MainActivity.this.partidos.size()!=0){
            adapter.add("Asistencias por partido: " + asistenciasTotales/MainActivity.this.partidos.size());
        }
    }
    public void robos(){
        int rebotesTotales=0;
        for (int i =0;i<MainActivity.this.partidos.size();i++){
            rebotesTotales = rebotesTotales + partidos.get(i).getRobos();
        }
        if (MainActivity.this.partidos.size()!=0){
            adapter.add("Robos por partido: " + rebotesTotales/MainActivity.this.partidos.size());
        }

    }
    public void tapones(){
        int rebotesTotales=0;
        for (int i =0;i<MainActivity.this.partidos.size();i++){
            rebotesTotales = rebotesTotales + partidos.get(i).getTapones();
        }
        if (MainActivity.this.partidos.size()!=0){
            adapter.add("Tapones por partido: " + rebotesTotales/MainActivity.this.partidos.size());
        }

    }
    public void faltasRealizadas(){
        int rebotesTotales=0;
        for (int i =0;i<MainActivity.this.partidos.size();i++){
            rebotesTotales = rebotesTotales + partidos.get(i).getFaltasRealizadas();
        }
        if (MainActivity.this.partidos.size()!=0){
            adapter.add("Faltas por partido: " + rebotesTotales/MainActivity.this.partidos.size());
        }

    }
    public void faltasRecibidas(){
        int rebotesTotales=0;
        for (int i =0;i<MainActivity.this.partidos.size();i++){
            rebotesTotales = rebotesTotales + partidos.get(i).getFaltasRecibidas();
        }
        if (MainActivity.this.partidos.size()!=0){
            adapter.add("Faltas recibidas por partido: " + rebotesTotales/MainActivity.this.partidos.size());
        }

    }
    public void tirosFallados(){
        int rebotesTotales=0;
        for (int i =0;i<MainActivity.this.partidos.size();i++){
            rebotesTotales = rebotesTotales + partidos.get(i).getTirosFallados();
        }
        if (MainActivity.this.partidos.size()!=0){
            adapter.add("Tiros fallados por partido: " + rebotesTotales/MainActivity.this.partidos.size());
        }

    }
    public void libresFallados(){
        int rebotesTotales=0;
        for (int i =0;i<MainActivity.this.partidos.size();i++){
            rebotesTotales = rebotesTotales + partidos.get(i).getLibresFallados();
        }
        if (MainActivity.this.partidos.size()!=0){
            adapter.add("Tiros libres fallados por partido: " + rebotesTotales/MainActivity.this.partidos.size());
        }

    }
    public void taponesRecibidos(){
        int rebotesTotales=0;
        for (int i =0;i<MainActivity.this.partidos.size();i++){
            rebotesTotales = rebotesTotales + partidos.get(i).getTaponesRecibidos();
        }
        if (MainActivity.this.partidos.size()!=0){
            adapter.add("Tapones recibidos por partido: " + rebotesTotales/MainActivity.this.partidos.size());
        }

    }
    public void perdidas(){
        int rebotesTotales=0;
        for (int i =0;i<MainActivity.this.partidos.size();i++){
            rebotesTotales = rebotesTotales + partidos.get(i).getPerdidas();
        }
        if (MainActivity.this.partidos.size()!=0){
            adapter.add("Perdidas por partido: " + rebotesTotales/MainActivity.this.partidos.size());
        }

    }
    public void rebotes(){
        int rebotesTotales=0;
        for (int i =0;i<MainActivity.this.partidos.size();i++){
            rebotesTotales = rebotesTotales + partidos.get(i).getRebotes();
        }
        if (MainActivity.this.partidos.size()!=0){
            adapter.add("Rebotes por partido: " + rebotesTotales/MainActivity.this.partidos.size());
        }

    }

}

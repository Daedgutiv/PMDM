package com.example.navidades.view;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.navidades.R;
import com.example.navidades.core.Partido;

import java.util.ArrayList;

public class Partidos extends Activity {

    private final int REQUEST_CODE=1;
    private ArrayList<Partido> partidos;
    private ArrayList<String> lista;
    private ArrayAdapter<String> adapter;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.partidos);

        Button btVolver = (Button) this.findViewById(R.id.btVolver);

        btVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Partidos.this.finish();
            }
        });

        this.partidos= new ArrayList<Partido>();
        this.lista = new ArrayList<String>();

        ListView list = (ListView) this.findViewById(R.id.lista);
        this.registerForContextMenu(list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Partidos.this.modificar(position);
            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Partidos.this.eliminar(position);
                return true;
            }
        });
        this.adapter = new ArrayAdapter<String>(this.getApplicationContext(),android.R.layout.simple_selectable_list_item, this.lista);
        list.setAdapter(this.adapter);
        leer();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (REQUEST_CODE == requestCode && resultCode==RESULT_OK){
            leer();
        } else if (requestCode==REQUEST_CODE && resultCode==1){
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(Partidos.this);
            builder.setTitle("ERROR al añadir");
            builder.setMessage("No se ha podido modificar el partido porque existían campos vacíos.");
            builder.setPositiveButton("Aceptar", null);
            builder.create().show();
        } else if (requestCode==REQUEST_CODE && resultCode==RESULT_CANCELED){
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(Partidos.this);
            builder.setTitle("Cancelado");
            builder.setMessage("No se han guardado los cambios.");
            builder.setPositiveButton("Aceptar", null);
            builder.create().show();
        }
    }

    public void leer(){
        SharedPreferences prefs = getSharedPreferences("partidos", Context.MODE_PRIVATE);
        int tamanho = Integer.valueOf(prefs.getString("tamanho","0"));

        Partidos.this.partidos.clear();
        Partidos.this.adapter.clear();
        Partidos.this.lista.clear();
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

            Partidos.this.partidos.add(partido);
            Partidos.this.adapter.add(partido.toString());
        }
    }

    public void modificar(int pos){
        if (pos >=0){
            activityModificar(pos);
        }
    }

    public void activityModificar(int pos){
        Intent intent = new Intent(this,Modificar.class);
        intent.putExtra("partido",pos);
        startActivityForResult(intent, REQUEST_CODE);
    }

    public void eliminar(final int pos){
        if (pos>=0){
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(Partidos.this);
            builder.setTitle("Borrado");
            builder.setMessage("Está a punto de borrar el partido: " + Partidos.this.partidos.get(pos).toString());
            builder.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Partidos.this.partidos.remove(pos);
                    Partidos.this.lista.remove(pos);
                    Partidos.this.adapter.notifyDataSetChanged();
                    escribir();
                }
            });
            builder.setNegativeButton("Cancelar", null);
            builder.create().show();
        }

    }

    public void escribir(){
        SharedPreferences prefs = Partidos.this.getSharedPreferences("partidos", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = prefs.edit();

        String aux = prefs.getString("tamanho", "0");
        edit.putString("tamanho",String.valueOf(Integer.valueOf(aux)-1));
        for(int i = 0; i<partidos.size();i++){
            edit.putString("oponente" + i, partidos.get(i).getOponente());
            edit.putString("puntos" + i, String.valueOf( partidos.get(i).getPuntos()));
            edit.putString("rebotes" + i, String.valueOf( partidos.get(i).getRebotes()));
            edit.putString("asistencias" + i, String.valueOf( partidos.get(i).getAsistencias()));
            edit.putString("robos" + i, String.valueOf( partidos.get(i).getRobos()));
            edit.putString("tapones" + i, String.valueOf( partidos.get(i).getTapones()));
            edit.putString("faltasRealizadas" + i, String.valueOf( partidos.get(i).getFaltasRealizadas()));
            edit.putString("faltasRecibidas" + i, String.valueOf( partidos.get(i).getFaltasRecibidas()));
            edit.putString("tirosFallados" + i, String.valueOf( partidos.get(i).getTirosFallados()));
            edit.putString("libresFallados" + i, String.valueOf( partidos.get(i).getLibresFallados()));
            edit.putString("taponesRecibidos" + i, String.valueOf( partidos.get(i).getTaponesRecibidos()));
            edit.putString("perdidas" + i, String.valueOf( partidos.get(i).getPerdidas()));
        }
        edit.apply();
    }


}

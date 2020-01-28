package com.example.watertraining.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.watertraining.R;
import com.example.watertraining.core.AplicattionEntrenamientos;
import com.example.watertraining.core.Entrenamiento;

public class MainActivity extends AppCompatActivity {

    //adaptador para la lista
    private ArrayAdapter<Entrenamiento> adaptadorEntrenamientos;
    //codigos de las actividades
    protected static final int CODIGO_ADD_ENTRENAMIENTO = 102;
    protected static final int CODIGO_EDIT_ENTRENAMIENTO = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //declaracion de la aplicacion para utilizar la lista de entrenamientos
        final AplicattionEntrenamientos app = (AplicattionEntrenamientos) this.getApplication();

        ListView lista = (ListView) this.findViewById(R.id.entrenamientos);

        this.registerForContextMenu(lista);

        Button btadd = (Button) findViewById(R.id.btAñadir);
        btadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent subActividad = new Intent(MainActivity.this, AddEntrenamiento.class);
                subActividad.putExtra("pos", -1);
                MainActivity.this.startActivityForResult(subActividad, CODIGO_ADD_ENTRENAMIENTO);
            }
        });

        this.adaptadorEntrenamientos = new ArrayAdapter<Entrenamiento>(this, android.R.layout.simple_selectable_list_item, app.getEntrenamientos());
        lista.setAdapter(this.adaptadorEntrenamientos);

    }

    //para crear el context menu
    @Override
    public void onCreateContextMenu(ContextMenu cm, View v, ContextMenu.ContextMenuInfo cmi){
        this.getMenuInflater().inflate(R.menu.context_menu, cm);
    }

    //lo que hacen las opciones del context menu
    @Override
    public boolean onContextItemSelected(MenuItem menuItem){
        boolean toret = false;
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();
        int pos = info.position;
        switch (menuItem.getItemId()){
            case R.id.change:
                Intent subActividad = new Intent(MainActivity.this, AddEntrenamiento.class);
                subActividad.putExtra("pos", pos);
                MainActivity.this.startActivityForResult(subActividad, CODIGO_EDIT_ENTRENAMIENTO);
                toret = true;
                break;
            case R.id.delete:
                borrarDialog(pos);
                toret =true;
                break;
        }
        return toret;
    }

    //crear menu de la actividad
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        this.getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // Indica que se hace en cada opcion del menu
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        boolean toret = false;
        switch (menuItem.getItemId()){
            case R.id.add:
                Intent subActividad = new Intent(MainActivity.this, AddEntrenamiento.class);
                subActividad.putExtra("pos", -1);
                MainActivity.this.startActivityForResult(subActividad, CODIGO_ADD_ENTRENAMIENTO);
                toret=true;
                break;
            case R.id.stats:
                Intent subActividad2 = new Intent(MainActivity.this, Estadisticas.class);
                MainActivity.this.startActivity(subActividad2);
                toret=true;
                break;
        }
        return toret;
    }

    //Que hacer segun el resultado de las actividades segun su codigo
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CODIGO_ADD_ENTRENAMIENTO && resultCode == Activity.RESULT_OK) {
            this.adaptadorEntrenamientos.notifyDataSetChanged();
        }
        if (requestCode == CODIGO_EDIT_ENTRENAMIENTO && resultCode == Activity.RESULT_OK) {
            this.adaptadorEntrenamientos.notifyDataSetChanged();
        }

        return;
    }

    //crear un dialog para conprobar si se quiere borrar
    protected void borrarDialog (final int pos){
        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Borrado");
        builder.setMessage("Está seguro que quiere borrar este entrenamiento?");
        builder.setNegativeButton("No borrar", null);
        builder.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final AplicattionEntrenamientos app = (AplicattionEntrenamientos) MainActivity.this.getApplication();
                app.eliminarEntrenamiento(pos);
                MainActivity.this.adaptadorEntrenamientos.notifyDataSetChanged();
            }
        });

        builder.create().show();

    }




}

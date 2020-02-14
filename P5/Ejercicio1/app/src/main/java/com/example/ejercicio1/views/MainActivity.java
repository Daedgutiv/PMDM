package com.example.ejercicio1.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ejercicio1.BBDD.SQLOH;
import com.example.ejercicio1.R;
import com.example.ejercicio1.core.Contacto;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    protected static final int CODIGO_EDICION_CONTACTO = 100;
    protected static final int CODIGO_DETALLE_CONTACTO = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lista = this.findViewById(R.id.contactos);
        ImageButton btAnhadir = this.findViewById(R.id.btAnhadir);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                         @Override
                                         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                             Intent detalle = new Intent(MainActivity.this, Detalles.class);
                                             Cursor cursor = MainActivity.this.adpatadorDB.getCursor();
                                             final int aux = cursor.getInt(0);
                                             Contacto contacto = new Contacto();
                                             contacto.setId(aux);
                                             detalle.putExtra("id", contacto.getId());
                                             MainActivity.this.startActivityForResult(detalle, CODIGO_DETALLE_CONTACTO);
                                         }
                                     });

                btAnhadir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Contacto contacto = new Contacto();
                        contacto.setId(-1);
                        subActividad(contacto);
                    }
                });

        this.registerForContextMenu(lista);
        this.gestorDB = new SQLOH(this.getApplicationContext());
    }

    @Override
    public void onStart(){
        super.onStart();

        final ListView lista = this.findViewById(R.id.contactos);

        this.adpatadorDB = new SimpleCursorAdapter(
                this, R.layout.lista_contactos,
                null,
                new String[]{SQLOH.CONTACTO_NOMBRE, SQLOH.CONTACTO_APELLIDO1, SQLOH.CONTACTO_APELLIDO2},
                new int[]{R.id.conNombre, R.id.conApellido1, R.id.conApellido2}
        );
        lista.setAdapter(this.adpatadorDB);
        this.actualizaLista();
    }

    @Override
    public void onPause(){
        super.onPause();

        this.gestorDB.close();
        this.adpatadorDB.getCursor().close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        this.getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem menuItem){
        boolean toret = false;
        switch (menuItem.getItemId() ){
            case R.id.menu_anhadir:
                Contacto contacto = new Contacto();
                contacto.setId(-1);
                subActividad(contacto);
                break;
        }
        return toret;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v.getId() == R.id.contactos){
            this.getMenuInflater().inflate(R.menu.menu_contextual, menu);
        }

        return;
    }

    @Override
    public boolean onContextItemSelected(MenuItem contacto){
        boolean toret=false;
        int pos = ((AdapterView.AdapterContextMenuInfo) contacto.getMenuInfo()).position;
        Cursor cursor = this.adpatadorDB.getCursor();

        switch (contacto.getItemId()){
            case R.id.item_contextual_elimina:
                if (cursor.moveToPosition(pos)){
                    final int id = cursor.getInt(0);
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Confirmación");
                    builder.setMessage("Seguro que desea borrar este contacto? Todos sus datos serán eliminados.");
                    builder.setNegativeButton("Cancelar", null);
                    builder.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.this.gestorDB.eliminarCotacto(id);
                            MainActivity.this.actualizaLista();
                        }
                    });
                    builder.create().show();

                    toret=true;
                } else {
                    String msg = "Posición incorrecta: " + pos;
                    Log.e("context_elimina", msg);
                    Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.item_contextual_modifica:
                if(cursor.moveToPosition(pos)){
                    final int id = cursor.getInt(0);
                    Contacto aux= new Contacto();
                    aux.setId(id);
                    subActividad(aux);
                    toret=true;
                } else {
                    String msg = "Posición incorrecta: " + pos;
                    Log.e("context_modifica", msg);
                    Toast.makeText(this,msg, Toast.LENGTH_LONG);
                }

                break;
        }
        return toret;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent retData){
        if (resultCode== Activity.RESULT_OK && requestCode == CODIGO_EDICION_CONTACTO){
            this.actualizaLista();
        }

        if (requestCode==CODIGO_DETALLE_CONTACTO && resultCode == Activity.RESULT_OK){
            this.actualizaLista();
        }
        return;
    }

    private void actualizaLista(){
        final TextView contador = this.findViewById(R.id.contador);
        System.out.println("Aqui no 0");
        this.adpatadorDB.changeCursor(this.gestorDB.recuperar());
        System.out.println("Aqui no ultimo");
        contador.setText(String.format(Locale.getDefault(), "%d", this.adpatadorDB.getCount()));
    }

    private void subActividad(Contacto contacto){
        Intent subactividad = new Intent(MainActivity.this, Subactividad.class);

        subactividad.putExtra("id", contacto.getId());
        MainActivity.this.startActivityForResult(subactividad, CODIGO_EDICION_CONTACTO);
    }

    private SQLOH gestorDB;
    private SimpleCursorAdapter adpatadorDB;
}

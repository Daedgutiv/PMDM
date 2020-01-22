package com.example.todo.view;

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
import android.widget.TextView;

import com.example.todo.R;
import com.example.todo.core.Task;
import com.example.todo.core.Tasks;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<Task> adaptadorItems;
    protected static final int CODIGO_ADD_TASK = 102;
    protected static final int CODIGO_EDIT_TASK = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Tasks app = (Tasks) this.getApplication();

        ListView lista = (ListView) this.findViewById(R.id.lvItems);

        this.registerForContextMenu(lista);

        Button btadd = (Button) findViewById(R.id.btAdd);
        btadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent subActividad = new Intent(MainActivity.this, AddTask.class);
                subActividad.putExtra("pos", -1);
                MainActivity.this.startActivityForResult(subActividad, CODIGO_ADD_TASK);
            }
        });
        this.adaptadorItems = new ArrayAdapter<Task>(this,android.R.layout.simple_selectable_list_item,app.getTasks() );
        lista.setAdapter( this.adaptadorItems );
    }

    @Override
    public void onCreateContextMenu(ContextMenu cm, View v, ContextMenu.ContextMenuInfo cmi){
        this.getMenuInflater().inflate(R.menu.context_menu, cm);
    }

    @Override
    public boolean onContextItemSelected(MenuItem menuItem){
        boolean toret = false;
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();
        int pos = info.position;
        switch (menuItem.getItemId()){
            case R.id.change:
                Intent subActividad = new Intent(MainActivity.this, AddTask.class);
                subActividad.putExtra("pos", pos);
                MainActivity.this.startActivityForResult(subActividad, CODIGO_EDIT_TASK);
                toret = true;
                break;
            case R.id.delete:
                final Tasks app = (Tasks) this.getApplication();
                app.eliminarTask(pos);
                this.adaptadorItems.notifyDataSetChanged();
                toret =true;
                break;
        }
        return toret;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        this.getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        boolean toret = false;
        switch (menuItem.getItemId()){
            case R.id.add:
                Intent subActividad = new Intent(MainActivity.this, AddTask.class);
                subActividad.putExtra("pos", -1);
                MainActivity.this.startActivityForResult(subActividad, CODIGO_ADD_TASK);
                toret=true;
                break;
        }
        return toret;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CODIGO_ADD_TASK && resultCode == Activity.RESULT_OK) {
            this.adaptadorItems.notifyDataSetChanged();
        }
        if (requestCode == CODIGO_EDIT_TASK && resultCode == Activity.RESULT_OK) {
            this.adaptadorItems.notifyDataSetChanged();
        }

        return;
    }

    @Override
    public void onResume(){
        super.onResume();

        final Tasks app = (Tasks) this.getApplication();

        List<Task> lista;

        lista = app.getTasks();

        List<Integer> posiciones = new ArrayList<Integer>();

        for (int i = 0; i<lista.size();i++){
            if (lista.get(i).isFinish()){
                posiciones.add(i);
            }
        }

        if (posiciones.size()!=0){
            alerta(posiciones);
        }

    }

    public void alerta(final List<Integer> posiciones){

        final Tasks app = (Tasks) this.getApplication();

        List<Task> lista;

        lista = app.getTasks();

        String[] strings = new String[posiciones.size()];
        final boolean[] selected = new boolean[posiciones.size()];
        final List<Boolean> borrar = new ArrayList<Boolean>();

        for (int i = 0; i< posiciones.size();i++){
            strings[i] = lista.get(posiciones.get(i)).toString();
            selected[i]= false;
            borrar.add(false);
        }

        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Tareas Caducadas");

        builder.setMultiChoiceItems(strings, selected, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                selected[which] = isChecked;
                borrar.set(which,isChecked);
            }
        });

        builder.setPositiveButton("Borrar tarea", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int aux=0;
                for (int i = 0; i < borrar.size();i++){
                    if(borrar.get(i)){
                        eliminar(aux, posiciones, i);
                        aux++;
                    }
                }
            }
        });
        builder.setNegativeButton("No borrar tareas", null);
        builder.create().show();

    }

    public void eliminar(int aux, List<Integer> posiciones, int i){

        final Tasks app = (Tasks) this.getApplication();

        app.eliminarTask((posiciones.get(i)-aux));
        this.adaptadorItems.notifyDataSetChanged();
    }

}

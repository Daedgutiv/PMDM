package com.example.ejercicio2.ui;

import androidx.appcompat.app.AppCompatActivity;

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

import com.example.ejercicio2.R;
import com.example.ejercicio2.core.Task;
import com.example.ejercicio2.core.Tasks;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<Task> adaptadorItems;
    protected static final int CODIGO_ADD_TASK = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Tasks app = (Tasks) this.getApplication();

        ListView lista = (ListView) this.findViewById(R.id.lvItems);

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

                toret = true;
                break;
            case R.id.delete:

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

                toret=true;
                break;
        }
        return toret;
    }


}

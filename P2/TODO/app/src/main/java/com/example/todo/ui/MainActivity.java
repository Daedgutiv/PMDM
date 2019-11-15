package com.example.todo.ui;

import androidx.appcompat.app.AlertDialog;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import com.example.todo.R;
import com.example.todo.core.ListAdapter;
import com.example.todo.core.Tasks;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private ArrayList<Tasks> items;
    private ListAdapter la;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.items = new ArrayList<Tasks>();

        Button btAdd = (Button) this.findViewById( R.id.btAdd );
        ListView lvItems = (ListView) this.findViewById(R.id.lvItems);
        ListAdapter la = new ListAdapter(this,items);

        lvItems.setAdapter( this.la );
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int pos, long l){
                final EditText editText = new EditText(MainActivity.this);

                editText.setText(MainActivity.this.items.get(pos).getTask());
                if (pos>=0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Modificar");
                    builder.setView(editText);
                    builder.setNegativeButton("Cancelar",null);
                    builder.setPositiveButton("Modificar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            items.get(pos).setTask(editText.getText().toString());
                        }
                    });
                    builder.create().show();
                }
            }});
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int pos, long l) {
                if ( pos >= 0 ) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Borrado");
                    builder.setMessage("Seguro que quieres borrar el elemento: '" + MainActivity.this.items.get(pos).toString() +"'?");
                    builder.setPositiveButton("Borrar",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.this.items.remove( pos );
                            MainActivity.this.la.notifyDataSetChanged();
                        }
                    });
                    builder.setNegativeButton("Cancelar", null);
                    builder.create().show();

                }
                return true;
            }
        });

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.onAdd();
            }
        });
    }

    private void onAdd() {
        final EditText edText = new EditText( this );


        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setTitle("Nueva tarea");
        builder.setMessage( "¿Qué quiere recordar?");
        builder.setView( edText );
        builder.setPositiveButton( "+", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Tasks task = new Tasks();
                task.setTask(edText.getText().toString());
                introducirFecha(task);
                items.add(task);
                MainActivity.this.la.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.create().show();
    }

    private boolean introducirFecha (final Tasks task){
        DatePickerDialog date = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            task.setAnho(year);
            task.setMes(month);
            task.setDia(dayOfMonth);
            }
        },
                2019,04,8
                );
        date.show();
        return true;
    }

}

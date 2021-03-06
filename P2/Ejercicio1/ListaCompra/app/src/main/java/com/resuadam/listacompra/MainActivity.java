package com.resuadam.listacompra;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;


public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        this.items = new ArrayList<String>();

        Button btAdd = (Button) this.findViewById( R.id.btAdd );
        ListView lvItems = (ListView) this.findViewById( R.id.lvItems );

        lvItems.setLongClickable( true );
        this.itemsAdapter = new ArrayAdapter<String>(
                this.getApplicationContext(),
                android.R.layout.simple_selectable_list_item,
                this.items
        );
        lvItems.setAdapter( this.itemsAdapter );
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int pos, long l){
                final EditText editText = new EditText(MainActivity.this);
                String texto = MainActivity.this.items.toString();

                editText.setText(MainActivity.this.items.get(pos).toString());
                if (pos>=0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Modificar");
                    builder.setView(editText);
                    builder.setNegativeButton("Cancelar",null);
                    builder.setPositiveButton("Modificar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.this.items.set(pos,editText.getText().toString());
                            MainActivity.this.itemsAdapter.notifyDataSetChanged();
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
                   builder.setTitle("Exit");
                   builder.setMessage("Seguro que quieres borrar el elemento: '" + MainActivity.this.items.get(pos).toString() +"'?");
                   builder.setPositiveButton("Borrar",new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           MainActivity.this.items.remove( pos );
                           MainActivity.this.itemsAdapter.notifyDataSetChanged();
                           MainActivity.this.updateStatus();
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
        builder.setTitle("A comprar...");
        builder.setMessage( "Nombre" );
        builder.setView( edText );
        builder.setPositiveButton( "+", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final String text = edText.getText().toString();

                MainActivity.this.itemsAdapter.add( text );
                MainActivity.this.updateStatus();
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.create().show();
    }

    private void updateStatus() {
        TextView txtNum = (TextView) this.findViewById( R.id.lblNum );
        txtNum.setText( Integer.toString( this.itemsAdapter.getCount() ) );
    }

    private ArrayAdapter<String> itemsAdapter;
    private ArrayList<String> items;
    private boolean introducirFecha (final String aux){
        DatePickerDialog date = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            }
        },
                2019,04,8
        );
        date.show();
        return true;
    }
}
package com.example.todo.ui;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import com.example.todo.R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Pattern;
public class MainActivity extends Activity {
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
                String texto = MainActivity.this.items.get(pos).toString();
                final String[] partes = texto.split(Pattern.quote("(") );
                editText.setText(partes[0]);
                if (pos>=0){
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Modificar");
                    builder.setView(editText);
                    builder.setNegativeButton("Cancelar",null);
                    builder.setPositiveButton("Modificar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            introducirFecha2(editText.getText().toString(),pos,partes[1]);
                        }
                    });
                    builder.create().show();
                }
            }});
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int pos, long l) {
                if ( pos >= 0 ) {

                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Borrado");
                    builder.setMessage("Seguro que quieres borrar el elemento: '" + MainActivity.this.items.get(pos).toString() +"'?");
                    builder.setPositiveButton("Borrar",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                           removeItem(pos);
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
    ArrayList<Integer> posiciones = new ArrayList<Integer>();
    @Override
    public void onResume() {
        super.onResume();
        caducados(0);
    }
    private void caducados(int aux){
        posiciones.clear();
        Calendar now = Calendar.getInstance();
        int anho= now.get(Calendar.YEAR);
        int mes= now.get(Calendar.MONTH);
        int dia = now.get(Calendar.DATE);

        for (int i = aux; i<items.size();i++){
            String[] partes = items.get(i).split(Pattern.quote("(") );
            String[] partes2 = partes[1].split("/");
            int a,b,c;
            a=Integer.valueOf(partes2[0]);
            System.out.println("+++++++" + a + "--------");
            b=Integer.valueOf(partes2[1]);
            System.out.println("+++++++" + b + "--------");
            String c2="";
            for(int j=0;j<partes2[2].length()-1;j++){
                c2=c2 + partes2[2].charAt(j);
            }
            c=Integer.valueOf(c2);
            System.out.println("+++++++" + c + "--------");
            if (c<anho){
                System.out.println("+++++++" + "Entro anho" + "--------");
                posiciones.add(i);
            } else if(b<mes){
                System.out.println("+++++++" + "Entro mes" + "--------");
                posiciones.add(i);
            } else if(a<dia){
                System.out.println("+++++++" + "Entro dia" + "--------");
                posiciones.add(i);
            }
        }
        System.out.println("+++++++" + posiciones.toString() + "--------");
        alerta(0);
    }
    private void removeItem (int pos){
        MainActivity.this.items.remove( pos );
        MainActivity.this.itemsAdapter.notifyDataSetChanged();
    }
    private void alerta(final int aux){
    System.out.println("aux="+ aux);
    System.out.println("Tamaño = "+ posiciones.size());
        if (aux<posiciones.size()){
            android.app.AlertDialog.Builder builder = new AlertDialog.Builder( this );
            builder.setTitle("Tarea Caducada");
            String[] texto =  MainActivity.this.items.get(posiciones.get(aux)).split(Pattern.quote("(") );
            builder.setMessage( "Su tarea \"" + texto[0] + "\" ha caducado.");
            builder.setPositiveButton("Borrar Tarea", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    removeItem(posiciones.get(aux));
                    caducados(posiciones.get(aux));
                }
            });
            builder.setNegativeButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    alerta(aux+1);
                }
            });
            builder.create().show();
        }
    }
    private void onAdd() {
        final EditText edText = new EditText( this );

        android.app.AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setTitle("Nueva Tarea");
        builder.setMessage( "¿Qué desea recordar?" );
        builder.setView( edText );
        builder.setPositiveButton("+", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!edText.getText().toString().isEmpty()){
                    introducirFecha(edText.getText().toString());
                } else {
                    vacio();
                }
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.create().show();

    }
    private void vacio(){
        android.app.AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setTitle("Tarea vacía");
        builder.setMessage( "Lo sentimos pero no podemos añadir una tarea vacía.");
        builder.setPositiveButton("Reintentar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              MainActivity.this.onAdd();
            }
        });
        builder.setNegativeButton("Aceptar",null);
        builder.create().show();
    }
    String aux="";
    private void introducirFecha (final String texto){
        Calendar now = Calendar.getInstance();
        int anho= now.get(Calendar.YEAR);
        int mes= now.get(Calendar.MONTH);
        int dia = now.get(Calendar.DATE);
        mes--;
        final DatePickerDialog date = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                aux = day + "/" + (month + 1) + "/" + year;
                MainActivity.this.itemsAdapter.add(texto +"(" + aux +")");
            }
        },
                anho,mes,dia
        );
        date.show();
    }
    private void introducirFecha2 (final String texto,final int pos, String texto2){
        String[] partes = texto2.split("/");
        int a,b,c;
        a=Integer.valueOf(partes[0]);
        b=Integer.valueOf(partes[1]);
        b--;
       String c2 = partes[2].substring(0,partes[2].length()-1);
        c=Integer.valueOf(c2);
        final DatePickerDialog date = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                aux = day + "/" + (month + 1) + "/" + year;
                MainActivity.this.items.set(pos,texto + " (" + aux +")");
                MainActivity.this.itemsAdapter.notifyDataSetChanged();
            }
        },
               c,b,a

                );
        date.show();
    }
    private ArrayAdapter<String> itemsAdapter;
    private ArrayList<String> items;
}

package com.example.watertraining.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.watertraining.R;
import com.example.watertraining.core.Entrenamiento;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Entrenamiento> entrenamientos;
    ArrayList<String> stringEnt;
    ArrayAdapter<String> adapter;
    DecimalFormatSymbols dfs;
    DecimalFormat formato = new DecimalFormat("#0.00");

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences("misPreferencias", Context.MODE_PRIVATE);
        int tam = Integer.valueOf(prefs.getString("tamaño","0"));
        for(int i = 0; i<tam; i++) {
            String distancia = prefs.getString("distancia" + i, "0");
            String tiempo = prefs.getString("tiempo" + i, "0");
            Entrenamiento aux = new Entrenamiento();
            aux.setDistance(Float.valueOf(distancia));
            aux.setMin(Float.valueOf(tiempo));
            System.out.println(aux.toString());
            MainActivity.this.entrenamientos.add(aux);
            MainActivity.this.adapter.add(aux.toString());
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        SharedPreferences prefs = getSharedPreferences("misPreferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("tamaño", String.valueOf(entrenamientos.size()));
        for(int i=0;i<entrenamientos.size();i++){
            edit.putString("distancia" + i, String.valueOf(entrenamientos.get(i).getDistance()));
            edit.putString("tiempo" + i, String.valueOf(entrenamientos.get(i).getMin()));

        }
        edit.apply();

        entrenamientos.clear();
        stringEnt.clear();
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
            case R.id.delete:
                MainActivity.this.delete(pos);
                toret=true;
                break;
            case R.id.modificar:
                MainActivity.this.modificar(pos);
                toret = true;
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
        switch(menuItem.getItemId()){
            case R.id.add:
                MainActivity.this.onAdd();
                toret = true;
                break;
            case R.id.stats:
                MainActivity.this.stats();
                toret = true;
                break;
        }

        return toret;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.entrenamientos = new ArrayList<Entrenamiento>();
        this.stringEnt = new ArrayList<String>();
        Button btañadir = (Button) this.findViewById(R.id.btAñadir);
        btañadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.onAdd();
            }
        });
        ListView lista = (ListView) this.findViewById(R.id.entrenamientos);
        this.registerForContextMenu(lista);
        lista.setLongClickable(true);
        this.adapter = new ArrayAdapter<String>(this.getApplicationContext(), android.R.layout.simple_selectable_list_item, this.stringEnt);
        lista.setAdapter(adapter);

    }

    private void delete (final int pos){
        if (pos >= 0) {

            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Borrado");
            builder.setMessage("Seguro que quieres borrar el elemento: '" + MainActivity.this.stringEnt.get(pos) + "'?");
            builder.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.this.stringEnt.remove(pos);
                    MainActivity.this.entrenamientos.remove(pos);
                    MainActivity.this.adapter.notifyDataSetChanged();
                }
            });
            builder.setNegativeButton("Cancelar", null);
            builder.create().show();
        }
    }

    private void modificar(final int pos) {
        if (pos >= 0) {
            android.app.AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Información del entrenamiento");
            String texto ="Distancia: " + MainActivity.this.entrenamientos.get(pos).getDistance() + " Km\nTiempo: " + MainActivity.this.entrenamientos.get(pos).getMin() + " min\nVelocidad media: " + formato.format(MainActivity.this.entrenamientos.get(pos).getKmHour()) + " Km/h\nMinutos por Km: " + formato.format(MainActivity.this.entrenamientos.get(pos).getMinKm());
            builder.setMessage(texto);
            builder.setPositiveButton("Modificar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Context context = MainActivity.this.getApplicationContext();

                    LinearLayout ly = new LinearLayout(context);

                    ly.setOrientation(LinearLayout.VERTICAL);

                    dfs = new DecimalFormatSymbols();
                    dfs.setDecimalSeparator('.');
                    formato = new DecimalFormat("#0.00",dfs);

                    final EditText distancia = new EditText(context);
                    distancia.setHint("Distancia: (Ejemplo: 9.9)");
                    distancia.setText(formato.format(entrenamientos.get(pos).getDistance()));
                    distancia.setKeyListener(DigitsKeyListener.getInstance("0123456789."));
                    ly.addView(distancia);

                    final EditText tiempo = new EditText(context);
                    tiempo.setHint("Tiempo: (Ejemplo: 9.9)");
                    tiempo.setText(formato.format(entrenamientos.get(pos).getMin()));
                    tiempo.setKeyListener(DigitsKeyListener.getInstance("0123456789."));
                    ly.addView(tiempo);

                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Modificar");
                    builder.setView(ly);
                    builder.setNegativeButton("Cancelar", null);
                    builder.setPositiveButton("Modificar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (!distancia.getText().toString().isEmpty() && !tiempo.getText().toString().isEmpty()) {
                                if (!isNumeric(distancia.getText().toString()) || !isNumeric(tiempo.getText().toString())) {
                                    noNum();
                                } else {
                                    entrenamientos.get(pos).setDistance(Float.valueOf(distancia.getText().toString()));
                                    entrenamientos.get(pos).setMin(Float.valueOf(tiempo.getText().toString()));
                                    MainActivity.this.stringEnt.set(pos, entrenamientos.get(pos).toString());
                                    MainActivity.this.adapter.notifyDataSetChanged();
                                }
                            } else {
                                vacio2(pos);
                            }
                        }
                    });
                    builder.create().show();
                }
            });
            builder.setNegativeButton("Atrás", null);
            builder.create().show();
        }

    }

    private void stats() {
        float totalKm = 0;
        float totalMinKm = 0;
        for (int i = 0; i < entrenamientos.size(); i++) {
            totalKm = totalKm + entrenamientos.get(i).getDistance();
            totalMinKm = totalMinKm + entrenamientos.get(i).getMinKm();
        }
        totalMinKm = totalMinKm / entrenamientos.size();
        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Estadísticas de los entrenamientos");
        String aux = formato.format(totalMinKm);
        if (aux.equalsIgnoreCase("NaN")){
            aux ="0";
        }
        builder.setMessage("Usted a recorrido un total de " + formato.format(totalKm) + " Km entre todos sus entrenamientos y además tiene una media de " + aux + " minutos por cada Km.");
        builder.setPositiveButton("Aceptar", null);
        builder.create().show();
    }

    private void vacio() {
        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Entrenamiento no válido");
        builder.setMessage("Lo sentimos pero no podemos añadir una Entrenamiento con campos vacíos.");
        builder.setPositiveButton("Reintentar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.onAdd();
            }
        });
        builder.setNegativeButton("Aceptar", null);
        builder.create().show();
    }

    private void vacio2(final int pos) {
        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Entrenamiento no válido");
        builder.setMessage("Lo sentimos pero no podemos los cambios porque algún campo estaba vacío.");
        builder.setPositiveButton("Aceptar", null);
        builder.create().show();
    }

    private void noNum() {
        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Entrenamiento no válido");
        builder.setMessage("Lo sentimos pero uno o ambos campos no estan en el formato válido.");
        builder.setPositiveButton("Reintentar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.onAdd();
            }
        });
        builder.setNegativeButton("Aceptar", null);
        builder.create().show();
    }

    public static boolean isNumeric(String cadena) {

        boolean resultado;

        try {
            Float.parseFloat(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }

    private void onAdd() {
        final Entrenamiento training = new Entrenamiento();
        Context context = MainActivity.this.getApplicationContext();
        LinearLayout ly = new LinearLayout(context);
        ly.setOrientation(LinearLayout.VERTICAL);
        final EditText distancia = new EditText(context);
        distancia.setHint("Distancia: (Ejemplo: 9.9)");
        distancia.setKeyListener(DigitsKeyListener.getInstance("0123456789."));
        ly.addView(distancia);
        final EditText tiempo = new EditText(context);
        tiempo.setHint("Tiempo: (Ejemplo: 9.9)");
        tiempo.setKeyListener(DigitsKeyListener.getInstance("0123456789."));
        ly.addView(tiempo);


        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Nuevo Entrenamiento");
        builder.setMessage("Introduzca los datos del entrenamiento:");
        builder.setView(ly);
        builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!distancia.getText().toString().isEmpty() && !tiempo.getText().toString().isEmpty()) {
                    if (!isNumeric(distancia.getText().toString()) || !isNumeric(tiempo.getText().toString())) {
                        noNum();
                    } else {
                        training.setDistance(Float.valueOf(distancia.getText().toString()));
                        training.setMin(Float.valueOf(tiempo.getText().toString()));
                        MainActivity.this.entrenamientos.add(training);
                        MainActivity.this.adapter.add(training.toString());
                    }
                } else {
                    vacio();
                }
            }
        });
        builder.setNegativeButton("Cancelar", null);
        builder.create().show();
    }
}

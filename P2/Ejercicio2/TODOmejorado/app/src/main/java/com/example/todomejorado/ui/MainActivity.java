package com.example.todomejorado.ui;

import androidx.appcompat.app.AppCompatActivity;

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

import com.example.todomejorado.R;
import com.example.todomejorado.core.Task;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.items = new ArrayList<String>();
        this.tasks = new ArrayList<Task>();
        Button btAdd = (Button) this.findViewById(R.id.btAdd);
        ListView lvItems = (ListView) this.findViewById(R.id.lvItems);
        lvItems.setLongClickable(true);
        this.itemsAdapter = new ArrayAdapter<String>(
                this.getApplicationContext(),
                android.R.layout.simple_selectable_list_item,
                this.items
        );
        lvItems.setAdapter(this.itemsAdapter);
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int pos, long l) {
                final EditText editText = new EditText(MainActivity.this);
                final String texto = MainActivity.this.tasks.get(pos).getTask();
                editText.setText(texto);
                if (pos >= 0) {
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Modificar");
                    builder.setView(editText);
                    builder.setNegativeButton("Cancelar", null);
                    builder.setPositiveButton("Modificar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            introducirFecha2(editText.getText().toString(), pos, texto);
                        }
                    });
                    builder.create().show();
                }
            }
        });
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int pos, long l) {
                if (pos >= 0) {

                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Borrado");
                    builder.setMessage("Seguro que quieres borrar el elemento: '" + MainActivity.this.items.get(pos).toString() + "'?");
                    builder.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
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

        caduca();
        this.selected = new boolean[posiciones.size()];
        this.selectedArray = new ArrayList<Boolean>();
        for (int x = 0; x < selected.length; x++) {
            selected[x] = false;
            selectedArray.add(false);
        }
        if (posiciones.size() != 0) {
            alerta();
        }

    }

    boolean[] selected;
    ArrayList<Boolean> selectedArray;

    private void caduca() {

        posiciones.clear();
        Calendar now = Calendar.getInstance();
        int anho = now.get(Calendar.YEAR);
        int mes = now.get(Calendar.MONTH);
        mes++;
        int dia = now.get(Calendar.DATE);
        for (int i = 0; i < items.size(); i++) {
            int a, b, c;
            a = tasks.get(i).getDia();
            System.out.println("+++++++" + a + "--------");
            b = tasks.get(i).getMes();
            System.out.println("+++++++" + b + "--------");
            c = tasks.get(i).getAnho();
            System.out.println("+++++++" + c + "--------");
            System.out.println("++++++" + b + "====" + mes + "--------");
            if (c < anho) {
                System.out.println("+++++++" + "Entro anho" + "--------");
                posiciones.add(i);
            } else if (b < mes) {

                System.out.println("+++++++" + "Entro mes" + "--------");
                posiciones.add(i);
            } else if (a < dia) {
                System.out.println("++++++" + a + "====" + dia + "--------");
                System.out.println("+++++++" + "Entro dia" + "--------");
                posiciones.add(i);
            }
        }
    }

    private void removeItem(int pos) {
        MainActivity.this.tasks.remove(pos);
        MainActivity.this.items.remove(pos);
        MainActivity.this.itemsAdapter.notifyDataSetChanged();
    }


    private void alerta() {

        String[] strings = new String[posiciones.size()];
        for (int i = 0; i < posiciones.size(); i++) {
            strings[i] = items.get(posiciones.get(i));
        }
        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Tarea Caducada");
        builder.setMultiChoiceItems(strings, selected, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                selected[which] = isChecked;
                selectedArray.set(which,isChecked);
            }
        });
        builder.setPositiveButton("Borrar Tarea", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                aux();
            }
        });
        builder.create().show();
    }

    private void aux() {
        for (int i = 0; i < selectedArray.size(); i++) {
            if (selectedArray.get(i)) {
                if (tasks.size() != 0) {
                    removeItem(posiciones.get(i));
                    selectedArray.remove(i);
                    caduca();
                    aux();
                }
                break;
            }
        }
    }

    private void onAdd() {
        final EditText edText = new EditText(this);

        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Nueva Tarea");
        builder.setMessage("¿Qué desea recordar?");
        builder.setView(edText);
        builder.setPositiveButton("+", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!edText.getText().toString().isEmpty()) {
                    introducirFecha(edText.getText().toString());
                } else {
                    vacio();
                }
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.create().show();

    }

    private void vacio() {
        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Tarea vacía");
        builder.setMessage("Lo sentimos pero no podemos añadir una tarea vacía.");
        builder.setPositiveButton("Reintentar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.onAdd();
            }
        });
        builder.setNegativeButton("Aceptar", null);
        builder.create().show();
    }

    String aux = "";

    private void introducirFecha(final String texto) {
        Calendar now = Calendar.getInstance();
        int anho = now.get(Calendar.YEAR);
        int mes = now.get(Calendar.MONTH);
        int dia = now.get(Calendar.DATE);
        final Task task = new Task();
        final DatePickerDialog date = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                aux = day + "/" + (month + 1) + "/" + year;
                task.setAnho(year);
                task.setMes(month + 1);
                task.setDia(day);
                task.setTask(texto);
                MainActivity.this.tasks.add(task);
                MainActivity.this.itemsAdapter.add(texto + "(" + aux + ")");
            }
        },
                anho, mes, dia
        );
        date.show();
    }

    private void introducirFecha2(final String texto, final int pos, String texto2) {
        int a, b, c;
        a = tasks.get(pos).getDia();
        b = tasks.get(pos).getMes();
        b--;
        c = tasks.get(pos).getAnho();
        final DatePickerDialog date = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                aux = day + "/" + (month + 1) + "/" + year;
                tasks.get(pos).setAnho(year);
                tasks.get(pos).setMes(month + 1);
                tasks.get(pos).setDia(day);
                tasks.get(pos).setTask(texto);
                MainActivity.this.items.set(pos, texto + " (" + aux + ")");
                MainActivity.this.itemsAdapter.notifyDataSetChanged();
            }
        },
                c, b, a

        );
        date.show();
    }

    private ArrayAdapter<String> itemsAdapter;
    private ArrayList<String> items;
    private ArrayList<Task> tasks;
}
package com.example.todo.view;

import android.app.Activity;
import android.app.Application;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todo.R;
import com.example.todo.core.Task;
import com.example.todo.core.Tasks;

import java.util.Calendar;

//actividad que permite añadir y modificar las tareas
public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Button btGuardar = (Button) this.findViewById(R.id.btGuardar);
        final Button btCancelar = (Button) this.findViewById(R.id.btCancelar);
        final Button btFecha = (Button) this.findViewById(R.id.btfecha);
        final EditText edTask = (EditText) this.findViewById(R.id.task);
        final Tasks app = (Tasks) this.getApplication();

        Intent datosEnviados = this.getIntent();
        final int pos = datosEnviados.getExtras().getInt("pos");
        final int ano;
        final int mes;
        final int dia;
        final Task Taux= new Task();

        final String task;
        if (pos>=0){
            task = app.getTasks().get(pos).getTask();
            ano = app.getTasks().get(pos).getAno();
            mes = app.getTasks().get(pos).getMes();
            dia = app.getTasks().get(pos).getDia();
        } else {
            task="";
            Calendar c = Calendar.getInstance();
            ano = c.get(Calendar.YEAR);
            mes= c.get(Calendar.MONTH);
           dia= c.get(Calendar.DATE);
        }

        Taux.setAno(ano);
        Taux.setMes(mes+1);
        Taux.setDia(dia);

        edTask.setText(task);
        String aux = dia + "/" + (mes+1) + "/" + ano;
        btFecha.setText(aux);

        btFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatePickerDialog date = new DatePickerDialog(AddTask.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Taux.setAno(year);
                        Taux.setDia(dayOfMonth);
                        Taux.setMes(month+1);
                        String aux = Taux.getDia() + "/" + (Taux.getMes()+1) + "/" + Taux.getAno();
                        btFecha.setText(aux);
                    }
                },
                        ano,mes,dia
                        );
                date.show();
                btGuardar.setEnabled(true);
            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTask.this.setResult(Activity.RESULT_CANCELED);
                AddTask.this.finish();
            }
        });

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String task = edTask.getText().toString();

                if (pos>=0){
                    app.modifiedTask(pos,task,Taux.getAno(),Taux.getMes(),Taux.getDia());
                } else {
                    app.addTask(task,Taux.getAno(),Taux.getMes(),Taux.getDia());
                }

                AddTask.this.setResult(Activity.RESULT_OK);
                AddTask.this.finish();
            }
        });

        btGuardar.setEnabled(false);

        edTask.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                btGuardar.setEnabled(edTask.getText().toString().trim().length()>0);
            }
        });



    }
}

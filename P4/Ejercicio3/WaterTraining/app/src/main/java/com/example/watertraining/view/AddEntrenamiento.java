package com.example.watertraining.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.watertraining.R;
import com.example.watertraining.core.AplicattionEntrenamientos;
import com.example.watertraining.core.Entrenamiento;

public class AddEntrenamiento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entrenamiento);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final Button btGuardar = (Button) this.findViewById(R.id.btGuardar);
        final Button btCancelar = (Button) this.findViewById(R.id.btCancelar);
        final EditText distancia = (EditText) this.findViewById(R.id.distancia);
        final EditText tiempo = (EditText) this.findViewById(R.id.tiempo);
        final AplicattionEntrenamientos app = (AplicattionEntrenamientos) this.getApplication();

        Intent datosEnviados = this.getIntent();
        final int pos = datosEnviados.getExtras().getInt("pos");
        final String km;
        final String min;

        if (pos>=0){
            km = String.valueOf(app.getEntrenamientos().get(pos).getDistance());
            min = String.valueOf(app.getEntrenamientos().get(pos).getMin());

        } else {
            km = "";
            min = "";
        }

        distancia.setText(String.valueOf(km));
        tiempo.setText(String.valueOf(min));

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddEntrenamiento.this.setResult(Activity.RESULT_CANCELED);
                AddEntrenamiento.this.finish();
            }
        });

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final float min = Float.valueOf(distancia.getText().toString());
                final float km = Float.valueOf(tiempo.getText().toString());

                if (pos>=0){
                    app.modifiedEntrenamiento(pos, km, min);
                } else {
                    app.addEntrenamiento(km,min);
                }

                AddEntrenamiento.this.setResult(Activity.RESULT_OK);
                AddEntrenamiento.this.finish();
            }
        });

        btGuardar.setEnabled(false);

        distancia.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                distancia.setText("");
                return false;
            }
        });

        distancia.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                btGuardar.setEnabled(distancia.getText().toString().trim().length()>0);
            }
        });

        tiempo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                tiempo.setText("");
                return false;
            }
        });

        tiempo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                btGuardar.setEnabled(tiempo.getText().toString().trim().length()>0);
            }
        });

    }
}

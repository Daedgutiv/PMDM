package com.example.myapplication.ui;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.core.LetraDNI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText edDni = (EditText) this.findViewById(R.id.edDni);
        edDni.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                MainActivity.this.calcula();
                return false;
            }
        });
    }

    protected void calcula(){
        EditText edDni = (EditText) this.findViewById(R.id.edDni);
        TextView lblLetra = (TextView) this.findViewById(R.id.lblResult);

        try {
            int dni = Integer.parseInt(edDni.getText().toString());
            char letra = LetraDNI.calcularLetra(dni);
            lblLetra.setText(Character.toString(letra));
        } catch(NumberFormatException fmt){
            lblLetra.setText(R.string.label_default_result);
        }
        return;
    }
}

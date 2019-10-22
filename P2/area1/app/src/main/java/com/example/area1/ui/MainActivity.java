package com.example.area1.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calcula(View X){
        TextView result = (TextView) this.findViewById(R.id.result);
        EditText largo = (EditText) this.findViewById(R.id.num1);
        EditText ancho = (EditText) this.findViewById(R.id.num2);

        try {
            int resultado = Integer.parseInt(largo.getText().toString())*Integer.parseInt(ancho.getText().toString());
            result.setText(String.valueOf(resultado));
        } catch(NumberFormatException fmt){
            result.setText(R.string.error);
        }

    }
}

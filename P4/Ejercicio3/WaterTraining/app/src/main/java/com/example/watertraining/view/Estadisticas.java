package com.example.watertraining.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.watertraining.R;
import com.example.watertraining.core.AplicattionEntrenamientos;
import com.example.watertraining.core.Entrenamiento;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

//Clase que muestra las estadisticas
public class Estadisticas extends AppCompatActivity {

    DecimalFormatSymbols dfs;
    DecimalFormat formato = new DecimalFormat("#0.00");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Button atras = (Button) this.findViewById(R.id.atras);

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Estadisticas.this.finish();
            }
        });

        final TextView tvKmTotales = (TextView) this.findViewById(R.id.kmtotales);
        final TextView minKm = (TextView) this.findViewById(R.id.minKm);

        final AplicattionEntrenamientos app = (AplicattionEntrenamientos) this.getApplication();

        float totalKm = 0;
        float totalMinKm = 0;

        List<Entrenamiento> entrenamientos = app.getEntrenamientos();
        for (int i = 0; i < entrenamientos.size(); i++) {
            totalKm = totalKm + entrenamientos.get(i).getDistance();
            totalMinKm = totalMinKm + entrenamientos.get(i).getMinKm();
        }
        totalMinKm = totalMinKm / entrenamientos.size();

        String aux = formato.format(totalMinKm);
        if (aux.equalsIgnoreCase("NaN")){
            aux ="0";
        }

        tvKmTotales.setText(formato.format(totalKm) + " km");
        minKm.setText(aux + " min/km");

    }


}

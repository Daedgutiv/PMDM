package com.example.navidades.view;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.navidades.R;
import com.example.navidades.core.Partido;

public class Anhadir extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.anhadir);

        final TextView tvVal = (TextView) this.findViewById(R.id.val);
        tvVal.setText("0");

        final EditText oponente = (EditText) this.findViewById(R.id.oponente);
        final EditText puntos = (EditText) this.findViewById(R.id.puntos);
        final EditText rebotes = (EditText) this.findViewById(R.id.rebotes);
        final EditText asistencias = (EditText) this.findViewById(R.id.asistencias);
        final EditText robos = (EditText) this.findViewById(R.id.robos);
        final EditText tapones = (EditText) this.findViewById(R.id.tapones);
        final EditText faltasRecibidas = (EditText) this.findViewById(R.id.faltasRecibidas);
        final EditText faltasRealizadas = (EditText) this.findViewById(R.id.faltasRealizadas);
        final EditText tirosFallados = (EditText) this.findViewById(R.id.tirosFallados);
        final EditText libresFallados = (EditText) this.findViewById(R.id.libresFallados);
        final EditText taponesRecibidos = (EditText) this.findViewById(R.id.taponesRecibidos);
        final EditText perdidas = (EditText) this.findViewById(R.id.perdidas);

        final Partido partido = new Partido();

        oponente.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!oponente.getText().toString().trim().isEmpty()) {
                    partido.setOponente(oponente.getText().toString());
                }
            }
        });
        puntos.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!puntos.getText().toString().trim().isEmpty()) {
                    partido.setPuntos(Integer.valueOf(puntos.getText().toString()));
                    String aux = String.valueOf(partido.valoracion());
                    tvVal.setText(aux);
                }

            }
        });
        rebotes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!rebotes.getText().toString().trim().isEmpty()) {
                    partido.setRebotes(Integer.valueOf(rebotes.getText().toString()));
                    String aux = String.valueOf(partido.valoracion());
                    tvVal.setText(aux);
                }

            }
        });
        asistencias.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!asistencias.getText().toString().trim().isEmpty()) {
                    partido.setAsistencias(Integer.valueOf(asistencias.getText().toString()));
                    String aux = String.valueOf(partido.valoracion());
                    tvVal.setText(aux);
                }

            }
        });
        robos.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!robos.getText().toString().trim().isEmpty()) {
                    partido.setRobos(Integer.valueOf(robos.getText().toString()));
                    String aux = String.valueOf(partido.valoracion());
                    tvVal.setText(aux);
                }

            }
        });
        tapones.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!tapones.getText().toString().trim().isEmpty()) {
                    partido.setTapones(Integer.valueOf(tapones.getText().toString()));
                    String aux = String.valueOf(partido.valoracion());
                    tvVal.setText(aux);
                }

            }
        });
        faltasRecibidas.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!faltasRecibidas.getText().toString().trim().isEmpty()) {
                    partido.setFaltasRecibidas(Integer.valueOf(faltasRecibidas.getText().toString()));
                    String aux = String.valueOf(partido.valoracion());
                    tvVal.setText(aux);
                }

            }
        });
        faltasRealizadas.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!faltasRealizadas.getText().toString().trim().isEmpty()) {
                    partido.setFaltasRealizadas(Integer.valueOf(faltasRealizadas.getText().toString()));
                    String aux = String.valueOf(partido.valoracion());
                    tvVal.setText(aux);
                }

            }
        });
        tirosFallados.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!tirosFallados.getText().toString().trim().isEmpty()) {
                    partido.setTirosFallados(Integer.valueOf(tirosFallados.getText().toString()));
                    String aux = String.valueOf(partido.valoracion());
                    tvVal.setText(aux);
                }
            }
        });
        libresFallados.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!libresFallados.getText().toString().trim().isEmpty()) {
                    partido.setLibresFallados(Integer.valueOf(libresFallados.getText().toString()));
                    String aux = String.valueOf(partido.valoracion());
                    tvVal.setText(aux);
                }
            }
        });
        taponesRecibidos.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!taponesRecibidos.getText().toString().trim().isEmpty()) {
                    partido.setTaponesRecibidos(Integer.valueOf(taponesRecibidos.getText().toString()));
                    String aux = String.valueOf(partido.valoracion());
                    tvVal.setText(aux);
                }
            }
        });
        perdidas.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!perdidas.getText().toString().trim().isEmpty()){
                    partido.setPerdidas(Integer.valueOf(perdidas.getText().toString()));
                    String aux = String.valueOf(partido.valoracion());
                    tvVal.setText(aux);
                }

            }
        });

        Button btsave = (Button) this.findViewById(R.id.btSave);

        btsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!oponente.getText().toString().trim().isEmpty() && !puntos.getText().toString().trim().isEmpty() && !asistencias.getText().toString().trim().isEmpty() && !robos.getText().toString().trim().isEmpty() && !rebotes.getText().toString().trim().isEmpty() && !tapones.getText().toString().trim().isEmpty() && !faltasRecibidas.getText().toString().trim().isEmpty() && !faltasRealizadas.getText().toString().trim().isEmpty() && !tirosFallados.getText().toString().trim().isEmpty() && !libresFallados.getText().toString().trim().isEmpty() && !taponesRecibidos.getText().toString().trim().isEmpty() && !perdidas.getText().toString().trim().isEmpty()) {
                    SharedPreferences prefs = Anhadir.this.getSharedPreferences("partidos", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = prefs.edit();

                    String aux = prefs.getString("tamanho", "0");
                    edit.putString("tamanho", String.valueOf(Integer.valueOf(aux) + 1));
                    edit.putString("oponente" + aux, partido.getOponente());
                    edit.putString("puntos" + aux, String.valueOf(partido.getPuntos()));
                    edit.putString("rebotes" + aux, String.valueOf(partido.getRebotes()));
                    edit.putString("asistencias" + aux, String.valueOf(partido.getAsistencias()));
                    edit.putString("robos" + aux, String.valueOf(partido.getRobos()));
                    edit.putString("tapones" + aux, String.valueOf(partido.getTapones()));
                    edit.putString("faltasRealizadas" + aux, String.valueOf(partido.getFaltasRealizadas()));
                    edit.putString("faltasRecibidas" + aux, String.valueOf(partido.getFaltasRecibidas()));
                    edit.putString("tirosFallados" + aux, String.valueOf(partido.getTirosFallados()));
                    edit.putString("libresFallados" + aux, String.valueOf(partido.getLibresFallados()));
                    edit.putString("taponesRecibidos" + aux, String.valueOf(partido.getTaponesRecibidos()));
                    edit.putString("perdidas" + aux, String.valueOf(partido.getPerdidas()));
                    edit.apply();
                    Anhadir.this.setResult(RESULT_OK);
                    Anhadir.this.finish();
                } else {
                    Anhadir.this.setResult(1);
                    Anhadir.this.finish();
                }
            }
        });

        Button btcancel = (Button) this.findViewById(R.id.btCancel);

        btcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Anhadir.this.setResult(RESULT_CANCELED);
                Anhadir.this.finish();
            }
        });
    }

}

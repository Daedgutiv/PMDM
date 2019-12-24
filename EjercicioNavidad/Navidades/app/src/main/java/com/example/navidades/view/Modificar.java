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

import java.util.ArrayList;

public class Modificar extends Activity {

    private ArrayList<Partido> partidos;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.anhadir);

        final TextView tvVal = (TextView) this.findViewById(R.id.val);
        final int pos = getIntent().getIntExtra("partido",0);
        Modificar.this.partidos = new ArrayList<Partido>();

        leer();

        final EditText oponente = (EditText) this.findViewById(R.id.oponente);
        oponente.setText(partidos.get(pos).getOponente());
        final EditText puntos = (EditText) this.findViewById(R.id.puntos);
        puntos.setText(String.valueOf(partidos.get(pos).getPuntos()));
        final EditText rebotes = (EditText) this.findViewById(R.id.rebotes);
        rebotes.setText(String.valueOf(partidos.get(pos).getRebotes()));
        final EditText asistencias = (EditText) this.findViewById(R.id.asistencias);
        asistencias.setText(String.valueOf(partidos.get(pos).getAsistencias()));
        final EditText robos = (EditText) this.findViewById(R.id.robos);
        robos.setText(String.valueOf(partidos.get(pos).getRobos()));
        final EditText tapones = (EditText) this.findViewById(R.id.tapones);
        tapones.setText(String.valueOf(partidos.get(pos).getTapones()));
        final EditText faltasRecibidas = (EditText) this.findViewById(R.id.faltasRecibidas);
        faltasRecibidas.setText(String.valueOf(partidos.get(pos).getFaltasRecibidas()));
        final EditText faltasRealizadas = (EditText) this.findViewById(R.id.faltasRealizadas);
        faltasRealizadas.setText(String.valueOf(partidos.get(pos).getFaltasRealizadas()));
        final EditText tirosFallados = (EditText) this.findViewById(R.id.tirosFallados);
        tirosFallados.setText(String.valueOf(partidos.get(pos).getTirosFallados()));
        final EditText libresFallados = (EditText) this.findViewById(R.id.libresFallados);
        libresFallados.setText(String.valueOf(partidos.get(pos).getLibresFallados()));
        final EditText taponesRecibidos = (EditText) this.findViewById(R.id.taponesRecibidos);
        taponesRecibidos.setText(String.valueOf(partidos.get(pos).getTaponesRecibidos()));
        final EditText perdidas = (EditText) this.findViewById(R.id.perdidas);
        perdidas.setText(String.valueOf(partidos.get(pos).getPerdidas()));

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
                if (!perdidas.getText().toString().trim().isEmpty()) {
                    partido.setPerdidas(Integer.valueOf(perdidas.getText().toString()));
                    String aux = String.valueOf(partido.valoracion());
                    tvVal.setText(aux);
                }
            }
        });

        tvVal.setText(String.valueOf(partidos.get(pos).valoracion()));

        Button btsave = (Button) this.findViewById(R.id.btSave);

        btsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!oponente.getText().toString().trim().isEmpty() && !puntos.getText().toString().trim().isEmpty() && !asistencias.getText().toString().trim().isEmpty() && !robos.getText().toString().trim().isEmpty() && !rebotes.getText().toString().trim().isEmpty() && !tapones.getText().toString().trim().isEmpty() && !faltasRecibidas.getText().toString().trim().isEmpty() && !faltasRealizadas.getText().toString().trim().isEmpty() && !tirosFallados.getText().toString().trim().isEmpty() && !libresFallados.getText().toString().trim().isEmpty() && !taponesRecibidos.getText().toString().trim().isEmpty() && !perdidas.getText().toString().trim().isEmpty()) {
                    SharedPreferences prefs = Modificar.this.getSharedPreferences("partidos", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = prefs.edit();

                    String aux = prefs.getString("tamanho", "0");
                    edit.putString("oponente" + pos, partido.getOponente());
                    edit.putString("puntos" + pos, String.valueOf(partido.getPuntos()));
                    edit.putString("rebotes" + pos, String.valueOf(partido.getRebotes()));
                    edit.putString("asistencias" + pos, String.valueOf(partido.getAsistencias()));
                    edit.putString("robos" + pos, String.valueOf(partido.getRobos()));
                    edit.putString("tapones" + pos, String.valueOf(partido.getTapones()));
                    edit.putString("faltasRealizadas" + pos, String.valueOf(partido.getFaltasRealizadas()));
                    edit.putString("faltasRecibidas" + pos, String.valueOf(partido.getFaltasRecibidas()));
                    edit.putString("tirosFallados" + pos, String.valueOf(partido.getTirosFallados()));
                    edit.putString("libresFallados" + pos, String.valueOf(partido.getLibresFallados()));
                    edit.putString("taponesRecibidos" + pos, String.valueOf(partido.getTaponesRecibidos()));
                    edit.putString("perdidas" + pos, String.valueOf(partido.getPerdidas()));
                    edit.apply();
                    Modificar.this.setResult(RESULT_OK);
                    Modificar.this.finish();
                } else {
                    Modificar.this.setResult(1);
                    Modificar.this.finish();
                }
            }
        });

        Button btcancel = (Button) this.findViewById(R.id.btCancel);

        btcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modificar.this.setResult(RESULT_CANCELED);
                Modificar.this.finish();
            }
        });
    }

    public void leer(){
        SharedPreferences prefs = getSharedPreferences("partidos", Context.MODE_PRIVATE);
        int tamanho = Integer.valueOf(prefs.getString("tamanho","0"));

        Modificar.this.partidos.clear();

        for(int i=0;i<tamanho;i++){
            String oponente = prefs.getString("oponente"+i,"");
            int puntos = Integer.valueOf(prefs.getString("puntos"+i,"0"));
            int rebotes = Integer.valueOf(prefs.getString("rebotes"+i,"0"));
            int asistencias = Integer.valueOf(prefs.getString("asistencias"+i,"0"));
            int robos = Integer.valueOf(prefs.getString("robos"+i,"0"));
            int tapones = Integer.valueOf(prefs.getString("tapones"+i,"0"));
            int faltasRecibidas = Integer.valueOf(prefs.getString("faltasRecibidas"+i,"0"));
            int faltasRealizadas = Integer.valueOf(prefs.getString("faltasRealizadas"+i,"0"));
            int tirosFallados = Integer.valueOf(prefs.getString("tirosFallados"+i,"0"));
            int libresFallados = Integer.valueOf(prefs.getString("libresFallados"+i,"0"));
            int taponesRecibidos = Integer.valueOf(prefs.getString("taponesRecibidos"+i,"0"));
            int perdidas = Integer.valueOf(prefs.getString("perdidas"+i,"0"));

            Partido partido = new Partido(oponente,puntos,rebotes,asistencias,robos,tapones,faltasRecibidas,faltasRealizadas,tirosFallados,libresFallados,taponesRecibidos,perdidas);

            Modificar.this.partidos.add(partido);
        }
    }
}

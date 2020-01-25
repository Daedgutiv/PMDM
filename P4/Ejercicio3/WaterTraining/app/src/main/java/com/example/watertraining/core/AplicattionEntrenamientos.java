package com.example.watertraining.core;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class AplicattionEntrenamientos extends Application {

    private List<Entrenamiento> entrenamientos;
    private int pos;

    public void onCreate(){
        super.onCreate();
        this.entrenamientos = new ArrayList<>();
        leer();
    }

    private void leer(){
        SharedPreferences prefs = getSharedPreferences("WaterTraining", Context.MODE_PRIVATE);

        int tamano = Integer.valueOf(prefs.getString("tamano" , "0"));

        for (int i=0; i<tamano;i++){
            String distancia = prefs.getString("distancia" +i,"0");
            String tiempo = prefs.getString("tiempo" +i,"0");

            Entrenamiento entrenamiento = new Entrenamiento();

            entrenamiento.setDistance(Float.valueOf(distancia));
            entrenamiento.setMin(Float.valueOf(tiempo));

            entrenamientos.add(entrenamiento);
        }
    }

    public List<Entrenamiento> getEntrenamientos(){return this.entrenamientos;}

    public void addEntrenamiento (float distancia, float tiempo){

        Entrenamiento entrenamiento = new Entrenamiento();

        entrenamiento.setMin(tiempo);
        entrenamiento.setDistance(distancia);

        this.entrenamientos.add(entrenamiento);

        escribir();

    }

    private void escribir(){

        SharedPreferences prefs = getSharedPreferences("WaterTraining", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("tamano", String.valueOf(this.entrenamientos.size()));

        for (int i = 0; i < this.entrenamientos.size();i++){
            editor.putString("distancia"+i, String.valueOf(this.entrenamientos.get(i).getDistance()));
            editor.putString("tiempo"+i, String.valueOf(this.entrenamientos.get(i).getMin()));
        }

        editor.apply();
    }

    public void modifiedEntrenamiento(int pos, float distancia, float tiempo){

        Entrenamiento entrenamiento = new Entrenamiento();

        entrenamiento.setDistance(distancia);
        entrenamiento.setMin(tiempo);

        this.entrenamientos.add(entrenamiento);
    }

    public void eliminarEntrenamiento(int pos){
        this.entrenamientos.remove(pos);

        escribir();
    }

}

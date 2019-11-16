package com.example.watertraining.core;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Entrenamiento {
    private float min;
    private float distance;

    public Entrenamiento (){
    }

    public float getMinKm (){
        return this.min/this.distance;
    }

    public float getSegKm (){
        return (this.min*60)/this.distance;
    }

    public float getKmHour (){
        return (this.min/60)/this.distance;
    }

    @Override
    public String toString(){
        DecimalFormat formato = new DecimalFormat("#0.00");
        String aux = "Entrenamiento: Se han recorrido " + formato.format(this.distance) + " Km en " + formato.format(this.min) + " minutos. Su velocidad media fue de " + formato.format(this.getKmHour()) + " Km/h";
        return aux;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}

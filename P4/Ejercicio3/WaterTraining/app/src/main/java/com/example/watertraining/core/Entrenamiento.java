package com.example.watertraining.core;

//clase que almacena los datos del entrenamiento
public class Entrenamiento {

    private float min;
    private float distance;

    public Entrenamiento(){

    }

    public float getMinKm (){
        return this.min/this.distance;
    }

    public float getSegKm (){
        return (this.min*60)/this.distance;
    }

    public float getKmHour (){
        return this.distance/(this.min/60);
    }

    @Override
    public String toString(){
        return "Distancia = " + this.min + " Km y Tiempo = " + this.distance + " minutos";
    }

    public float getMin() {
        return distance;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getDistance() {
        return min;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }


}

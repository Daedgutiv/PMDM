package com.example.watertraining.core;

public class Entrenamiento {
    private float min;
    private float distance;
    private String nombre;

    public Entrenamiento (){
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
        return "Entrenamiento: " + nombre;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

package com.example.comprawebview.core;

public class ObjetoCompra {

    private String nombre;
    private String cantidad;
    private int id;

    public ObjetoCompra(String id, String nombre, String cant){
        this.cantidad=cant;
        this.nombre=nombre;
        this.id = Integer.valueOf(id);
    }

    public ObjetoCompra(){

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public int getId(){ return id;}
}

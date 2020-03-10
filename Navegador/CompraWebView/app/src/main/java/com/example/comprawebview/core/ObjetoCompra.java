package com.example.comprawebview.core;

public class ObjetoCompra {

    private String nombre;
    private String cantidad;

    public ObjetoCompra(String nombre, String cant){
        this.cantidad=cant;
        this.nombre=nombre;
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
}

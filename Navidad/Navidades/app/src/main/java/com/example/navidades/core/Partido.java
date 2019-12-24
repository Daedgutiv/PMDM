package com.example.navidades.core;

public class Partido {

    private int puntos;
    private int rebotes;
    private int asistencias;
    private int robos;
    private int tapones;
    private int faltasRecibidas;
    private int faltasRealizadas;
    private int tirosFallados;
    private int libresFallados;
    private int taponesRecibidos;
    private int perdidas;
    private String oponente;

    public Partido(){
        this.puntos=0;
        this.rebotes=0;
        this.asistencias=0;
        this.robos=0;
        this.tapones=0;
        this.faltasRealizadas=0;
        this.faltasRecibidas=0;
        this.tirosFallados=0;
        this.libresFallados=0;
        this.taponesRecibidos=0;
        this.perdidas=0;
    }

    public Partido( String oponente, int puntos, int rebotes, int asistencias, int robos, int tapones, int faltasRecibidas, int faltasRealizadas, int tirosFallados, int libresFallados, int taponesRecibidos, int perdidas){
        this.puntos=puntos;
        this.rebotes=rebotes;
        this.asistencias=asistencias;
        this.robos=robos;
        this.tapones=tapones;
        this.faltasRealizadas=faltasRealizadas;
        this.faltasRecibidas=faltasRecibidas;
        this.tirosFallados=tirosFallados;
        this.libresFallados=libresFallados;
        this.taponesRecibidos=taponesRecibidos;
        this.perdidas=perdidas;
        this.oponente=oponente;
    }

    public int valoracion(){
        return ((this.puntos+this.rebotes+this.asistencias+this.robos+this.tapones+this.faltasRecibidas) - (this.tirosFallados + this.libresFallados + this.taponesRecibidos +faltasRealizadas));
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getRebotes() {
        return rebotes;
    }

    public void setRebotes(int rebotes) {
        this.rebotes = rebotes;
    }

    public int getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(int asistencias) {
        this.asistencias = asistencias;
    }

    public int getRobos() {
        return robos;
    }

    public void setRobos(int robos) {
        this.robos = robos;
    }

    public int getTapones() {
        return tapones;
    }

    public void setTapones(int tapones) {
        this.tapones = tapones;
    }

    public int getFaltasRecibidas() {
        return faltasRecibidas;
    }

    public void setFaltasRecibidas(int faltasRecibidas) {
        this.faltasRecibidas = faltasRecibidas;
    }

    public int getFaltasRealizadas() {
        return faltasRealizadas;
    }

    public void setFaltasRealizadas(int faltasRealizadas) {
        this.faltasRealizadas = faltasRealizadas;
    }

    public int getTirosFallados() {
        return tirosFallados;
    }

    public void setTirosFallados(int tirosFallados) {
        this.tirosFallados = tirosFallados;
    }

    public int getLibresFallados() {
        return libresFallados;
    }

    public void setLibresFallados(int libresFallados) {
        this.libresFallados = libresFallados;
    }

    public int getTaponesRecibidos() {
        return taponesRecibidos;
    }

    public void setTaponesRecibidos(int taponesRecibidos) {
        this.taponesRecibidos = taponesRecibidos;
    }

    public int getPerdidas() {
        return perdidas;
    }

    public void setPerdidas(int perdidas) {
        this.perdidas = perdidas;
    }

    public String getOponente() {
        return oponente;
    }

    public void setOponente(String oponente) {
        this.oponente = oponente;
    }

    @Override
    public String toString() {
        return "Partido contra " + this.oponente + " Valoración: " + this.valoracion();
    }
}

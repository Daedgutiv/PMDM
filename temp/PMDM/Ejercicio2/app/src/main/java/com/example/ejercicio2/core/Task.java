package com.example.ejercicio2.core;

public class Task {

    String task;
    int ano;
    int mes;
    int dia;

    public Task(){

    }

    public String getTask() {return task;}

    public void setTask(String task) {this.task = task;}

    public int getAnho() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String toString(){
        return this.task + "(" + this.dia + "/" + this.mes + "/" + this.ano + ")";
    }
}

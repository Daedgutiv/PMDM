package com.example.todo.core;

public class Tasks {

    String task;
    int anho;
    int mes;
    int dia;

    public int getAnho() {
        return anho;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public void setAnho(int anho) {
        this.anho = anho;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}

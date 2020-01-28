package com.example.todo.core;

import java.util.Calendar;

//clase que contiene los datos de las tareas
public class Task {
   private String task;
   private int ano;
   private int mes;
   private int dia;

    public Task(){

    }

    public String getTask() {return this.task;}

    public void setTask(String task) {this.task = task;}

    public int getAno() {
        return this.ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getMes() {
        return this.mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getDia() {
        return this.dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    //metodo que devuelve si una tarea esta caducada
    public boolean isFinish(){

        Calendar c = Calendar.getInstance();
        int ano2 = c.get(Calendar.YEAR);
        int mes2 = c.get(Calendar.MONTH);
        int dia2 = c.get(Calendar.DATE);

        if (this.ano< ano2){
            return true;
        } else if (this.mes<mes2){
            return true;
        } else if (this.dia<=dia2){
            return true;
        }

        return false;
    }

    public String toString(){
        return this.task + "(" + this.dia + "/" + this.mes + "/" + this.ano + ")";
    }
}

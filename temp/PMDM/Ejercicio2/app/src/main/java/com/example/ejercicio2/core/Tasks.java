package com.example.ejercicio2.core;

import android.app.Application;

import java.util.ArrayList;

public class Tasks extends Application {

    private ArrayList<Task> tasks;
    private int pos;

    public void OnCreate() {
        super.onCreate();
        this.tasks = new ArrayList<Task>();
    }

    public ArrayList<Task> getTasks(){
        return tasks;
    }

    public void addTask (String text, int ano, int mes, int dia){

        Task task = new Task();
        task.setTask(text);
        task.setAno(ano);
        task.setDia(dia);
        task.setMes(mes);

        this.tasks.add(task);
    }

    public void modifiedTask (int pos, String text, int ano, int mes, int dia){

        Task task = new Task();
        task.setTask(text);
        task.setAno(ano);
        task.setDia(dia);
        task.setMes(mes);

        this.tasks.set(pos,task);
    }

}

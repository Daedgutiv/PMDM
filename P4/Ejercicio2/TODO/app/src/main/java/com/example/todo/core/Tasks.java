package com.example.todo.core;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class Tasks extends Application {

    private List<Task> tasks;
    private int pos;

    public void onCreate() {
        super.onCreate();
        this.tasks = new ArrayList<>();
        leer();
    }

    private void leer(){
        SharedPreferences prefs = getSharedPreferences("TODO", Context.MODE_PRIVATE);

        int tamanho = Integer.valueOf(prefs.getString("tamanho","0"));

        for (int i = 0; i<tamanho;i++){
            String tarea = prefs.getString("task"+i, "");
            int ano = Integer.valueOf(prefs.getString("ano"+i,"0"));
            int mes = Integer.valueOf(prefs.getString("mes"+i, "0"));
            int dia = Integer.valueOf(prefs.getString("dia"+i,"0"));

            Task task = new Task();
            task.setTask(tarea);
            task.setAno(ano);
            task.setDia(dia);
            task.setMes(mes);

            tasks.add(task);
        }
    }

    public List<Task> getTasks(){
        return tasks;
    }

    public void addTask (String text, int ano, int mes, int dia){

        Task task = new Task();
        task.setTask(text);
        task.setAno(ano);
        task.setDia(dia);
        task.setMes(mes);

        this.tasks.add(task);

       escribir();

    }

    private void escribir(){

        SharedPreferences prefs = getSharedPreferences("TODO", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("tamanho", String.valueOf(this.tasks.size()));

        for (int i = 0; i<this.tasks.size();i++){
            editor.putString("task" + i, this.tasks.get(i).getTask());
            editor.putString("ano" + i, String.valueOf(this.tasks.get(i).getAno()));
            editor.putString("mes" + i, String.valueOf(this.tasks.get(i).getMes()));
            editor.putString("dia" + i, String.valueOf(this.tasks.get(i).getDia()));
        }

        editor.apply();

    }

    public void modifiedTask (int pos, String text, int ano, int mes, int dia){

        Task task = new Task();
        task.setTask(text);
        task.setAno(ano);
        task.setDia(dia);
        task.setMes(mes);

        this.tasks.set(pos,task);
    }

    public void eliminarTask(int pos){
        this.tasks.remove(pos);

        escribir();

    }
}

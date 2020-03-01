package com.example.ejercicio3.core;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class ListaAyunta extends Application {

    private List<Ayuntamiento> lista;
    private int pos;

    public void onCreate(){
        super.onCreate();
        this.lista = new ArrayList<>();

       /* Ayuntamiento ayuntamiento = new Ayuntamiento("asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd");

        lista.add(ayuntamiento);*/
    }

    public List<Ayuntamiento> getLista(){
        return this.lista;
    }

    public void addLista (List<Ayuntamiento> lista2){
        this.lista.clear();
        for (int i=0;i<lista2.size();i++){
            lista.add(lista2.get(i));
        }
    }


}

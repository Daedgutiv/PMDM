package com.resuadam.listacompra2.Core;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class Aplication extends Application {

    private List<Item> items;
    private  int pos;

    public void onCreate() {
        super.onCreate();
         this.items = new ArrayList<>();
    }

    public List<Item> getItems(){
        return this.items;
    }

    public void addItem(String nombre, int num){
        Item item = new Item(nombre);
        item.setNum(num);
        this.items.add(item);
    }

    public void modifyItem(int pos, String nombre, int num){
        Item item = new Item(nombre);
        item.setNum(num);
        this.items.set(pos,item);
    }



}

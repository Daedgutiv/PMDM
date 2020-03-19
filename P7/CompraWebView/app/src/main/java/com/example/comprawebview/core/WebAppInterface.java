package com.example.comprawebview.core;

import android.content.Context;
import android.webkit.JavascriptInterface;

import com.example.comprawebview.BBDD.FuncionesBBDD;

public class WebAppInterface {

    Context context;
    FuncionesBBDD funciones;

    public WebAppInterface(Context c){
        context=c;
        funciones = new FuncionesBBDD(context);
    }

    @JavascriptInterface
    public void anhadir(String id, String nombre, String cantidad){

        System.out.println(id);
        System.out.println(nombre);
        System.out.println(cantidad);

        ObjetoCompra objeto = new ObjetoCompra(id, nombre, cantidad);

        funciones.insertar(objeto);
    }

    @JavascriptInterface
    public void eliminar(int a){

        System.out.println(a);

        funciones.eliminar(a);

    }

    @JavascriptInterface
    public void modificar(String i, String nombre, String cantidad){

        ObjetoCompra objeto = new ObjetoCompra(i, nombre, cantidad);

        funciones.modificar(objeto);

    }

    @JavascriptInterface
    public String getNombres(){

        String aux = funciones.recuperarNombres();

        System.out.println(aux);

        return aux;
    }

    @JavascriptInterface
    public String getCantidades(){

        String aux = funciones.recuperarCantidades();

        return aux;
    }

    @JavascriptInterface
    public String getIds(){

        String aux = funciones.recuperarIds();

        return aux;
    }


}

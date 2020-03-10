package com.example.comprawebview.core;

import android.content.Context;
import android.webkit.JavascriptInterface;

public class WebAppInterface {

    Context context;

    public WebAppInterface(Context c){
        context=c;
    }

    @JavascriptInterface
    public void guardar(String nombre, String cantidad){

    }



}

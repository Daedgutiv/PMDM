package com.example.myapplication.core;

public class LetraDNI {
    public static final String NIF_STRING_ASSOCIATING = "TRWAGMYFPDXBNJZSQVHLCKE";

    public static char calcularLetra (int dni){
        return NIF_STRING_ASSOCIATING.charAt(dni % 23);
    }
}

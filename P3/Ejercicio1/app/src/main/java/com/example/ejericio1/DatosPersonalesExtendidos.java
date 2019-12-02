package com.example.ejericio1;

public class DatosPersonalesExtendidos {

    public DatosPersonalesExtendidos(String n, String a, String e, String apellidos, String edad) {
        this.setNombre( n );
        this.setDireccion( a );
        this.setEmail( e );
        this.setApellidos(apellidos);
        this.setEdad(edad);
    }

    final public String getNombre() {
        return nombre;
    }

    final public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    final public String getEmail() {
        return email;
    }

    final public void setEmail(String email) {
        this.email = email;
    }

    final public String getDireccion() {
        return direccion;
    }

    final public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    private String nombre;
    private String apellidos;
    private String edad;
    private String email;
    private String direccion;

}

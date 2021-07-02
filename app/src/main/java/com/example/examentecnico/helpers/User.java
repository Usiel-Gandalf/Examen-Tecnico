package com.example.examentecnico.helpers;

public class User {
    public String estado;
    public String genero;
    public String pais;
    public String usuario;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String estado, String genero, String pais, String usuario) {
        this.estado = estado;
        this.genero = genero;
        this.pais = pais;
        this.usuario = usuario;
    }


}

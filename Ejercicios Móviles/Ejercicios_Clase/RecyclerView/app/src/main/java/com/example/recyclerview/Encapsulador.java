package com.example.recyclerview;

public class Encapsulador {

    private int imagen;
    private String titulo;
    private String descripcion;


    public Encapsulador(int imagen, String titulo, String descripcion) {

        this.imagen = imagen;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getTitulo() {

        return titulo;
    }

    public void setTitulo(String titulo) {

        this.titulo = titulo;
    }

    public String getDescripcion() {

        return descripcion;
    }

    public void setDescripcion(String descripcion) {

        this.descripcion = descripcion;
    }
}

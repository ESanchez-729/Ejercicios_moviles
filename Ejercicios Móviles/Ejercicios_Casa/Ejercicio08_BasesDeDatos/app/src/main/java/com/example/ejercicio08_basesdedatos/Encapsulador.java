package com.example.ejercicio08_basesdedatos;

import java.io.Serializable;

/**
 * @author Edgar Sánchez Amores
 * Clase encapsulador que nos permite ordenar los datos de cada objeto en una sola fila
 */

public class Encapsulador implements Serializable {

    private String grupo;
    private String disco;


    public Encapsulador(String grupo, String disco) {

        this.grupo = grupo;
        this.disco = disco;

    }

    public String getGrupo() {

        return grupo;
    }

    public void setGrupo(String grupo) {

        this.grupo = grupo;
    }

    public String getDisco() {

        return disco;
    }

    public void setDisco(String disco) {

        this.disco = disco;
    }
}

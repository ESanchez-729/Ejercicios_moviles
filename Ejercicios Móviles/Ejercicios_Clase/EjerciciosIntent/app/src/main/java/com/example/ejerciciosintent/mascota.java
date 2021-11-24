package com.example.ejerciciosintent;

import java.io.Serializable;

public class mascota implements Serializable {

    private String animal;
    private Integer edad;
    private String raza;


    public mascota(String Animal, Integer Edad, String Raza) {

        this.animal = Animal;
        this.edad = Edad;
        this.raza = Raza;

    }

    public String getAnimal () {

        return this.animal;
    }

    public Integer getEdad () {

        return this.edad;
    }

    public String getRaza () {

        return this.raza;
    }

    public String toString () {

        return "Animal: " + this.animal + " Edad: " + this.edad + " Raza: " + this.raza;
    }
}

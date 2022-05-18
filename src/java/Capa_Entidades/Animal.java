/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_Entidades;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Steven
 */
public class Animal {
    
    //attributes
    private int id_animal;
    private int id_cliente;
    private String alias;
    private String especie;
    private String raza;
    private String color;
    private Date fechaNacimiento;
    private boolean existe;
    
    //constructors
    public Animal(int id_animal, int id_cliente, String alias, String especie, String raza, String color, Date fechaNacimiento) {
        this.id_animal = id_animal;
        this.id_cliente = id_cliente;
        this.alias = alias;
        this.especie = especie;
        this.raza = raza;
        this.color = color;
        this.fechaNacimiento = fechaNacimiento;
        this.existe = true;
    }

    public Animal(){
        id_animal = 0;
        id_cliente = 0;
        alias = "";
        especie = "";
        raza = "";
        color = "";
        fechaNacimiento = Date.valueOf(LocalDate.MIN);
        existe = false;
    }
    
    
    //getters and setters
    public int getId_animal() {
        return id_animal;
    }

    public void setId_animal(int id_animal) {
        this.id_animal = id_animal;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
    
}

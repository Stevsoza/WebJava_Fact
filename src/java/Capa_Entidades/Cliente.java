/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_Entidades;

import java.sql.Date;

/**
 *
 * @author Steven
 */
public class Cliente extends Persona {
    
    //Attributes
    private int id_cliente;
    
    //constructors
    public Cliente(int id_cliente, String nombre, String apellido1, String apellido2, Date fechaNacimiento, String telefono, String email) {
        super(nombre,apellido1,apellido2,fechaNacimiento,telefono,email);
        this.id_cliente = id_cliente;
    }
    
    public Cliente(){
        super();
        id_cliente = 0;
    }
    
    //getters and setters
    public int getId_cliente() {    
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
}

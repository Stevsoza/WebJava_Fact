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
public class Vendedor extends Persona{
    
    private int id_vendedor;
    private String contrasena;
    private String usuario;
    
    public Vendedor(int id_vendedor, String nombre, String apellido1, String apellido2, Date fechaNacimiento, String telefono, String email, String contrasena, String usuario) {
        super(nombre,apellido1,apellido2,fechaNacimiento,telefono,email);
        this.id_vendedor = id_vendedor;
        this.contrasena = contrasena;
        this.usuario = usuario;
    }
    
    public Vendedor(){
        super();
        id_vendedor = 0;
        contrasena = "";
        usuario = "";
    }
    
    public int getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(int id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_Entidades;

/**
 *
 * @author Steven
 */
public class Producto {
    
     //attributes
    private int id_producto;
    private String descripcion;
    private String categoria;
    private double precio;
    private double descuento;
    private int existencia;
    private boolean existe;
    
    //contructors
    public Producto(int id_producto, String descripcion, String categoria, double precio, double descuento, int existencia) {
        this.id_producto = id_producto;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precio = precio;
        this.descuento = descuento;
        this.existencia = existencia;
        this.existe = true;
    }
    
    public Producto(){
        id_producto= 0;
        descripcion= "";
        categoria = "";
        precio = 0;
        descuento = 0;
        existencia = 0;
        existe = false;
    }
    //getters & setters
    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }
    
    
    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public boolean isExiste() {
        return existe;
    }
    
    public void setExiste(boolean existe) {
        this.existe = existe;
    }
    
}

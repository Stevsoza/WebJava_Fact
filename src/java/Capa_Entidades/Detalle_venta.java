/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_Entidades;

/**
 *
 * @author Steven
 */
public class Detalle_venta {
    
     //attributes
    private int id_venta;
    private int id_producto;
    private String descripcion;
    private int id_veterinario;
    private int id_animal;
    private int cantidad;
    private double precio;
    private double descuento;
    private double total;
    private boolean existe;
    
    //constructors

    public Detalle_venta(int id_venta, int id_producto, String descripcion, int id_veterinario, int id_animal, int cantidad, double precio, double descuento, double total) {
        this.id_venta = id_venta;
        this.id_producto = id_producto;
        this.descripcion = descripcion;
        this.id_veterinario = id_veterinario;
        this.id_animal = id_animal;
        this.cantidad = cantidad;
        this.precio = precio;
        this.descuento = descuento;
        this.total = total;
        this.existe = true;
    }
    
    public Detalle_venta(){
        id_venta = 0;
        id_producto = 0;
        descripcion = "";
        id_veterinario = 0;
        id_animal = 0;
        cantidad = 0;
        precio = 0;
        descuento = 0;
        total = 0;
        existe = false;
    }

    
    //getters and setters

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

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

    public int getId_veterinario() {
        return id_veterinario;
    }

    public void setId_veterinario(int id_veterinario) {
        this.id_veterinario = id_veterinario;
    }

    public int getId_animal() {
        return id_animal;
    }

    public void setId_animal(int id_animal) {
        this.id_animal = id_animal;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
}

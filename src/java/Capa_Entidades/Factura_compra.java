/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_Entidades;

import java.sql.Timestamp;

/**
 *
 * @author Steven
 */
public class Factura_compra extends Factura {
    
    private int id_compra;
    private int id_proveedor;
    
    public Factura_compra(int id_compra, int id_proveedor, Timestamp fecha, int id_vendedor, double importe, double descuento, String estado ,String tipo) {
        super(fecha, id_vendedor, importe, descuento, estado, tipo);
        this.id_compra = id_compra;
        this.id_proveedor = id_proveedor;
    }

    public Factura_compra() {
        super();
        id_compra = 0;
        id_proveedor = 0;
    }
    
    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }
    
}

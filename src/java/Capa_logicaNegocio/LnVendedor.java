/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_logicaNegocio;

import Capa_AccesoDatos.AdVendedor;
import Capa_Entidades.Vendedor;

/**
 *
 * @author Steven
 */
public class LnVendedor {
    
    private String _mensaje;
    
    public String getMsg(){
    
        return _mensaje;
    }
    
    public LnVendedor(){
        _mensaje = "";
    }
    
    public int insertarVendedor(Vendedor vendedor)throws Exception{
    
        int resultado = -1;
        AdVendedor dv = new AdVendedor();
        try{
            resultado = dv.insertarVendedor(vendedor);
            _mensaje = dv.getMsg();
        }catch(Exception ex){
            throw ex;
        }      
        return resultado;
    }
    
    public Vendedor obtenerRegistro(String condicion)throws Exception {
    
        Vendedor vendedor = null;
        AdVendedor adVen = new AdVendedor();
        try{
            vendedor = adVen.obtenerRegistro(condicion);
            _mensaje = adVen.getMsg();
        }catch(Exception ex){
            throw ex;
        }
        return vendedor;
    }
    
}

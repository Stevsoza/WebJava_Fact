/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_logicaNegocio;

import Capa_Entidades.Detalle_venta;
import Capa_AccesoDatos.*;
import java.util.List;

/**
 *
 * @author Steven
 */
public class LnDetalleVenta {
    
    private String _Mensaje;
    
    public String getMsg(){
        return _Mensaje;
    }
    
    public LnDetalleVenta(){
        _Mensaje = "";
    }
    
    public List<Detalle_venta> ListarRegistros(String condicion)throws Exception{
        List<Detalle_venta> Datos;
        
        try{
            AdDetalle_venta adVenta = new AdDetalle_venta();
            Datos = adVenta.ListarRegistros(condicion);
        }catch(Exception ex){
            Datos = null;
            throw ex;
        }
        return Datos;
    }
    
    public int insertarDetalleVenta(Detalle_venta detalle)throws Exception{
        int resultado= -1;
        AdDetalle_venta acsd = new AdDetalle_venta();
        try{
            resultado = acsd.insertarDetalleVenta(detalle);
            _Mensaje = acsd.getMensaje();
        }catch(Exception ex){
            throw ex;
        }
        return resultado;
    }
    
    public Detalle_venta ObtenerRegistro(String condicion)throws Exception{
    
        Detalle_venta dVenta = null;
        AdDetalle_venta dtalVenta = new AdDetalle_venta();
        try{
            dVenta = dtalVenta.ObtenerRegistro(condicion);
        }catch(Exception ex)
        {
            throw ex;
        }
        
        return dVenta ;
    }
    
    public int eliminarDetalle(Detalle_venta detalle)throws Exception{
    
        int resultado = -1;
        AdDetalle_venta adVen = new AdDetalle_venta();
        try{
            resultado =  adVen.eliminarDetalle(detalle);
            _Mensaje = adVen.getMensaje();
        }catch(Exception ex){
            throw ex;
        }
        return resultado;
    }
}

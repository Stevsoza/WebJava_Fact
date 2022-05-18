/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_logicaNegocio;

import Capa_AccesoDatos.AdFacturaVenta;
import Capa_Entidades.Factura_venta;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Steven
 */
public class LnFacturaVenta {
    
    private String _mensaje;
    
    public String getMsg(){
        return _mensaje;
    }
    
    public LnFacturaVenta(){
        _mensaje = "";
    }
    
    public int Insertar(Factura_venta venta) throws Exception{
    
        int id = -1;
        AdFacturaVenta adventa;
        
        try{
            adventa = new AdFacturaVenta();
            id = adventa.Insertar(venta);
            _mensaje = adventa.getMsg();
        }
        catch(Exception ex){
            throw ex;
        }
        return id;
    }
    
    public Factura_venta ObtenerRegistro(String condicion)throws Exception{
    
        Factura_venta fVenta = null;
        AdFacturaVenta adFacturaVenta = new AdFacturaVenta();
        try{
            fVenta = adFacturaVenta.ObtenerRegistro(condicion);
            _mensaje = adFacturaVenta.getMsg();
        }catch(Exception ex){throw ex;}
        return fVenta;
    }
    
    public int Cancelar(Factura_venta fventa)throws Exception {
    
        int resultado  = -1;
        AdFacturaVenta adFacturaVenta = new AdFacturaVenta();
        try{
            resultado = adFacturaVenta.Cancelar(fventa);
            _mensaje = adFacturaVenta.getMsg();
        }catch(Exception ex){
            throw ex;
        }
        
        return resultado;
    }
    
    public List<Factura_venta> ListarRegistros(String condicion)throws Exception {
    
        List<Factura_venta> lista = null;
        AdFacturaVenta adFacturaVenta = new AdFacturaVenta();
        try{
        
             lista = adFacturaVenta.ListarRegistros(condicion);
             
        }catch(Exception ex){
        
            throw ex;
        }
        
        return lista;
    }
    
    public int Eliminar(Factura_venta fVenta) throws Exception{
        int resultado = -1;
        AdFacturaVenta adFacturaVenta;
        try{
            adFacturaVenta = new AdFacturaVenta();
            resultado = adFacturaVenta.Eliminar(fVenta);
            _mensaje= adFacturaVenta.getMsg();
        }catch(Exception ex){throw ex;}
        return resultado;
    }
    
}

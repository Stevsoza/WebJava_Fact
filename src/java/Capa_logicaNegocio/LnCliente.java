/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_logicaNegocio;

import Capa_AccesoDatos.AdCliente;
import Capa_Entidades.Cliente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Steven
 */
public class LnCliente {
    
    
    private String _mensaje;
    
    public String getMensaje(){
    
        return _mensaje;
    }
    
    public LnCliente(){
        _mensaje = "";
    }
    
    
    public List<Cliente> ListarRegistros(String condicion) throws Exception{
        
        List<Cliente> resultado = new ArrayList();
        
        AdCliente adCliente;
        try{
        
            adCliente = new AdCliente();
            resultado = adCliente.ListarRegistros(condicion);
        }
        catch(Exception ex)
        {
            throw ex;
        }
        return resultado;
    }
    
    public Cliente ObtenerRegistro(String condicion) throws Exception{
    
        Cliente resultado;
        AdCliente adCliente;
        
        try{
            adCliente = new AdCliente();
            resultado = adCliente.ObtenerRegistro(condicion);
            if(resultado.isExiste()){
                _mensaje= "Cliente recuperado exitosamente";
            }
            else{
                _mensaje= "El cliente no existe";
            }
            
        }catch(Exception ex)
        {
            throw ex;
        }
        return resultado;
    }
    
    public int Insertar(Cliente cliente) throws Exception{
    
        int id = -1;
        AdCliente adcliente;
        
        try{
            adcliente = new AdCliente();
            id = adcliente.Insertar(cliente);
            _mensaje = adcliente.getMensaje();
        }
        catch(Exception ex){
            throw ex;
        }
        return id;
    }
    
    public int Modificar(Cliente cliente) throws Exception{
        int resultado = -1;
        AdCliente adCliente;
        try{
            adCliente = new AdCliente();
            resultado = adCliente.Modificar(cliente);
            _mensaje= adCliente.getMensaje();
        }catch(Exception ex){
            throw ex;
        }
        return resultado;
    }
    
    public int Eliminar(Cliente cliente) throws Exception{
        int resultado = -1;
        AdCliente adCliente;
        try{
            adCliente = new AdCliente();
            resultado = adCliente.Eliminar(cliente);
            _mensaje= adCliente.getMensaje();
        }catch(Exception ex){throw ex;}
        return resultado;
    }
}

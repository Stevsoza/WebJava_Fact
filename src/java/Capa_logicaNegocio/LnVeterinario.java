/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_logicaNegocio;

import Capa_AccesoDatos.AdVeterinario;
import Capa_Entidades.Veterinario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Steven
 */
public class LnVeterinario {
    
    private String _mensaje;
    
    public String getMsg(){
        return _mensaje;
    }
    
    public LnVeterinario(){
        _mensaje = "";
    }
    
    public Veterinario ObtenerRegistro(String condicion)throws Exception{
    
        Veterinario veterinario = null;
        AdVeterinario adVeterinario = new AdVeterinario();
        try{
            veterinario = adVeterinario.ObtenerRegistro(condicion);
            _mensaje = adVeterinario.getMensaje();
        }catch(Exception ex){throw ex;}
        return veterinario;
    }
    
    public List<Veterinario> ListarRegistros(String condicion) throws Exception{
        
        List<Veterinario> resultado = new ArrayList();
        
        AdVeterinario adVeterinario;
        try{
        
            adVeterinario = new AdVeterinario();
            resultado = adVeterinario.ListarRegistros(condicion);
        }
        catch(Exception ex)
        {
            throw ex;
        }
        return resultado;
    }
}

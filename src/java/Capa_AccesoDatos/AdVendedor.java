/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_AccesoDatos;

import Capa_Entidades.Vendedor;
import java.sql.*;

/**
 *
 * @author Steven
 */
public class AdVendedor {
    
    private Connection _cnn;
    private String _mensaje;

    public String getMsg() {
        return _mensaje;
    }

    //constructor
    public AdVendedor() throws Exception {

        try {
            _cnn = ClaseConexion.getConnection();
            _mensaje = "";
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    public int insertarVendedor(Vendedor vendedor)throws Exception{
        int resultado = -1;
        
        try{
            //crear sentencia
            CallableStatement cs = _cnn.prepareCall("{call insertarVendedor(?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1,vendedor.getId_vendedor());
            cs.setString(2, vendedor.getNombre());
            cs.setString(3, vendedor.getApellido1());
            cs.setString(4, vendedor.getApellido2());
            cs.setString(5, vendedor.getUsuario());
            cs.setString(6, vendedor.getContrasena());
            cs.setString(7, vendedor.getEmail());
            cs.setString(8, vendedor.getTelefono());
            cs.setString(9, _mensaje);
            cs.registerOutParameter(1, Types.INTEGER);
            cs.registerOutParameter(9, Types.VARCHAR);
            cs.executeUpdate();
            _mensaje = cs.getString(9);
            resultado = cs.getInt(1);
        }
        catch(Exception ex){
            throw ex;
        }
        
        return resultado;
    }
    
    
    public Vendedor obtenerRegistro(String condicion) throws Exception{
        Vendedor vendedor = null;
        ResultSet rs = null;
        try{
            Statement st = _cnn.createStatement();
            String sentencia = "SELECT ID_VENDEDOR, NOMBRE, APELLIDO1, APELLIDO2, FECHA_NACIMIENTO, TELEFONO, EMAIL"
                    + " FROM VENDEDOR";
            
            if(!condicion.equals("")){
                sentencia = String.format("%s where %s", sentencia, condicion);
            }
            
            rs = st.executeQuery(sentencia);
            if(rs.next()){
                vendedor = new Vendedor();
                vendedor.setId_vendedor(rs.getInt(1));
                vendedor.setNombre(rs.getString(2));
                vendedor.setApellido1(rs.getString(3));
                vendedor.setApellido2(rs.getString(4));
                vendedor.setFechaNacimiento(rs.getDate(5));
                vendedor.setTelefono(rs.getString(6));
                vendedor.setEmail(rs.getString(7));
                vendedor.setExiste(true);
            }
            if(vendedor!=null){
                _mensaje = "Vendedor recuperado";
            }
        }catch(Exception ex){
            throw ex;
        }
        
        return vendedor;
    }

}

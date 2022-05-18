/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_AccesoDatos;

import Capa_Entidades.Cliente;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Steven
 */
public class AdCliente {

    private Connection _cnn;
    private String _mensaje;

    public String getMensaje() {
        return _mensaje;
    }

    //constructor
    public AdCliente() throws Exception {

        try {
            _cnn = ClaseConexion.getConnection();
            _mensaje = "";
        } catch (Exception ex) {
            throw ex;
        }
    }

    public List<Cliente> ListarRegistros(String condicion) throws SQLException {

        ResultSet rs = null;
        List<Cliente> lista = new ArrayList();
        try {

            Statement stm = _cnn.createStatement();
            String sentencia = "Select id_cliente ,nombre, Apellido1 , Apellido2, fecha_nacimiento, telefono, email from cliente";
            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s", sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);
            while (rs.next()) {
                lista.add(new Cliente(rs.getInt("id_cliente"), rs.getString("nombre"),
                        rs.getString("apellido1"), rs.getString("apellido2"), rs.getDate("fecha_nacimiento"), rs.getString("telefono"), rs.getString("email")));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            _cnn = null;
        }
        return lista;
    }

    public Cliente ObtenerRegistro(String condicion) throws SQLException {

        Cliente cliente = new Cliente();
        ResultSet rs = null;
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "Select id_cliente ,nombre, Apellido1 , Apellido2, fecha_nacimiento, telefono, email from cliente";
            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s", sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);
            if (rs.next()) {
                cliente.setId_cliente(rs.getInt(1));
                cliente.setNombre(rs.getString(2));
                cliente.setApellido1(rs.getString(3));
                cliente.setApellido2(rs.getString(4));
                cliente.setFechaNacimiento(rs.getDate(5));
                cliente.setTelefono(rs.getString(6));
                cliente.setEmail(rs.getString(7));
                cliente.setExiste(true);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            _cnn = null;
        }
        return cliente;
    }
    
    public int Insertar(Cliente cliente) throws Exception {

        int id_cliente = -1;

        String sentencia = "insert into Cliente(nombre, apellido1, apellido2, fecha_nacimiento, telefono, email) values(?, ? ,?,? ,?,?)";

        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido1());
            ps.setString(3, cliente.getApellido2());
            ps.setDate(4, cliente.getFechaNacimiento());
            ps.setString(5, cliente.getTelefono());
            ps.setString(6, cliente.getEmail());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs != null && rs.next()) {

                id_cliente = rs.getInt(1);
                _mensaje = "Cliente ingresado satisfactoriamente";
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            _cnn = null;
        }

        return id_cliente;
    }
    
    public int Modificar(Cliente cliente) throws Exception {

        int resultado = 0;
        String sentencia = "update Cliente set nombre=?, Apellido1 = ?, Apellido2 = ?, fecha_nacimiento = ?, telefono=?, email =? where id_cliente=?";
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido1());
            ps.setString(3, cliente.getApellido2());
            ps.setDate(4, cliente.getFechaNacimiento());
            ps.setString(5, cliente.getTelefono());
            ps.setString(6, cliente.getEmail());
            ps.setInt(7, cliente.getId_cliente());
            resultado = ps.executeUpdate();
            if (resultado > 0) {
                _mensaje = "Registro modificado satisfactoriamente";
            }
        } catch (Exception ex) {
            throw ex;
        }
        return resultado;
    }
    
    public int Eliminar(Cliente cliente) throws Exception{
        
//        int resultado = 0;
//        String sentencia = "delete cliente where id_cliente = ?";
//        try{
//            PreparedStatement ps= _cnn.prepareStatement(sentencia);
//            ps.setInt(1, cliente.getId_cliente());
//            resultado= ps.executeUpdate();
//            if(resultado > 0){
//                _mensaje= "Registro eliminado satisfactoriamente";
//            }
//        }catch(Exception ex){throw ex;}
//        finally{
//        _cnn=null;}
//        return resultado;

        int resultado = -1;
        
        try{
            CallableStatement cs = _cnn.prepareCall("{call eliminarCliente(?,?,?)}");
            cs.setInt(1, resultado);
            cs.setInt(2, cliente.getId_cliente());
            cs.setString(3, _mensaje);
            cs.registerOutParameter(1, Types.INTEGER);
            cs.registerOutParameter(3, Types.VARCHAR);
            cs.executeUpdate();
            resultado = cs.getInt(1);
            _mensaje=cs.getString(3);
            
        }catch(Exception ex){
            throw ex;
        }
        return resultado;
    }

}

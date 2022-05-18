/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_AccesoDatos;

import Capa_Entidades.Veterinario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Steven
 */
public class AdVeterinario {
    
    private Connection _cnn;
    private String _mensaje;

    public String getMensaje() {
        return _mensaje;
    }

    //constructor
    public AdVeterinario() throws Exception {

        try {
            _cnn = ClaseConexion.getConnection();
            _mensaje = "";
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    public Veterinario ObtenerRegistro(String condicion) throws Exception{
    
        Veterinario veterinario = new Veterinario();
        ResultSet rs = null;
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "Select id_veterinario ,nombre, Apellido1 , Apellido2, fecha_nacimiento, telefono, email from veterinario";
            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s", sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);
            if (rs.next()) {
                veterinario.setId_veterinario(rs.getInt(1));
                veterinario.setNombre(rs.getString(2));
                veterinario.setApellido1(rs.getString(3));
                veterinario.setApellido2(rs.getString(4));
                veterinario.setFechaNacimiento(rs.getDate(5));
                veterinario.setTelefono(rs.getString(6));
                veterinario.setEmail(rs.getString(7));
                veterinario.setExiste(true);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            _cnn = null;
        }
        return veterinario;
    }
    
    public List<Veterinario> ListarRegistros(String condicion) throws SQLException {

        ResultSet rs = null;
        List<Veterinario> lista = new ArrayList();
        try {

            Statement stm = _cnn.createStatement();
            String sentencia = "Select id_veterinario ,nombre, Apellido1 , Apellido2, fecha_nacimiento, telefono, email from veterinario";
            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s", sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);
            while (rs.next()) {
                lista.add(new Veterinario(rs.getInt("id_veterinario"), rs.getString("nombre"),
                        rs.getString("apellido1"), rs.getString("apellido2"), rs.getDate("fecha_nacimiento"), rs.getString("telefono"), rs.getString("email")));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            _cnn = null;
        }
        return lista;
    }
    
}

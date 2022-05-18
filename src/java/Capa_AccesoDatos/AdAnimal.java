/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_AccesoDatos;

import Capa_Entidades.Animal;
import java.sql.*;

/**
 *
 * @author Steven
 */
public class AdAnimal {
    
    private Connection _cnn;
    private String _mensaje;

    public String getMensaje() {
        return _mensaje;
    }

    //constructor
    public AdAnimal() throws Exception {

        try {
            _cnn = ClaseConexion.getConnection();
            _mensaje = "";
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    
    public Animal ObtenerRegistro(String condicion) throws SQLException {

        Animal animal = new Animal();
        ResultSet rs = null;
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "Select id_animal ,id_cliente, alias, especie, raza, color, fecha_nacimiento from animal";
            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s", sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);
            if (rs.next()) {
                animal.setId_animal(rs.getInt(1));
                animal.setId_cliente(rs.getInt(2));
                animal.setAlias(rs.getString(3));
                animal.setEspecie(rs.getString(4));
                animal.setRaza(rs.getString(5));
                animal.setColor(rs.getString(6));
                animal.setFechaNacimiento(rs.getDate(7));
                animal.setExiste(true);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            _cnn = null;
        }
        return animal;
    }
    
}

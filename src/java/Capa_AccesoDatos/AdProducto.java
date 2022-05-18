/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_AccesoDatos;
import Capa_Entidades.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Steven
 */
public class AdProducto {
    private Connection _cnn;
    private String _mensaje;

    public String getMensaje() {
        return _mensaje;
    }

    //constructor
    public AdProducto() throws Exception {

        try {
            _cnn = ClaseConexion.getConnection();
            _mensaje = "";
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    public List<Producto> ListarRegistros(String condicion) throws SQLException {

        ResultSet rs = null;
        List<Producto> lista = new ArrayList();
        try {

            Statement stm = _cnn.createStatement();
            String sentencia = "Select id_producto, descripcion, categoria, precio, descuento, existencia from producto";
            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s", sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);
            while (rs.next()) {
                lista.add(new Producto(rs.getInt("id_producto"), rs.getString("descripcion"),
                        rs.getString("categoria"), rs.getDouble("precio"), rs.getDouble("descuento"),rs.getInt("existencia")));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            _cnn = null;
        }
        return lista;
    }
    
    public Producto ObtenerRegistro(String condicion) throws SQLException {

        Producto producto = new Producto();
        ResultSet rs = null;
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "Select id_producto, descripcion, categoria, precio, descuento, existencia from producto";
            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s", sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);
            if (rs.next()) {
                producto.setId_producto(rs.getInt(1));
                producto.setDescripcion(rs.getString(2));
                producto.setCategoria(rs.getString(3));
                producto.setPrecio(rs.getDouble(4));
                producto.setDescuento(rs.getDouble(5));
                producto.setExistencia(rs.getInt(6));
                producto.setExiste(true);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            _cnn = null;
        }
        return producto;
    }
    
    
    public int Insertar(Producto producto) throws Exception {

        int id_producto = -1;

        String sentencia = "insert into Producto(descripcion, categoria, precio, descuento, existencia) values(?, ? ,?,? ,?,?)";

        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, producto.getDescripcion());
            ps.setString(2, producto.getCategoria());
            ps.setDouble(3, producto.getPrecio());
            ps.setDouble(4, producto.getDescuento());
            ps.setInt(5, producto.getExistencia());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs != null && rs.next()) {

                id_producto = rs.getInt(1);
                _mensaje = "Cliente ingresado satisfactoriamente";
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            _cnn = null;
        }

        return id_producto;
    }
    
    public int Modificar(Producto producto) throws Exception {

        int resultado = 0;
        String sentencia = "update Producto set descripcion=?, categoria = ?, precio = ?, descuento = ?, existencia=? where id_producto=?";
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            ps.setString(1, producto.getDescripcion());
            ps.setString(2, producto.getCategoria());
            ps.setDouble(3, producto.getPrecio());
            ps.setDouble(4, producto.getDescuento());
            ps.setInt(5, producto.getExistencia());
            resultado = ps.executeUpdate();
            if (resultado > 0) {
                _mensaje = "Registro modificado satisfactoriamente";
            }
        } catch (Exception ex) {
            throw ex;
        }
        return resultado;
    }
    
    public int Eliminar(Producto producto) throws Exception{
        

        int resultado = -1;
        
        try{
            CallableStatement cs = _cnn.prepareCall("{call eliminarProducto(?,?,?)}");
            cs.setInt(1, resultado);
            cs.setInt(2, producto.getId_producto());
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

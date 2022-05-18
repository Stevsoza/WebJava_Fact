/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_AccesoDatos;

import Capa_Entidades.Detalle_venta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Steven
 */
public class AdDetalle_venta {
    
    private Connection _cnn;
    private String _mensaje;

    public String getMensaje() {
        return _mensaje;
    }

    public AdDetalle_venta() throws Exception {

        try {
            String url = Config.getConnectionString();
            _cnn = DriverManager.getConnection(url);
            _mensaje = "";
        } catch (Exception ex) {
            throw ex;
        }
    }

    public int insertarDetalleVenta(Detalle_venta detalle) throws Exception {
        int resultado = -1;

        String sentencia = "insert into Detalle_venta(id_venta, id_producto, id_veterinario, id_animal ,cantidad, precio, descuento, total)"
                + "values(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, detalle.getId_venta());
            ps.setInt(2, detalle.getId_producto());
            ps.setInt(3, detalle.getId_veterinario());
            ps.setInt(4, detalle.getId_animal());
            ps.setDouble(5, detalle.getCantidad());
            ps.setDouble(6, detalle.getPrecio());
            ps.setDouble(7, detalle.getDescuento());
            ps.setDouble(8, detalle.getTotal());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs != null && rs.next()) {
                resultado = rs.getInt(1);
                _mensaje = "Detalle Ingresado satisfactoriamente";
            }else{
                _mensaje = "No se ha podido ingresar";
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            _cnn = null;
        }
        return resultado;
    }
    
    public List<Detalle_venta> ListarRegistros(String Condicion) throws Exception{
        ResultSet Rs = null;
        Detalle_venta entidad;
        List<Detalle_venta> Lista = new ArrayList<>();
        Connection _conexion = null;
        try{
            _conexion = _cnn;
            Statement ST = _conexion.createStatement();
            String Sentencia;
            
            Sentencia = "SELECT id_venta, d.ID_PRODUCTO , DESCRIPCION, id_veterinario, id_animal, CANTIDAD, d.PRECIO, d.descuento, total"
                    + " FROM detalle_venta d "
                    + "INNER JOIN PRODUCTO p ON d.ID_PRODUCTO = p.ID_PRODUCTO";
            if(!Condicion.equals("")){
                Sentencia = String.format("%s Where %s", Sentencia, Condicion);
            }
            Rs = ST.executeQuery(Sentencia);
            while(Rs.next()){
                entidad = new Detalle_venta(Rs.getInt("id_venta"),
                        Rs.getInt("id_producto"),
                        Rs.getString("Descripcion"),
                        Rs.getInt("id_veterinario"),
                        Rs.getInt("id_animal"),
                        Rs.getInt("cantidad"),
                        Rs.getDouble("precio"),
                        Rs.getDouble("descuento"),
                        Rs.getDouble("total"));
                Lista.add(entidad);
            }
        }catch(Exception ex){
            throw ex;
        }finally{
            if(_conexion != null){
                _conexion = null;
            }
        }
        return Lista;
    }
    
    
    public Detalle_venta ObtenerRegistro(String condicion) throws Exception{
        Detalle_venta dVenta = null;
        ResultSet rs = null;
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "Select id_venta, dv.id_producto, p.descripcion, id_veterinario, id_animal , cantidad, p..precio, p.descuento, total from Detalle_venta dv"
                    + " inner join Producto p on p.id_producto = dv.id_producto";
            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s", sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);
            if (rs.next()) {
                dVenta = new Detalle_venta();
                dVenta.setId_venta(rs.getInt(1));
                dVenta.setId_producto(rs.getInt(2));
                dVenta.setDescripcion(rs.getString(3));
                dVenta.setId_animal(rs.getInt(4));
                dVenta.setId_veterinario(rs.getInt(5));
                dVenta.setCantidad(rs.getInt(6));
                dVenta.setPrecio(rs.getDouble(7));
                dVenta.setDescuento(rs.getDouble(8));
                dVenta.setExiste(true);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            _cnn = null;
        }
        return dVenta;
    }
    
    public int eliminarDetalle(Detalle_venta detalle) throws Exception{
        int resultado = -1;
        
        try{
            CallableStatement cs = _cnn.prepareCall("{call eliminar_detalle(?,?,?,?)}");
            cs.setInt(1, resultado);
            cs.setInt(2,detalle.getId_venta());
            cs.setInt(3,detalle.getId_producto());
            cs.setString(4, _mensaje);
            cs.registerOutParameter(1, Types.INTEGER);
            cs.registerOutParameter(4, Types.VARCHAR);
            
            cs.executeUpdate();
            resultado = cs.getInt(1);
            _mensaje = cs.getString(4);
            
        }catch(Exception ex){
            throw ex;
        }finally{
            _cnn= null;
        }
        
        return resultado;
    }
}

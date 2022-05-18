/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_AccesoDatos;

import java.sql.*;
import Capa_Entidades.Factura_venta;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Steven
 */
public class AdFacturaVenta {

    private Connection _cnn;
    private String _mensaje;

    public String getMsg() {
        return _mensaje;
    }

    public AdFacturaVenta() throws Exception {

        try {
            _cnn = ClaseConexion.getConnection();
        } catch (Exception ex) {
            throw ex;
        }
    }

    public int Insertar(Factura_venta venta) throws Exception {

        int id_venta = -1;
        try {
            if (!venta.isExiste()) {

                CallableStatement cs = _cnn.prepareCall("{call REV_FACTURA(?,?,?)}");
                cs.setInt(1, venta.getId_venta());
                cs.setTimestamp(2, venta.getFecha());
                cs.setString(3, _mensaje);
                cs.registerOutParameter(1, Types.INTEGER);
                cs.registerOutParameter(3, Types.VARCHAR);
                cs.executeUpdate();
                _mensaje = cs.getString(3);
                id_venta = cs.getInt(1);

            } else {
                String sentencia = "UPDATE FACTURA_VENTA SET FECHA = ?, ID_VENDEDOR = ?, ID_CLIENTE = ?, IMPORTE = ?, DESCUENTO = ?, ESTADO = ?, TIPO = ?"
                        + "         WHERE ID_VENTA = ?";
                PreparedStatement ps = _cnn.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
                ps.setTimestamp(1, venta.getFecha());
                ps.setInt(2, venta.getId_vendedor());
                ps.setInt(3, venta.getId_cliente());
                ps.setDouble(4, venta.getImporte());
                ps.setDouble(5, venta.getDescuento());
                ps.setString(6, venta.getEstado());
                ps.setString(7, venta.getTipo());
                ps.setInt(8, venta.getId_venta());
                ps.execute();
                ResultSet rs = ps.getGeneratedKeys();

                if (rs != null && rs.next()) {

                    id_venta = rs.getInt(1);
                    _mensaje = "Factura Guardada";
                }
            }

        } catch (Exception ex) {
            throw ex;
        } finally {
            _cnn = null;
        }

        return id_venta;
    }

    public Factura_venta ObtenerRegistro(String condicion) throws Exception {
        Factura_venta fVenta = null;
        ResultSet rs = null;
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "Select id_venta, fecha, id_vendedor, id_cliente, tipo, descuento, importe, estado from factura_venta";
            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s", sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);
            if (rs.next()) {
                fVenta = new Factura_venta();
                fVenta.setId_venta(rs.getInt(1));
                fVenta.setFecha(rs.getTimestamp(2));
                fVenta.setId_vendedor(rs.getInt(3));
                fVenta.setId_cliente(rs.getInt(4));
                fVenta.setTipo(rs.getString(5));
                fVenta.setDescuento(rs.getDouble(6));
                fVenta.setImporte(rs.getDouble(7));
                fVenta.setEstado(rs.getString(8));
                fVenta.setExiste(true);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            _cnn = null;
        }
        return fVenta;
    }

    public int Cancelar(Factura_venta fventa) throws Exception {

        int resultado = -1;
        try {

            String sentencia = "UPDATE FACTURA_VENTA"
                    + " SET DESCUENTO = ? , ESTADO = ? , IMPORTE = ?"
                    + " WHERE ID_VENTA = ?";
            PreparedStatement ps = _cnn.prepareStatement(sentencia);

            ps.setDouble(1, fventa.getDescuento());
            ps.setString(2, fventa.getEstado());
            ps.setDouble(3, fventa.getImporte());
            ps.setInt(4, fventa.getId_venta());
            resultado = ps.executeUpdate();

        } catch (Exception ex) {

            throw ex;
        }
        return resultado;
    }

    public List<Factura_venta> ListarRegistros(String condicion) throws SQLException {

        ResultSet rs = null;
        List<Factura_venta> lista = new ArrayList();
        try {

            Statement stm = _cnn.createStatement();
            String sentencia = "Select id_venta, id_cliente, fecha, id_vendedor, importe, descuento, estado, tipo from Factura_venta";
            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s", sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);
            while (rs.next()) {
                lista.add(new Factura_venta(
                        rs.getInt("id_venta"),
                        rs.getInt("id_cliente"),
                        rs.getTimestamp("Fecha"),
                        rs.getInt("id_vendedor"),
                        rs.getDouble("importe"),
                        rs.getDouble("descuento"),
                        rs.getString("estado"),
                        rs.getString("tipo")
                )
                );
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            _cnn = null;
        }
        return lista;
    }
    
    public int Eliminar(Factura_venta fVenta) throws Exception{
        int resultado = -1;
        
        try{
            CallableStatement cs = _cnn.prepareCall("{call eliminarVenta(?,?,?)}");
            cs.setInt(1, resultado);
            cs.setInt(2, fVenta.getId_venta());
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

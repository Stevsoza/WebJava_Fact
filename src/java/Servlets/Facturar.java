/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Capa_Entidades.Detalle_venta;
import Capa_Entidades.Factura_venta;
import Capa_Entidades.Vendedor;
import Capa_logicaNegocio.LnDetalleVenta;
import Capa_logicaNegocio.LnFacturaVenta;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.Instant;
import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 *
 * @author Steven
 */
public class Facturar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession();
            int resultado1 = -1, resultado2 = -1;
            //creamos el acceso a la capa logica y los 
            LnFacturaVenta lnFventa = new LnFacturaVenta();
            LnDetalleVenta lnVenta = new LnDetalleVenta();
            Factura_venta fventa = new Factura_venta();
            Vendedor vendedor = (Vendedor) session.getAttribute("vendedor");
            fventa.setId_venta(Integer.parseInt(request.getParameter("n_factura")));
            //revisar si la factura ya ha sido guardada y tiene cliente{
            fventa = lnFventa.ObtenerRegistro("id_venta=" + fventa.getId_venta());
            if (fventa.getEstado().equals("CANCELADO")) {
                response.sendRedirect("Facturacion.jsp?nFactura=" + fventa.getId_venta() + "&msg=Esta factura esta cancelada por lo tanto no se puede modificar");
            } else {
                if (fventa.getId_cliente() == 0) {
                    //si es una nueva venta, ya que cuando se crean los detalles tiene que ir con cliente
                    fventa.setId_cliente(Integer.parseInt(request.getParameter("f_cliente")));
                    String tipoFactura = request.getParameter("t_factura");
                    fventa.setEstado("PENDIENTE");
                    fventa.setFecha(Timestamp.from(Instant.now()));
                    fventa.setId_vendedor(vendedor.getId_vendedor());
                    fventa.setTipo(tipoFactura);
                    fventa.setExiste(true);
                    resultado1 = lnFventa.Insertar(fventa);
                } else {
                    //porque la factura ya esta guardada
                    resultado1 = 1;
                }
                //}fin if

                //ahora el detalle
                Detalle_venta dVenta = new Detalle_venta();
                dVenta.setId_venta(Integer.parseInt(request.getParameter("n_factura")));
                dVenta.setDescripcion(request.getParameter("txtdescripcion"));
                dVenta.setId_producto(Integer.parseInt(request.getParameter("txtidproducto")));
                dVenta.setPrecio(Double.parseDouble(request.getParameter("txtprecio")));
                dVenta.setCantidad(Integer.parseInt(request.getParameter("txtcantidad")));
                dVenta.setTotal(dVenta.getPrecio() * dVenta.getCantidad());

                resultado2 = lnVenta.insertarDetalleVenta(dVenta);

                if (resultado1 > -1 && resultado2 > -1) {
                    response.sendRedirect("Facturacion.jsp?nFactura=" + fventa.getId_venta());
                } else {
                    response.sendRedirect("Facturacion.jsp?nFactura"+ fventa.getId_venta() +"&msg=No se ha podido Ingresar");
                }
            }

        } catch (Exception ex) {
            out.print(ex.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Capa_Entidades.Detalle_venta;
import Capa_Entidades.Factura_venta;
import Capa_logicaNegocio.LnDetalleVenta;
import Capa_logicaNegocio.LnFacturaVenta;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Steven
 */
public class EliminarDetalle extends HttpServlet {

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
        
        //para eliminar detalle vamos a ingresar a la capa de negocio de factura venta
        LnFacturaVenta lnVenta = new LnFacturaVenta();
        //agarramos los valores que nos mando el formulario de ingresar producto
        int idVenta = Integer.parseInt(request.getParameter("idVenta"));
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        
        //creamos una factura venta que luego le asignaremos su valor
        Factura_venta fVenta;
        String condicion = "id_venta = " + idVenta;
        
        //tambien tendremos que ingresar a los detalles
        //pues es lo que trataremos de eliminar
        //creamos el detalle venta
        Detalle_venta dVen = new Detalle_venta();
        
        //asignamos los valores que trae el request para asignarlos al detalle
        dVen.setId_venta(idVenta);
        dVen.setId_producto(idProducto);
        
        //
        LnDetalleVenta lnDetalle = new LnDetalleVenta();
        int resultado = -1;
        String mensaje = "";
        try {
            //obteniendo la factura creamos un control de flujo
            //en el cual revisaremos que la factura no este cancelada para poder
            //eliminar el detalle
            fVenta = lnVenta.ObtenerRegistro(condicion);
            if (!fVenta.getEstado().equals("CANCELADO")) {
                resultado = lnDetalle.eliminarDetalle(dVen);
                mensaje = lnDetalle.getMsg();

                //aqui el resultado es lo que traemos desde un parametro asignado
                //en la base de datos
                if (resultado > -1) {
                    //cuando ingrese en este bloque significa que la factura se elimino
                    //por defecto el direccionamiento a la página nos crea una nueva factura
                    //sin cliente ni vendedor o productos
                    response.sendRedirect("Facturacion.jsp?msg=" + mensaje);
                } else {
                    //es -1 si la factura tiene más detalles por ende volveremos
                    //a visualizar esa factura
                    response.sendRedirect("Facturacion.jsp?nFactura=" + idVenta + "&msg=" + mensaje);
                }
            }else{
                //si la factura esta cancelada la mostrará con un mensaje diciendo que no se puede modificar
                response.sendRedirect("Facturacion.jsp?nFactura="+idVenta+"&msg=La Factura esta cancelada no se puede modificar");
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

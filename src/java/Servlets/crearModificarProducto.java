/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Capa_Entidades.Producto;
import Capa_logicaNegocio.LnProducto;
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
public class crearModificarProducto extends HttpServlet {

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
        
        int resultado = -1;
        LnProducto lnProducto = new LnProducto();
        Producto producto = new Producto();
        producto.setId_producto(Integer.parseInt(request.getParameter("nuevoid")));
        producto.setDescripcion(request.getParameter("txtdescripcion"));
        producto.setCategoria(request.getParameter("txtcategoria"));
        producto.setPrecio(Double.parseDouble(request.getParameter("txtprecio")));
        producto.setDescuento(Double.parseDouble(request.getParameter("txtfecha")));
        producto.setExistencia(Integer.parseInt(request.getParameter("txtTelefono")));

        try {
            if (producto.getId_producto()> 0) {
                resultado = lnProducto.Modificar(producto);
                if(resultado > -1){
                    response.sendRedirect("mantenimiento_productos.jsp?msg=Producto Actualizado");
                }else{
                    response.sendRedirect("mantenimiento_productos.jsp?msg=No se pudo actualizar");
                }
            } else {
                resultado = lnProducto.Insertar(producto);
                if(resultado > -1){
                    response.sendRedirect("mantenimiento_productos.jsp?msg=Producto Ingresado");
                }else{
                    response.sendRedirect("mantenimiento_productos.jsp?msg=No se pudo ingresar");
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

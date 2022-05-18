/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Capa_Entidades.Cliente;
import Capa_logicaNegocio.LnCliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Steven
 */
public class crearModificarCliente extends HttpServlet {

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
        LnCliente lnCliente = new LnCliente();
        Cliente cliente = new Cliente();
        cliente.setId_cliente(Integer.parseInt(request.getParameter("nuevoid")));
        cliente.setNombre(request.getParameter("txtnombre"));
        cliente.setApellido1(request.getParameter("txtapellido1"));
        cliente.setApellido2(request.getParameter("txtapellido2"));
        cliente.setFechaNacimiento(Date.valueOf(request.getParameter("txtfecha")));
        cliente.setTelefono(request.getParameter("txtTelefono"));
        cliente.setEmail(request.getParameter("txtEmail"));

        try {
            if (cliente.getId_cliente() > 0) {
                resultado = lnCliente.Modificar(cliente);
                if(resultado > -1){
                    response.sendRedirect("mantenimiento_clientes.jsp?msg=Cliente Actualizado");
                }else{
                    response.sendRedirect("mantenimiento_clientes.jsp?msg=No se pudo actualizar");
                }
            } else {
                resultado = lnCliente.Insertar(cliente);
                if(resultado > -1){
                    response.sendRedirect("mantenimiento_clientes.jsp?msg=Cliente Ingresado");
                }else{
                    response.sendRedirect("mantenimiento_clientes.jsp?msg=No se pudo ingresar");
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

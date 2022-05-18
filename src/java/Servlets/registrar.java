/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Capa_Entidades.Vendedor;
import Capa_logicaNegocio.LnVendedor;
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
public class registrar extends HttpServlet {

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
        //revisar que el usuario no exista
        LnVendedor vend = new LnVendedor();
        Vendedor vendedor = new Vendedor();
        int resultado = -1;
        String mensaje = "";
        try {
            vendedor.setId_vendedor(0);
            vendedor.setNombre(request.getParameter("txtnombre"));
            vendedor.setApellido1(request.getParameter("txtapellido1"));
            vendedor.setApellido2(request.getParameter("txtapellido2"));
            vendedor.setUsuario(request.getParameter("Usuario"));
            vendedor.setContrasena(request.getParameter("contrasena1"));
            vendedor.setEmail(request.getParameter("txtemail"));
            vendedor.setTelefono(request.getParameter("txtTelefono"));
            
            resultado = vend.insertarVendedor(vendedor);
            mensaje = vend.getMsg();
            if(resultado > -1){
                response.sendRedirect("login.jsp?msg="+mensaje);
            }
            else{
                response.sendRedirect("registro.jsp?msg="+mensaje);
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

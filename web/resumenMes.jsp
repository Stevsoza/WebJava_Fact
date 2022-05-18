<%-- 
    Document   : resumenMes
    Created on : 18/05/2022, 03:50:06 AM
    Author     : Steven
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="Capa_logicaNegocio.LnCliente"%>
<%@page import="java.util.List"%>
<%@page import="Capa_Entidades.Cliente"%>
<%@page import="Capa_Entidades.Vendedor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String mensaje = "";

    if (request.getParameter("msg") != null && !request.getParameter("msg").equals("")) {
        mensaje = request.getParameter("msg");
    }
    Vendedor vendedor = new Vendedor();
    HttpSession actualSession = null;
    if (request.getSession() != null && !request.getSession().isNew()) {
        actualSession = request.getSession();
        vendedor = (Vendedor) actualSession.getAttribute("vendedor");

    } else {
        response.sendRedirect("login.jsp");
    }
%>
<html>
    <head>
        <title>Inicio</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <META HTTP-EQUIV="refresh" CONTENT="<%= actualSession.getMaxInactiveInterval()%>; URL=login.jsp" />
        <link href="CSS/estilos.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css" />
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/2.0.1/css/buttons.dataTables.min.css" />
        <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/styles.css" rel="stylesheet" type="text/css"/>  
    </head>
    <body class="imagenFondo vh-100 overflow-hidden">
        <div class="position-absolute top-0 end-0 mx-5 mt-1 position-fixed" style="cursor: pointer;">
            <a class="text-primary" data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight"><i class="fas fa-user-circle" style="font-size: 2.5em;"></i></a>
        </div>
        <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasRight" aria-labelledby="offcanvasRightLabel">
            <div class="offcanvas-header">
                <h5 id="offcanvasRightLabel">Vendedor: <%=vendedor.getNombre()%></h5>
                <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
            </div>
            <div class="offcanvas-body">
                <a class="btn btn-primary" href="CerrarSesion">Cerrar Sesión</a>
            </div>
        </div>
        <header class="container pt-2">
            <div class="row justify-content-center">
                <ul class="nav col-10 bg-secondary justify-content-center rounded-top fw-bolder">
                    <li class="nav-item rounded-top active bg-primary">
                        <a class="nav-link text-white" href="index.jsp">Inicio</a>
                    </li>
                    <li class="nav-item rounded-top">
                        <a class="nav-link" href="Facturacion.jsp">Facturación</a>
                    </li>
                    <li class="nav-item rounded-top">
                        <a class="nav-link" href="mantenimiento_clientes.jsp">Mantenimiento Clientes</a>
                    </li>
                    <li class="nav-item rounded-top">
                        <a class="nav-link" href="mantenimiento_productos.jsp">Mantenimiento Productos</a>
                    </li>
                </ul>
            </div>
        </header>

        <%
            LnCliente lnCliente = new LnCliente();
            List<Cliente> lista;
            lista = lnCliente.ListarRegistros("");

        %>
        <div class="container-fluid bg-primary p-0 m-0 h-100 overflow-hidden">
            <div class="container rounded p-0">
                <div class="row p-0 mt-5">
                    <div class="col-12">
                        <div class="card p-0 shadow">
                            <div class="card-header p-2">Clientes</div>
                            <div class="card-body p-2">
                                <div class="d-flex justify-content-center"><a href="crearModificar.jsp" class="btn btn-primary"><i class="fas fa-plus" style="color: white"></i></a></div>

                                <table class="table" id="tblClientesLista">
                                    <thead>
                                        <tr>
                                            <td>ID</td>
                                            <td>Nombre</td>
                                            <td>Primer apellido</td>
                                            <td>Segundo apellido</td>
                                            <td>Fecha Nacimiento</td>
                                            <td>Telefóno</td>
                                            <td>Email</td>
                                            <td>Opciones</td>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%for (Cliente registro : lista) {
                                        %>

                                        <tr>
                                            <%int codigo = registro.getId_cliente();%>
                                            <td><%=codigo%></td>
                                            <td><%=registro.getNombre()%></td>
                                            <td><%=registro.getApellido1()%></td>
                                            <td><%=registro.getApellido2()%></td>
                                            <td><%=registro.getFechaNacimiento()%></td>
                                            <td><%=registro.getTelefono()%></td>
                                            <td><%=registro.getEmail()%></td>
                                            <td>
                                                <a href="crearModificar.jsp?idCliente=<%=codigo%>"><i class="fas fa-user-edit"></i></a>
                                                <a href="EliminarCliente?idCliente=<%=codigo%>"><i class="fas fa-trash-alt"></i></a>
                                            </td>
                                        </tr>  
                                        <%}%>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- crearemos un offcanvas para mostrar mensajes -->
        <button id="btnForMsg" class="invisible" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasTop" aria-controls="offcanvasTop"></button>

        <div class="offcanvas offcanvas-top" tabindex="-1" id="offcanvasTop" aria-labelledby="offcanvasTopLabel">
            <div class="offcanvas-header">
                <h5 id="offcanvasTopLabel justify-content-center">Aviso:</h5>
                <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
            </div>
            <div class="offcanvas-body">
                <h1 id="msgFromServer"><%=mensaje%></h1>
            </div>
        </div>
        <!--fin del offcanvas-->

        <script src="lib/jquery/dist/jquery.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap/dist/js/bootstrap.bundle.js" type="text/javascript"></script>
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script>
        <script src="lib/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>
        <script src="js/scriptVeterinaria.js" type="text/javascript"></script>
    </script>
</body>
</html>

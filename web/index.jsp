<%-- 
    Document   : index
    Created on : 14/05/2022, 07:49:22 PM
    Author     : Steven
--%>



<%@page import="java.time.Month"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.sql.Date"%>
<%@page import="Capa_logicaNegocio.LnFacturaVenta"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Capa_Entidades.Factura_venta"%>
<%@page import="Capa_Entidades.Vendedor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
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
        <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/estilos.css" rel="stylesheet" type="text/css"/>
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
                    <li class="nav-item active bg-primary rounded-top">
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
                    <li class="nav-item rounded-top">
                        <a class="nav-link" href="mantenimiento_facturas.jsp">Mis Facturas</a>
                    </li>
                </ul>
            </div>
        </header>
        <div class="container-fluid bg-primary p-0 m-0 h-100 overflow-hidden">
            <div class="container rounded p-0">
                <div class="row p-0 mt-5">
                    <!--<div class="col-6">
                        <div class="card p-0 shadow">
                            <div class="card-header p-2"></div>
                            <div class="card-body p-2">
                                <a class="btn btn-primary">Resumen mes</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="card p-0 shadow">
                            <div class="card-header p-2"></div>
                            <div class="card-body p-2">
                                 <a class="btn btn-primary">Resumen Ventas Empleado</a>
                            </div>
                        </div>
                    </div>
                </div>-->
                <div class="row p-0 mt-5 justify-content-center">
                    <div class="col-8 mt-5 d-flex flex-column">
                        <h1 class="fw-bolder text-white text-center">Bienvenido: <%=vendedor.getNombre()%></h1>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <script src="lib/jquery/dist/jquery.min.js" type="text/javascript"></script>
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script>
        <script src="lib/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>
        <script src="js/scriptVeterinaria.js" type="text/javascript"></script>
    </body>
</html>

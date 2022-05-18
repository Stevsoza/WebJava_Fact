<%-- 
    Document   : Mantenimiento Clientes
    Created on : 14/05/2022, 07:49:22 PM
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
        <title>Mantenimiento Clientes</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <META HTTP-EQUIV="refresh" CONTENT="<%= actualSession.getMaxInactiveInterval()%>; URL=login.jsp" />
        <link href="CSS/estilos.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css" />
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/2.0.1/css/buttons.dataTables.min.css" />
        <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/bootstrap-datepicker/css/bootstrap-datepicker3.standalone.min.css" rel="stylesheet" type="text/css"/>
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
                    <li class="nav-item rounded-top">
                        <a class="nav-link" href="index.jsp">Inicio</a>
                    </li>
                    <li class="nav-item rounded-top">
                        <a class="nav-link" href="Facturacion.jsp">Facturación</a>
                    </li>
                    <li class="nav-item active bg-primary rounded-top">
                        <a class="nav-link text-white" href="mantenimiento_clientes.jsp">Mantenimiento Clientes</a>
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
                <div class="row p-0 mt-5 justify-content-center">
                    <div class="col-8">
                        <div class="card p-0 shadow">
                            <div class="card-header p-2">Crear modificar</div>
                            <div class="card-body p-4">
                                <form id="formCM" action="crearModificarCliente">
                                    <div class="form-group row justify-content-center">
                                        <%
                                            LnCliente lncliente = new LnCliente();
                                            Cliente cliente = null;
                                            if (request.getParameter("idCliente") != null
                                                    && !request.getParameter("idCliente").equals("")) {

                                                int codigo = Integer.parseInt(request.getParameter("idCliente"));

                                                cliente = lncliente.ObtenerRegistro("id_cliente =" + codigo);
                                        %>

                                        <div class="col-form-label col-6">
                                            <label for="txtidCliente" class="form-label">ID</label>
                                            <input disabled="" type="text" value="<%=cliente.getId_cliente()%>" name="txtidCliente" id="txtidCliente" class="form-control form-control-sm"/>
                                        </div>


                                        <%
                                            } else {

                                                cliente = new Cliente();
                                            }
                                        %>

                                        <input type="hidden" name="nuevoid" id="nuevoid" value="<%=cliente.getId_cliente()%>"/>
                                        <div class="col-form-label col-6">
                                            <label for="txtnombre" class="form-label">Nombre</label>
                                            <input type="text" name="txtnombre" id="txtnombre" value="<%=cliente.getNombre()%>" class="form-control form-control-sm"/>
                                        </div>

                                        <div class="col-form-label col-6">
                                            <label for="txtapellido1" class="form-label">Primer apellido</label>
                                            <input type="text" name="txtapellido1" id="txtapellido1"
                                                   placeholder="primer apellido" value="<%=cliente.getApellido1()%>" class="form-control form-control-sm"/>
                                        </div>
                                        <div class="col-form-label col-6">
                                            <label for="txtapellido2" class="form-label">Segundo apellido</label>
                                            <input type="text" name="txtapellido2" id="txtapellido2" value="<%=cliente.getApellido2()%>" class="form-control form-control-sm"/>
                                        </div>
                                        <div class="col-form-label col-6">
                                            <label for="txtfecha" class="form-label">Fecha Nacimiento</label>
                                            <input type="text" name="txtfecha" id="txtfecha" value="<%=cliente.getFechaNacimiento()%>" class="datepicker form-control form-control-sm"/>
                                        </div>
                                        <div class="col-form-label col-6">
                                            <label for="txtTelefono" class="form-label">Telefono</label>
                                            <input type="text" name="txtTelefono" id="txtTelefono" value="<%=cliente.getTelefono()%>" class="form-control form-control-sm"/>
                                        </div>
                                        <div class="col-form-label col-6">
                                            <label for="txtEmail" class="form-label">Email</label>
                                            <input type="text" name="txtEmail" id="txtEmail" value="<%=cliente.getEmail()%>" class="form-control form-control-sm"/>
                                        </div>
                                        <input type="submit" hidden id="btnFormCM" />
                                    </div>
                                </form>
                            </div>
                            <div class="card-footer justify-content-around">
                                <div class="d-flex justify-content-between">
                                    <a class="btn btn-primary" onclick="guardar()">Confirmar</a>
                                    <a class="btn btn-primary" href="mantenimiento_clientes.jsp">Volver</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="lib/jquery/dist/jquery.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap/dist/js/bootstrap.bundle.js" type="text/javascript"></script>
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script>
        <script src="lib/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap-datepicker/locales/bootstrap-datepicker.es.min.js" type="text/javascript"></script>
        <script src="js/scriptVeterinaria.js" type="text/javascript"></script>
        <script>
            $(".datepicker").datepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                language: 'es',
                endDate: "today"
            });
            
            function guardar(){
                $("#btnFormCM").trigger('click');
            }
        </script>
    </body>
</html>

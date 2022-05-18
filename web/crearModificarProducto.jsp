<%-- 
    Document   : crearModificarProducto
    Created on : 18/05/2022, 04:17:02 AM
    Author     : Steven
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Capa_logicaNegocio.*"%>
<%@page import="java.util.List"%>
<%@page import="Capa_Entidades.*"%>
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
        <title>Mantenimiento Productos</title>
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
                    <li class="nav-item  rounded-top">
                        <a class="nav-link " href="mantenimiento_clientes.jsp">Mantenimiento Clientes</a>
                    </li>
                    <li class="nav-item rounded-top active bg-primary">
                        <a class="nav-link text-white" href="mantenimiento_productos.jsp">Mantenimiento Productos</a>
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
                                <form id="formCM" action="crearModificarProducto">
                                    <div class="form-group row justify-content-center">
                                        <%
                                            LnProducto lnproducto = new LnProducto();
                                            Producto producto = null;
                                            if (request.getParameter("idProducto") != null
                                                    && !request.getParameter("idProducto").equals("")) {

                                                int codigo = Integer.parseInt(request.getParameter("idProducto"));

                                                producto = lnproducto.ObtenerRegistro("id_producto =" + codigo);
                                        %>

                                        <div class="col-form-label col-6">
                                            <label for="txtidProducto" class="form-label">ID</label>
                                            <input disabled="" type="text" value="<%=producto.getId_producto()%>" name="txtidProducto" id="txtidProducto" class="form-control form-control-sm"/>
                                        </div>


                                        <%
                                            } else {

                                                producto = new Producto();
                                            }
                                        %>

                                        <input type="hidden" name="nuevoid" id="nuevoid" value="<%=producto.getId_producto()%>"/>
                                        <div class="col-form-label col-6">
                                            <label for="txtdescripcion" class="form-label">Descripcion</label>
                                            <input type="text" name="txtdescripcion" id="txtdescripcion" value="<%=producto.getDescripcion()%>" class="form-control form-control-sm"/>
                                        </div>

                                        <div class="col-form-label col-6">
                                            <label for="txtcategoria" class="form-label">Categoria</label>
                                            <input type="text" name="txtcategoria" id="txtcategoria" value="<%=producto.getCategoria()%>" class="form-control form-control-sm"/>
                                        </div>
                                        <div class="col-form-label col-6">
                                            <label for="txtprecio" class="form-label">Precio</label>
                                            <input oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1')" type="text" name="txtprecio" id="txtprecio" value="<%=producto.getPrecio()%>" class="form-control form-control-sm"/>
                                        </div>
                                        <div class="col-form-label col-6">
                                            <label for="txtdescuento" class="form-label">Descuento</label>
                                            <input oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1')" type="text" name="txtdescuento" id="txtfecha" value="<%=producto.getDescuento()%>" class="datepicker form-control form-control-sm"/>
                                        </div>
                                        <div class="col-form-label col-6">
                                            <label for="txtexistencia" class="form-label">Existencia</label>
                                            <input oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1')" type="text" name="txtexistencia" id="txtexistencia" value="<%=producto.getExistencia()%>" class="form-control form-control-sm"/>
                                        </div>
                                        <input type="submit" hidden id="btnFormCM" />
                                    </div>
                                </form>
                            </div>
                            <div class="card-footer justify-content-around">
                                <div class="d-flex justify-content-between">
                                    <a class="btn btn-primary" onclick="guardar()">Confirmar</a>
                                    <a class="btn btn-primary" href="mantenimiento_productos.jsp">Volver</a>
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
            function guardar(){
                $("#btnFormCM").trigger('click');
            }
        </script>
    </body>
</html>
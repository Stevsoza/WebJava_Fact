<%-- 
    Document   : registro
    Created on : 8/05/2022, 09:50:48 PM
    Author     : Steven
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="CSS/estilos.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/spanCss.css" rel="stylesheet" type="text/css"/>
        <title>Registro</title>
    </head>
    <body class="bg-primary">
        <div class="container mt-5">
            <div class="row justify-content-center rounded bg-secondary">
                <form id="form1" class="d-flex" method="post" action="registrar">
                    <div class="col-lg-6 ">
                        <div class="container p-lg-5 px-3 py-5">

                            <div class="col-lg-6 col-sm-12">
                                <div class="form-group mb-3">
                                    <label for="Usuario" class="text-black-50">Usuario</label>
                                    <input type="text" id="Usuario" name="Usuario" class="form-control" />
                                </div>
                            </div>
                            <div class="col-lg-6 col-sm-12">
                                <div class="form-group mb-3">
                                    <label for="contrasena1" class="text-black-50">Constraseña</label>
                                    <input type="password" id="contrasena1" name="contrasena1" class="form-control" />
                                </div>
                            </div>
                            <div class="col-lg-6 col-sm-12">
                                <div class="form-group mb-3">
                                    <label for="contrasena2" class="text-black-50">Repetir constraseña</label>
                                    <input type="password" id="contrasena2" name="contrasena2" class="form-control" />
                                </div>
                            </div>
                            <div class="col-lg-6 col-sm-12">
                                <div class="form-group mb-3">
                                    <label for="txtnombre" class="text-black-50">Nombre</label>
                                    <input type="text" id="txtnombre" name="txtnombre" class="form-control" />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="container p-lg-5 px-3 py-5">
                            <div class="col-lg-6 col-sm-12">
                                <div class="form-group mb-3">
                                    <label for="txtapellido1" class="text-black-50">Primer apellido</label>
                                    <input type="text" id="txtapellido1" name="txtapellido1" class="form-control" />
                                </div>
                            </div>
                            <div class="col-lg-6 col-sm-12">
                                <div class="form-group mb-3">
                                    <label for="txtapellido2" class="text-black-50">Segundo apellido</label>
                                    <input type="text" id="txtapellido2" name="txtapellido2" class="form-control" />
                                </div>
                            </div>
                            <div class="col-lg-6 col-sm-12">
                                <div class="form-group mb-3">
                                    <label for="txtemail" class="text-black-50">Email</label>
                                    <input type="text" id="txtemail" name="txtemail" class="form-control" />
                                </div>
                            </div>
                            <div class="col-lg-6 col-sm-12">
                                <div class="form-group mb-3">
                                    <label for="txtTelefono" class="text-black-50">Telefono</label>
                                    <input type="text" id="textTelefono" name="txtTelefono" oninput="this.value = this.value.replace(/[^1-9.]/g, '').replace(/(\..*)\./g, '$1');" class="form-control" />
                                </div>
                            </div>
                        </div>
                    </div>
                    <input type="submit" class="invisible" id="formButton"/>
                </form>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="row justify-content-center">
                        <div class="col-lg-6 col-sm-12">
                            <div class="d-flex justify-content-center mt-5">
                                <input type="button" class="btn btn-warning mx-1 text-black-50" value="Confirmar" onclick="verificarDatos();"/>
                                <button type="button" onclick="location.href = 'login.jsp'" class="btn btn-warning mx-1 text-black-50">
                                    Cancelar
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-6">
                    <%if (request.getParameter("msg") != null) {
                            String mensaje = request.getParameter("msg");
                    %>
                    <div class="alert alert-danger alert-dismissible" role="alert">
                        <%=mensaje%>
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                    <%}%>
                </div>
            </div>
        </div>
        <script src="lib/jquery/dist/jquery.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap/dist/js/bootstrap.js" type="text/javascript"></script>
        <script src="lib/bootstrap/dist/js/bootstrap.bundle.js" type="text/javascript"></script>
        <script src="lib/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>
        <script src="js/validacionRegistro.js" type="text/javascript"></script>
    </body>
</html>

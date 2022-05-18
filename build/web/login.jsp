<%-- 
    Document   : login
    Created on : 7/05/2022, 11:29:46 AM
    Author     : Steven
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/estilos.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/styles.css" rel="stylesheet" type="text/css"/>
        <title>login</title>
    </head>
    <body class="imagenFondo vh-100 p-0">
        <div class="container my-0">
            <div class="row justify-content-center">
                <div class="col-6">
                    <%  String msg = "";
                        if (request.getParameter("msg") != null && !request.getParameter("msg").equals("")) {
                            msg = request.getParameter("msg");
                    %>
                    <div class="alert alert-danger alert-dismissible" role="alert">
                        <%=msg%>
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                    <%}%>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-sm-12 col-lg-6">
                    <form id="form1" class="d-flex" method="post" action="trylogin">
                        <div class="container rounded bg-secondary p-lg-5 px-3 py-5">
                            <figure class="figure d-flex flex-column justify-content-center w-50 mx-auto">
                                <img src="img/veterinaria.svg" class="figure-img img-fluid rounded" alt="..." />
                                <figcaption class="invisible figure-caption">A caption for the above image.</figcaption>
                            </figure>

                            <div class="row justify-content-center">
                                <div class="col-lg-6 col-sm-12">
                                    <div class="form-group mb-3">
                                        <label for="txtusuario" class="text-black-50">Usuario</label>
                                        <input type="text" id="txtusuario" name="txtusuario" class="form-control" />

                                    </div>
                                </div>
                            </div>
                            <div class="row justify-content-center">
                                <div class="col-lg-6 col-sm-12">
                                    <div class="form-group mb-3">
                                        <label for="txtconstrasena" class="text-black-50">Constraseña</label>
                                        <input type="password" id="txtcontrasena" name="txtcontrasena" class="form-control" />

                                    </div>
                                </div>
                            </div>
                            <div class="row justify-content-center">
                                <div class="col-lg-6 col-sm-12">
                                    <div class="d-flex justify-content-center mt-5">
                                        <input type="submit" class="btn btn-warning mx-1 text-black-50" value="Iniciar Sesion" />
                                        <button type="button" onclick="location.href = 'registro.jsp'" class="btn btn-warning mx-1 text-black-50"">
                                            Registrarse
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script src="lib/jquery/dist/jquery.min.js" type="text/javascript"></script>
        <script type="text/javascript" charset="utf8" src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
        <script src="lib/bootstrap/dist/js/bootstrap.js" type="text/javascript"></script>
        <script src="lib/bootstrap/dist/js/bootstrap.bundle.js" type="text/javascript"></script>
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script>
        <script src="lib/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>
        <script src="js/scriptVeterinaria.js" type="text/javascript"></script>
        <script>
                                            $(document).ready(function () {

                                                $("#form1").validate({

                                                    rules: {
                                                        txtusuario: {required: true},
                                                        txtcontrasena: {required: true}
                                                    },

                                                    messages: {
                                                        txtusuario: "usuario requerido",
                                                        txtcontrasena: "contraseña requerida"
                                                    },

                                                    errorElement: 'span'
                                                });

                                            });
        </script>
    </body>
</html>

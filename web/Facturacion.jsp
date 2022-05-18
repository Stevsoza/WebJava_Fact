<%-- 
    Document   : Facturacion
    Created on : 5/05/2022, 05:24:11 PM
    Author     : Steven
--%>

<%@page import="Capa_logicaNegocio.*"%>
<%@page import="Capa_Entidades.*"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="CSS/estilos.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css" />
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/2.0.1/css/buttons.dataTables.min.css" />
        <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/styles.css" rel="stylesheet" type="text/css"/>                             
        <title>Facturación</title>
    </head>
    <body class="imagenFondo vh-100 position-relative">
        <%

            //cargamos la session si la session existe se crea el vendedor con los datos
            //que la session trae sino nos manda a hacer login
            HttpSession actualSession = null;
            if (request.getSession(false) != null) {
                actualSession = request.getSession();

                Vendedor vendedor = new Vendedor();
                if (actualSession != null && !actualSession.isNew()) {
                    vendedor = (Vendedor) actualSession.getAttribute("vendedor");

                    String mensaje = "";

                    if (request.getParameter("msg") != null && !request.getParameter("msg").equals("")) {
                        mensaje = request.getParameter("msg");
                    }
                    //creamos variable que nos guardara el número de la factura
                    // total, el nombre del cliente, el id del cliente
                    // y Creamos una variable tipo cliente
                    // que nos guardara los datos del cliente
                    int nfactura = -1;
                    double total = 0;
                    String nombreCliente = "";
                    int idCliente = -1;
                    Cliente entidadCliente;
                    //creamos los accesos a la capa logica de negocio
                    //para la factura y los datalles
                    LnFacturaVenta lnVenta = new LnFacturaVenta();
                    LnDetalleVenta lnDetaV = new LnDetalleVenta();
                    LnCliente lnCliente = new LnCliente();
                    Factura_venta Fventa;
                    //vamos a crear el listado de detalles pero en null
                    List<Detalle_venta> detalles = null;
                    //creamos la fecha 
                    Date fecha = Date.valueOf(LocalDate.now());

                    //verificamos si se nos esta enviando por parametro el número de factura
                    if (request.getParameter("nFactura") != null
                            && !request.getParameter("nFactura").equals("")) {
                        //si nos envian la factura por parametro vamos a obtener la factura
                        //y sus detalles
                        //si nos están enviando la factura significará que
                        //es porque se va a editar la factura

                        nfactura = Integer.parseInt(request.getParameter("nFactura"));
                        String condicion = String.format("id_venta = %d", nfactura);
                        Fventa = lnVenta.ObtenerRegistro(condicion);
                        fecha = new Date(Fventa.getFecha().getTime());
                        detalles = lnDetaV.ListarRegistros(condicion);
                        if (Fventa.getId_cliente() != 0) {
                            //esto sucede en el caso que sea una factura a la que no se le haya
                            //asignado cliente
                            idCliente = Fventa.getId_cliente();
                            entidadCliente = lnCliente.ObtenerRegistro("id_cliente=" + idCliente);
                            nombreCliente = entidadCliente.getNombre() + " " + entidadCliente.getApellido1();
                        }
                        //si no nos envian la factura por parametro se creara una-
                        //factura nueva
                    } else {
                        Fventa = new Factura_venta();
                        //se inserta la factura en la base de datos
                        nfactura = lnVenta.Insertar(Fventa);
                    }
                    //ahora revisaremos que nos hayan enviado el cliente por parametro
                    //para asi cargarlo y cargar los animales de su propiedad
                    if (request.getParameter("idCliente") != null
                            && !request.getParameter("idCliente").equals("") && idCliente != 0) {
                        //si esto sucede cargaremos los datos del cliente
                        //y sus mascotas o animales
                        //si nos están enviando el cliente significa que estamos apenas
                        //creando la factura y puede que el cliente cambie para esta factura
                        entidadCliente = lnCliente.ObtenerRegistro("id_cliente = "
                                + Integer.parseInt(request.getParameter("idCliente")));
                        idCliente = entidadCliente.getId_cliente();
                        nombreCliente = entidadCliente.getNombre() + " " + entidadCliente.getApellido1();
                    }
        %>

        <!--offcanvas de vendedor-->
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
        <!--fin de offcanvas de vendedor-->    


        <header class="container">
            <div class="row justify-content-center mb-0 mt-2">
                <ul class="nav col-10 bg-secondary justify-content-center rounded-top fw-bolder">
                    <li class="nav-item rounded-top">
                        <a class="nav-link" href="index.jsp">Inicio</a>
                    </li>
                    <li class="nav-item active bg-primary rounded-top">
                        <a class="nav-link text-white" href="Facturacion.jsp">Facturación</a>
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
        <div class="container mb-5">
            <div class="row justify-content-center text-white">
                <div class="col-5 bg-primary bg-opacity-75 rounded-start p-3">
                    <div class="form-group row">
                        <label for="nFactura" class="col-sm-3 col-form-label fw-bolder">no. Factura</label>
                        <div class="col-sm-4">
                            <input type="text" disabled readonly class="form-control disabled text-center" id="nFactura" value="<%=nfactura%>">
                        </div>

                    </div>
                    <div class="form-group row">
                        <label for="txtcliente" class="col-sm-3 col-form-label fw-bolder">Cliente</label>
                        <div class="col-sm-4">
                            <input type="hidden" id="txtCodCliente" class="invisible" readonly value="<%=idCliente%>"/>
                            <input type="text" placeholder="Seleccione Cliente" readonly class="form-control text-center" id="txtCliente" disabled="true" value="<%=nombreCliente%>"/>
                        </div>
                        <div class="col-sm-5">
                            <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#clienteS">
                                Agregar Cliente
                            </button>
                        </div>
                    </div>
                </div>
                <div class="col-5 bg-primary bg-opacity-75 rounded-end p-3">
                    <div class="form-group row">
                        <label for="fecha" class="col-sm-4 col-form-label fw-bolder">Fecha</label>
                        <div class="col-sm-8">
                            <input type="text" disabled readonly class="form-control disabled text-center" id="fecha" value="<%=fecha%>">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="tipo_fac" class="col-sm-4 col-form-label fw-bolder">Tipo Factura</label>
                        <div class="col-sm-8">
                            <%if (Fventa.getId_cliente() > 0) {
                            %>
                            <label class="form-control disabled text-center"><%=Fventa.getTipo()%></label>
                            <%} else {%>

                            <select class="form-select text-center" id="tipo_fac" aria-label="Default select example">
                                <option selected >Seleccione tipo</option>
                                <option value="contado">Contado</option>
                                <option value="credito">Credito</option>
                            </select> 
                            <%}%>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row justify-content-center text-black mt-2">
                <div class="col-10 p-4 rounded bg-secondary bg-opacity-75">
                    <form action="Facturar" method="post" id="Frm_detalle" class="pb-2">
                        <div class="row">
                            <h5 class="bg-primary p-3 rounded text-white">Selección de producto</h5>
                        </div>
                        <div class="row">
                            <div class="col-6 mx-auto">
                                <input type="hidden" id="txtidvendedor" name="txtidvendedor" value="<%=vendedor.getId_vendedor()%>"/>
                                <input type="hidden" id="n_factura" name="n_factura" value=""/>
                                <input type="hidden" id="t_factura" name="t_factura" value=""/>
                                <input type="hidden" id="f_cliente" name="f_cliente" value="<%=idCliente%>" />
                                <input type="hidden" id="txtidproducto" name="txtidproducto" value=""/>
                                <label for="txtdescripcion" class="form-label ms-3">Descripción</label>
                                <div class="form-group d-flex">
                                    <a id="btnBuscarP" class="btn btn-primary ms-3" data-bs-toggle="modal" data-bs-target="#productos">
                                        <i class="fas fa-search" style="color: #ffffff"></i></a>
                                    <input type="text" id="txtdescripcion" name="txtdescripcion" value="" class="form-control" readonly="true"
                                           placeholder="Seleccione un producto" required/>
                                </div>

                                <input type="hidden" readonly id="txtcategoria" name="txtcategoria" class="form-control" />

                            </div>
                        </div>
                        <div class="row mx-auto mt-2 mb-5">
                            <div class="col-12 d-flex justify-content-around ">
                                <div class="form-group mx-2">
                                    <label for="txtcantidad" class="form-label m-2">Cantidad</label>
                                    <input disabled required type="number" id="txtcantidad" name="txtcantidad" value="" class="form-control"
                                           placeholder="Cantidad" />
                                </div>
                                <div class="form-group mx-2">
                                    <label for="txtprecio" class="form-label m-2">precio</label>
                                    <input type="number" id="txtprecio" name="txtprecio" value="" class="form-control"
                                           placeholder="Precio" readonly="true"/>
                                </div>

                                <div class="form-group mx-2">
                                    <label for="txtdescuento" class="form-label m-2">Descuento</label>
                                    <input type="number" id="txtdescuento" name="txtdescuento" value="" class="form-control"
                                           placeholder="Descuento" readonly="true"/>
                                </div>
                                <div class="form-group mx-2">
                                    <label for="txtexistencia" class="form-label m-2">Existencia</label>
                                    <input type="number" id="txtexistencia" name="txtexistencia" value="" class="form-control"
                                           placeholder="Existencia" readonly="true"/>
                                </div>
                            </div>
                        </div>
                        <!--<div class="form-group row invisible">
                            <div class="col-6 d-flex flex-column align-items-end">
                                <label for="cbxVeterinario" class="col-form-label">Veterinario</label>
                                <select class="form-select w-50 text-black-50" id="cbxVeterinario" disabled aria-label="form veterinario">
                                    <option selected>Seleccione veterinario</option>
                        <%

                            LnVeterinario logicVet = new LnVeterinario();
                            List<Veterinario> listaVeterinarios = null;
                            listaVeterinarios = logicVet.ListarRegistros("");
                            String registroVeterinario;
                            if (listaVeterinarios != null) {
                                for (Veterinario registro : listaVeterinarios) {
                                    registroVeterinario = registro.getNombre() + " " + registro.getApellido1();
                        %>

                        <option value="<%=registro.getId_veterinario()%>">
                        <%=registroVeterinario%>
                    </option>     

                        <%}
                            }
                        %>
                    </select>
                </div>
                <div class="col-6">
                    <label for="cbxAnimal" class=" col-form-label">Animal</label>
                    <select disabled class="form-select w-50 text-black-50" id="cbxAnimal" aria-label="form animal">
                        <option selected>Seleccione Animal</option>
                        <option value="1">Contado</option>
                        <option value="2">Credito</option>
                    </select>
                </div>
            </div> -->
                        <div class="form-group">
                            <a onclick="revisar();" class="btn btn-primary text-white">Agregar y guardar</a>
                            <button id="btnGuardar" class="invisible"></button>
                        </div>
                    </form>
                </div>
            </div>
            <!--creacion de tabla detalles -->
            <div class="row justify-content-center">
                <div class="col-lg-10 bg-secondary bg-opacity-75 rounded m-lg-3 m-sm-1 p-lg-3 p-sm-1">
                    <table id="detalles" class="table table-hover text-black-50 fw-bolder">
                        <div class="row p-2">
                            <h5 class="bg-primary p-3 rounded text-white">Detalles de Factura</h5>
                        </div>
                        <thead>
                            <tr>
                                <td>Código</td>
                                <td>Descripción</td>
                                <td>Veterinario</td>
                                <td>Animal</td>
                                <td>Cantidad</td>
                                <td>Precio</td>
                                <td>Descuento</td>
                                <td>Total</td>
                                <td>opciones</td>
                            </tr>
                        </thead>
                        <tbody><%
                            double Subtotal = 0;
                            //creamos la tabla de detalles
                            //en caso de que esta factura los tenga
                            if (detalles != null) {
                                for (Detalle_venta dVenta : detalles) {%>
                            <tr id="filaDetalle">
                                <%int numventa = dVenta.getId_venta();
                                    int codigoP = dVenta.getId_producto();
                                    String descripcion = new String(dVenta.getDescripcion());
                                    //obtenemos los ids de veterinario y animal
                                    //para cargar sus datos
                                    int codigoVet = dVenta.getId_veterinario();
                                    int codigoAni = dVenta.getId_animal();
                                    int cantidadD = dVenta.getCantidad();
                                    double precio = dVenta.getPrecio();
                                    double descuento = dVenta.getDescuento();
                                    double totalD = dVenta.getTotal();
                                    Subtotal += totalD;
                                    LnAnimal logicAni = new LnAnimal();
                                    String alAnimal;
                                    String nombreVeterinario;
                                    Veterinario veterinario = logicVet.ObtenerRegistro("id_veterinario=" + codigoVet);
                                    Animal animal = logicAni.ObtenerRegistro("id_animal=" + codigoAni);
                                    alAnimal = animal.getAlias();
                                    nombreVeterinario = veterinario.getNombre() + " " + veterinario.getApellido1();
                                %>

                                <td><%=codigoP%></td>
                                <td><%=descripcion%></td>
                                <td><%=nombreVeterinario%></td>
                                <td><%=alAnimal%></td>
                                <td><%=cantidadD%></td>
                                <td><%=precio%></td>
                                <td><%=descuento%></td>
                                <td><%=totalD%></td>
                                <td class="d-flex justify-content-around">
                                    <a class="btn btn-dark" href="EliminarDetalle?idProducto=<%=codigoP%>&idVenta=<%=numventa%>">
                                        <i class="fas fa-trash-alt" style="color: whitesmoke"></i></a>
                                    <a class="btn btn-dark" data-bs-toggle="tooltip" data-bs-placement="top" title="Quitar 1" href="reducirDetalle?idProducto=<%=codigoP%>&idVenta=<%=numventa%>">
                                        <i class="fas fa-minus-circle" style="color: whitesmoke"></i></a>
                                </td>
                            </tr>
                            <%}
                            } else {%>
                            <tr>
                        <h3 class="p-2">Aqui se mostrarán los detalles de la factura, agregue un producto</h3>
                        </tr>   
                        <%}%>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-10 mb-3 px-0">
                    <div class="card bg-secondary">
                        <div class="card-header fw-bolder bg-primary text-white">Resumen</div>
                        <div class="card-body">
                            <form class="d-flex justify-content-center" action="formalizar" id="datosformAlizar">
                                <div class="form-group m-2">
                                    <input type="hidden" id="nVenta" name="nVenta" value="<%=nfactura%>" />
                                    <label for="txtsubtotalF" class="form-label">Subtotal</label>
                                    <input id="txtsubtotalF" readonly="" name="txtsubtotalF" class="form-control text-end" type="text" value="<%=Subtotal%>"/>
                                </div>
                                <div class="form-group m-2">
                                    <label for="sledescuento" class="form-label">Descuento</label>
                                    <select class="form-select form-control text-black" id="sledescuento">
                                        <option selected value="0">0%</option>
                                        <option value="0.05">5%</option>
                                        <option value="0.1">10%</option>
                                        <option value="0.15">15%</option>
                                        <option value="0.25">25%</option>
                                    </select>
                                </div>
                                <div class="form-group m-2">
                                    <label for="txtimpuestoF" class="form-label">Impuesto</label>
                                    <label id="txtimpuestoF" readonly="" name="txtimpuestoF" class="form-control text-end" type="text" value="0.13">13%</label>
                                </div>
                                <div class="form-group m-2">
                                    <label for="txtTotalF" class="form-label fw-bolder">Total</label>
                                    <input id="txtTotalF" readonly="" name="txtTotalF" class="form-control text-end" type="text" value=""/>
                                </div>
                                <input type="submit" hidden="" id="btnFacturaCancelar"/>
                            </form>
                        </div>
                    </div>  
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-10 d-flex justify-content-around">
                    <a class="btn btn-primary text-white fs-5 rounded px-4" onclick="cancelacion()">Formalizar</a>
                    <a class="btn btn-primary text-white fs-5 rounded px-5" href="index.jsp">Inicio</a>
                </div>
            </div>
        </div>
        <!--creacion de modal-->
        <div class="modal" id="clienteS" tabindex="-1" role="dialog" aria-labelledby="clientelabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="clientelabel">Seleccionar Cliente</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <table class="table" ID="TablaClientes">
                            <thead>
                                <tr>
                                    <th>Código</th>
                                    <th>Nombre</th>
                                    <th>Primer Apellido</th>
                                    <th>Email</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    LnCliente logica = new LnCliente();
                                    List<Cliente> lista;
                                    lista = logica.ListarRegistros("");
                                    for (Cliente registro : lista) {
                                %>
                                <tr>
                                    <%int codigo = registro.getId_cliente();%>

                                    <td><%=codigo%></td>
                                    <td><%=registro.getNombre()%></td>
                                    <td><%=registro.getApellido1()%></td>
                                    <td><%=registro.getEmail()%></td>
                                    <td><a href="Facturacion.jsp?nFactura=<%=nfactura%>&idCliente=<%=codigo%>" class="btn btn-primary">Seleccionar</td>
                                </tr> 
                                <%}%>
                            </tbody>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-warning" data-bs-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- fin modal Clientes -->
        <div class="modal" id="productos" tabindex="-1" role="dialog" aria-labelledby="productolabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="productolabel">Seleccionar producto</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <table class="table" ID="TablaProductos">
                            <thead>
                                <tr>
                                    <th>Código</th>
                                    <th>Descripcion</th>
                                    <th>Categoria</th>
                                    <th>Precio</th>
                                    <th>Descuento</th>
                                    <th>Existencia</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    LnProducto logicaProd = new LnProducto();
                                    List<Producto> listaProd;
                                    listaProd = logicaProd.ListarRegistros("");
                                    for (Producto registro : listaProd) {
                                %>
                                <tr>
                                    <%int codigo = registro.getId_producto();%>

                                    <td><%=codigo%></td>
                                    <td><%=registro.getDescripcion()%></td>
                                    <td><%=registro.getCategoria()%></td>
                                    <td><%=registro.getPrecio()%></td>
                                    <td><%=registro.getDescuento()%></td>
                                    <td><%=registro.getExistencia()%></td>
                                    <td><a href="#" data-bs-dismiss="modal" onclick="SeleccionarProducto('<%=codigo%>',
                                                    '<%=registro.getDescripcion()%> ', '<%=registro.getCategoria()%>', '<%=registro.getPrecio()%>',
                                                    '<%=registro.getDescuento()%>', '<%=registro.getExistencia()%>');"
                                           class="btn btn-primary">Seleccionar</td>
                                </tr> 
                                <%}%>
                            </tbody>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- fin modal productos-->

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
        <%
                } else {
                    response.sendRedirect("login.jsp");
                }
            }
        %>
    </body>
</html>

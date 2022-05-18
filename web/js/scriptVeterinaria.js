/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */
$(document).ready(function () {

    sacarTotal();

    $('#TablaClientes').DataTable({
        "lengthMenu": [[5, 15, 15, -1], [5, 10, 15, "All"]],
        "language": {
            "info": "Página _PAGE_ de _PAGES_",
            "infoEmpty": "No existen Registros disponibles",
            "zeroRecords": "No se encuentran Registros",
            "search": "Buscar ",
            "infoFiltered": "",
            "lengthMenu": "Mostrar  _MENU_  Registros",
            "paginate": {
                "first": "Primero",
                "last": "Último",
                "next": "Siguiente",
                "previous": "Anterior"
            }
        }
    });

    $('#TablaProductos').DataTable({
        "lengthMenu": [[5, 15, 15, -1], [5, 10, 15, "All"]],
        "language": {
            "info": "Página _PAGE_ de _PAGES_",
            "infoEmpty": "No existen Registros disponibles",
            "zeroRecords": "No se encuentran Registros",
            "search": "Buscar ",
            "infoFiltered": "",
            "lengthMenu": "Mostrar  _MENU_  Registros",
            "paginate": {

                "first": "Primero",
                "last": "Último",
                "next": "Siguiente",
                "previous": "Anterior"
            }
        }
    });
    
    
     $('#tblClientesLista').DataTable({
        "lengthMenu": [[5, 15, 15, -1], [5, 10, 15, "All"]],
        "language": {
            "info": "Página _PAGE_ de _PAGES_",
            "infoEmpty": "No existen Registros disponibles",
            "zeroRecords": "No se encuentran Registros",
            "search": "Buscar ",
            "infoFiltered": "",
            "lengthMenu": "Mostrar  _MENU_  Registros",
            "paginate": {
                "first": "Primero",
                "last": "Último",
                "next": "Siguiente",
                "previous": "Anterior"
            }
        }
    });
    
     $('#tblProductosLista').DataTable({
        "lengthMenu": [[5, 15, 15, -1], [5, 10, 15, "All"]],
        "language": {
            "info": "Página _PAGE_ de _PAGES_",
            "infoEmpty": "No existen Registros disponibles",
            "zeroRecords": "No se encuentran Registros",
            "search": "Buscar ",
            "infoFiltered": "",
            "lengthMenu": "Mostrar  _MENU_  Registros",
            "paginate": {
                "first": "Primero",
                "last": "Último",
                "next": "Siguiente",
                "previous": "Anterior"
            }
        }
    });

    $("#Frm_detalle").validate({
        messages: {
            txtdescripcion: "campo requerido",
            txtcantidad: "campo requerido"
        }
    });

    $("ul li").hover(function () {
        if (!$(this).hasClass("active")) {
            $(this).addClass("bg-success");
            $(this).children().addClass("text-white");
        }

    }, function () {
        if (!$(this).hasClass("active")) {
            $(this).removeClass("bg-success");
            $(this).children().removeClass("text-white");
        }
    });

    $("#sledescuento").change(function () {
        $("#sledescuento option:selected").each(function () {
            sacarTotal();
        });

    });
    
    setTimeout(function() {
        var msg = $("#msgFromServer").text();
        if(msg !== "" && msg !== null){
            $("#btnForMsg").trigger('click');
        }
    },100);


});

function SeleccionarCliente(idCliente, nombreCliente) {

    $("#txtCodCliente").val(idCliente);
    $("#txtCliente").val(nombreCliente);
}

function SeleccionarProducto(idproducto, descripcion, categoria, precio, descuento, existencia) {
    $("#txtidproducto").val(idproducto);
    $("#txtdescripcion").val(descripcion);
    $("#txtcategoria").val(categoria);
    $("#txtprecio").val(precio);
    $("#txtdescuento").val(descuento);
    $("#txtexistencia").val(existencia);
    $("#txtcantidad").removeAttr("disabled");

    if (categoria === "servicio") {
        $("#cbxVeterinario").removeAttr("disabled");
    } else {
        $('#cbxVeterinario').prop('selectedIndex', 0);
        $("#cbxVeterinario").prop("disabled", true);
    }
}

function revisar() {
    var texto = $("#f_cliente").val();
    var cant = parseInt($("#txtcantidad").val());
    var exist = parseInt($("#txtexistencia").val());
    if (texto <= -1) {
        alert("Debe seleccionar cliente");
    } else {
        if (cant > exist) {
            alert("no hay suficiente cantidad de este producto");
        } else {
            if ($("#tipo_fac option:selected").index() === 0) {
                alert("debe seleccionar un tipo de factura");
            } else {
                $("#t_factura").val($("#tipo_fac").val());
                $("#n_factura").val($("#nFactura").val());
                $("#btnGuardar").trigger('click');
            }
        }
    }
}

function sacarTotal() {
    var subtotal = Number($("#txtsubtotalF").val());
    var impuesto = 0.13;
    var descuento = Number($("#sledescuento option:selected").val());
    var total;
    if (descuento > 0) {
        total = subtotal + (subtotal * impuesto);
        total = total - (total * descuento);
    } else {
        total = subtotal + (subtotal * impuesto);
    }

    $("#txtTotalF").val(total.toFixed(2));
}

function cancelacion(){
   if($("#filaDetalle").length){
        $("#btnFacturaCancelar").trigger('click');
   }else{
      alert("debe agregar al menos un producto");
   }
}





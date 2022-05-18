/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */


$(document).ready(function () {

    $("#form1").validate({

        rules: {
            Usuario: {required: true, maxlength: 50},
            contrasena1: {required: true, maxlength: 20},
            contrasena2: {required: true, maxlength: 20},
            txtnombre: {required: true, maxlength: 20},
            txtapellido1: {required: true, maxlength: 20},
            txtapellido2: {required: true, maxlength: 20},
            txtemail: {required: true, maxlength: 50},
            txtTelefono: {required: true, maxlength: 12}
        },

        messages: {
            Usuario: "campo requerido, máximo 50 caracteres",
            contrasena1: "campo requerido, máximo 20 caracteres",
            contrasena2: "campo requerido, máximo 20 caracteres",
            txtnombre: "campo requerido, máximo 20 caracteres",
            txtapellido1: "campo requerido, máximo 20 caracteres",
            txtapellido2: "campo requerido, máximo 20 caracteres",
            txtemail: "campo requerido, máximo 50 caracteres",
            txtTelefono: "campo requerido, máximo 15 caracteres",
        },

        errorElement: 'span'
    });

});



function verificarDatos() {

    var contrasena1 = $("#contrasena1").val();
    var contrasena2 = $("#contrasena2").val();

    if (contrasena1 === contrasena2) {
        $("#formButton").trigger('click');
    } else {
        alert("la contraseña debe coincidir");
    }
}
package com.zevallos.login_registro.entidad

data class Usuario (
    var id:Int,
    var usuario:String,
    var correo:String,
    var pwd:String,
    var nombre:String,
    var apellido:String,
    var swt:Int
)
data class ResponseUsuario(
    var id:Int,
    var usuario:String,
    var nombre:String,
    var apellido:String,
    var correo:String
)
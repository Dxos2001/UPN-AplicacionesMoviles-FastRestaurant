package com.zevallos.login_registro.entidad

data class Restaurante (
    var id:Int?,
    var nombre:String?,
    var descripcion:String?,
    var direccion:String?,
    var telefono:String?,
    var imagen:String?
)
data class ParamGetListRestaurante(
    var id:Int?,
    var nombre:String?
)

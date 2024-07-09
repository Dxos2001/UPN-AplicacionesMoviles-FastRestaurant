package com.zevallos.login_registro.entidad

data class Plato (
    var id:Int?,
    var idRestaurante:Int?,
    var nombre:String?,
    var descripcion:String?,
    var precio:Double?
)

data class  ParamGetListPlato(
    var id:Int?,
    var nombre:String?,
)

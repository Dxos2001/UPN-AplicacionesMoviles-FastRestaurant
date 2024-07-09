package com.zevallos.login_registro.entidad

import java.util.Calendar
import java.util.Date

data class Comentario(
    var id:Int?,
    var fecha: Date? = Calendar.getInstance().time,
    var idUsuario:Int?,
    var valoracion:Int?,
    var comentario:String?,
    var idRestaurante:Int?,
    var idPlato:Int?
)

data class  ParamGetListComentario(
    var fdesde:Date?,
    var fhasta:Date?,
    var idUsuario:Int?,
    var idRestaurante:Int?,
    var idPlato:Int?
)

data class  ParamPostComentario(
    var id:Int?,
    var idUsuario:Int?,
    var valoracion:Int?,
    var comentario:String?,
    var idRestaurante:Int?,
    var idPlato:Int?
)

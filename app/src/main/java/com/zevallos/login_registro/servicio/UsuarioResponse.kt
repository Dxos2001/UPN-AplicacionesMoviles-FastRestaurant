package com.zevallos.login_registro.servicio

import com.google.gson.annotations.SerializedName
import com.zevallos.login_registro.entidad.Comentario
import com.zevallos.login_registro.entidad.Plato
import com.zevallos.login_registro.entidad.ResponseUsuario
import com.zevallos.login_registro.entidad.Restaurante
import com.zevallos.login_registro.entidad.Usuario
import okhttp3.Response

data class UsuarioResponse (
    @SerializedName("listaUsuarios") var listaUsuarios:ArrayList<Usuario>
)
data class LoginResponse(
    @SerializedName("lista") var login:ArrayList<ResponseUsuario>
)
data class RestauranteResponse(
    @SerializedName("items") var listaRestaurante:ArrayList<Restaurante>
)

data class PlatoResponse(
    @SerializedName("items") var listaPlatos:ArrayList<Plato>
)

data class ComentarioResponse(
    @SerializedName("items") var listaComentarios:ArrayList<Comentario>
)
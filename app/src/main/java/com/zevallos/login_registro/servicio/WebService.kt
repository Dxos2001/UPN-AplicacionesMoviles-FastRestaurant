package com.zevallos.login_registro.servicio

import com.google.gson.GsonBuilder
import com.zevallos.login_registro.entidad.Comentario
import com.zevallos.login_registro.entidad.ParamGetListComentario
import com.zevallos.login_registro.entidad.ParamGetListPlato
import com.zevallos.login_registro.entidad.ParamGetListRestaurante
import com.zevallos.login_registro.entidad.ParamPostComentario
import com.zevallos.login_registro.entidad.ResponseUsuario
import com.zevallos.login_registro.entidad.Restaurante
import com.zevallos.login_registro.entidad.Usuario
import com.zevallos.login_registro.entidad.VerificarLogin
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

object AppConstantes{
    const val BASE_URL = "http://18.226.165.232:7011/Restaurant/"
}

interface WebService {

    //Login
    @POST("SaveUsuario")
    suspend fun agregarUsuario(@Body usuario: Usuario):Response<String>

    @POST("PostLogin")
    suspend fun loginUsuario(@Body verificarLogin: VerificarLogin): Response<LoginResponse>

    //Restaurante
    @POST("GetListRestaurante")
    suspend fun GetListRestaurante(@Body restaurante: ParamGetListRestaurante):Response<RestauranteResponse>

    //Plato
    @POST("GetListPlato")
    suspend fun GetListPlato(@Body plato: ParamGetListPlato):Response<PlatoResponse>

    //Comentario
    @POST("GetListComentario")
    suspend fun GetListComentario(@Body comentario: ParamGetListComentario):Response<ComentarioResponse>

    @POST("SaveComentario")
    suspend fun agregarComentario(@Body comentario: ParamPostComentario):Response<String>
}

object RetrofitClient{

    val webService: WebService by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstantes.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(WebService::class.java)
    }
}
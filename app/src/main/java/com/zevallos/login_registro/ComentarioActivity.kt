package com.zevallos.login_registro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zevallos.login_registro.Adapter.ComentarioAdapter
import com.zevallos.login_registro.Adapter.PlatosAdapter
import com.zevallos.login_registro.entidad.Comentario
import com.zevallos.login_registro.entidad.ParamGetListComentario
import com.zevallos.login_registro.entidad.ParamGetListPlato
import com.zevallos.login_registro.entidad.Plato
import com.zevallos.login_registro.entidad.TempStoragePlato
import com.zevallos.login_registro.servicio.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ComentarioActivity : AppCompatActivity() {

    private lateinit var lblNombrePlato:TextView
    private lateinit var rvComentarios:RecyclerView
    private lateinit var btnComentar:Button

    //private lateinit var FilaPlato:RecyclerView
    private var adapter: ComentarioAdapter = ComentarioAdapter()
    private var listaComentarios: ArrayList<Comentario> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comentario)
        asignarReferencias()
        cargarDatos()
    }

    fun asignarReferencias(){
        lblNombrePlato = findViewById(R.id.lblNombrePlato)
        rvComentarios = findViewById(R.id.rvComentarios)
        btnComentar = findViewById(R.id.btnComentar)
        lblNombrePlato.text = TempStoragePlato.Nombre.toString()
        rvComentarios.layoutManager = LinearLayoutManager(this)

        btnComentar.setOnClickListener {
            val intent = Intent(this@ComentarioActivity, hacer_comentaria::class.java)
            startActivity(intent)
        }
    }

    private fun cargarDatos(){
        CoroutineScope(Dispatchers.IO).launch {
            var paramComentario= ParamGetListComentario(null,null, null, TempStoragePlato.idRestaurante, TempStoragePlato.idPlato)
            val call = RetrofitClient.webService. GetListComentario(paramComentario)
            runOnUiThread {
                if(call.isSuccessful){
                    Log.d("listaComentario",call.body().toString())
                    listaComentarios = call.body()!!.listaComentarios
                    adapter.agregarComentarios(listaComentarios)
                    mostrar()
                }else{
                    Toast.makeText(this@ComentarioActivity,"Error al consultar servicio", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun mostrar(){
        rvComentarios.adapter = adapter
    }
}
package com.zevallos.login_registro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zevallos.login_registro.Adapter.RestauranteAdapter
import com.zevallos.login_registro.entidad.ParamGetListRestaurante
import com.zevallos.login_registro.entidad.Restaurante
import com.zevallos.login_registro.servicio.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RestaurantesActivity : AppCompatActivity() {
    private lateinit var rvFilaRestaurante:RecyclerView
    private var adapter:RestauranteAdapter = RestauranteAdapter()
    private var listaRestaurante:ArrayList<Restaurante> = ArrayList()
    private lateinit var btnPlato:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurantes)
        AsignarReferencias()
        cargarDatos()
    }

    private fun AsignarReferencias(){
        btnPlato = findViewById(R.id.btnPlato)
        rvFilaRestaurante = findViewById(R.id.rvFilaRestaurante)
        rvFilaRestaurante.layoutManager = LinearLayoutManager(this)

        btnPlato.setOnClickListener {
            val intent = Intent(this, fila_platos::class.java)
            startActivity(intent)
        }
    }
    private fun cargarDatos(){
        CoroutineScope(Dispatchers.IO).launch {
            var paramRestaurante = ParamGetListRestaurante(null,null)
            val call = RetrofitClient.webService.GetListRestaurante(paramRestaurante)
            runOnUiThread {
                if(call.isSuccessful){
                    Log.d("listaRestaurante",call.body().toString())
                    listaRestaurante = call.body()!!.listaRestaurante
                    adapter.agregarDatos(listaRestaurante)
                    mostrar()
                }else{
                    Toast.makeText(this@RestaurantesActivity,"Error al consultar servicio", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun mostrar(){
        rvFilaRestaurante.adapter = adapter
    }

}
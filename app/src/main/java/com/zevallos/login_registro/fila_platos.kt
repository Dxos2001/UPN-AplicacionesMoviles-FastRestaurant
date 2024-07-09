package com.zevallos.login_registro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zevallos.login_registro.Adapter.PlatosAdapter
import com.zevallos.login_registro.entidad.ParamGetListPlato
import com.zevallos.login_registro.entidad.ParamGetListRestaurante
import com.zevallos.login_registro.entidad.Plato
import com.zevallos.login_registro.servicio.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class fila_platos : AppCompatActivity() {

    private lateinit var FilaPlato:RecyclerView
    private var adapter:PlatosAdapter = PlatosAdapter()
    private var listaPlatos: ArrayList<Plato> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fila_platos)
        AsignarReferencias()
        cargarDatos()
    }

    private fun AsignarReferencias(){
        FilaPlato = findViewById(R.id.filaPlatos)
        FilaPlato.layoutManager = LinearLayoutManager(this)

        adapter.contexto(this)
    }

    private fun cargarDatos(){
        CoroutineScope(Dispatchers.IO).launch {
            var paramPlato= ParamGetListPlato(null,null)
            val call = RetrofitClient.webService.GetListPlato(paramPlato)
            runOnUiThread {
                if(call.isSuccessful){
                    Log.d("listaPlato",call.body().toString())
                    listaPlatos = call.body()!!.listaPlatos
                    adapter.agregarPlatos(listaPlatos)
                    mostrar()
                }else{
                    Toast.makeText(this@fila_platos,"Error al consultar servicio", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun mostrar(){
        FilaPlato.adapter = adapter
    }

}
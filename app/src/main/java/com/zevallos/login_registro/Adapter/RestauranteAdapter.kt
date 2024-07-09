package com.zevallos.login_registro.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zevallos.login_registro.R
import com.zevallos.login_registro.entidad.Restaurante

class RestauranteAdapter:RecyclerView.Adapter<RestauranteAdapter.MiViewHolder>()  {

    private var listaRestaurante:ArrayList<Restaurante> = ArrayList()
    fun agregarDatos(items:ArrayList<Restaurante>){
        this.listaRestaurante = items
    }
    class MiViewHolder(var view: View): RecyclerView.ViewHolder(view) {
        var fNombreRestaurante = view.findViewById<TextView>(R.id.fNombreRestaurante)


        fun setValores(restaurante: Restaurante ){
            fNombreRestaurante.text = restaurante.nombre
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MiViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.fila_restaurante,parent, false)
    )

    override fun onBindViewHolder(holder: RestauranteAdapter.MiViewHolder, position: Int) {
        val usuarioItem = listaRestaurante[position]
        holder.setValores(usuarioItem)
    }

    override fun getItemCount(): Int {
        return listaRestaurante.size
    }

}
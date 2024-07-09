package com.zevallos.login_registro.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zevallos.login_registro.ComentarioActivity
import com.zevallos.login_registro.R
import com.zevallos.login_registro.entidad.Comentario
import com.zevallos.login_registro.entidad.Plato

class ComentarioAdapter: RecyclerView.Adapter<ComentarioAdapter.MiViewHolder>() {

    private var listaComentarios: ArrayList<Comentario> = ArrayList()

    fun agregarComentarios(items: ArrayList<Comentario>) {
        this.listaComentarios = items
    }

    class MiViewHolder(var view: View) : RecyclerView.ViewHolder(view){
        var comUsuario = view.findViewById<TextView>(R.id.comUsuario)
        var comComentario = view.findViewById<TextView>(R.id.comComentario)


        fun setValor(comentario: Comentario) {
            comComentario.text = comentario.comentario.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComentarioAdapter.MiViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fila_comentario, parent, false)
        return ComentarioAdapter.MiViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaComentarios.size
    }

    override fun onBindViewHolder(holder: ComentarioAdapter.MiViewHolder, position: Int) {
        val comItem = listaComentarios[position]
        holder.setValor(comItem)
    }
}
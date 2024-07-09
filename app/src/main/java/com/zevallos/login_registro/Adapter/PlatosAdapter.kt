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
import com.zevallos.login_registro.entidad.Plato
import com.zevallos.login_registro.entidad.TempStoragePlato


class PlatosAdapter: RecyclerView.Adapter<PlatosAdapter.MiViewHolder>() {

    private var listaPlatos: ArrayList<Plato> = ArrayList()
    private lateinit var context:Context

    fun agregarPlatos(items: ArrayList<Plato>) {
        this.listaPlatos = items
    }

    fun contexto(context:Context){
        this.context=context
    }

    class MiViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
            var platonombre = view.findViewById<TextView>(R.id.platoNombre)
            var platodescrip = view.findViewById<TextView>(R.id.platoDescrip)
            var platoprecio = view.findViewById<TextView>(R.id.platoPrecio)
            var verComentarios = view.findViewById<Button>(R.id.verComentarios)


            fun setValor(plato: Plato) {
                platonombre.text = plato.nombre
                platodescrip.text = plato.descripcion
                platoprecio.text = plato.precio.toString()
                TempStoragePlato.idPlato = plato.id
                TempStoragePlato.idRestaurante = plato.idRestaurante
                TempStoragePlato.Nombre = plato.nombre.toString()

            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.platos, parent, false)
        return MiViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaPlatos.size
    }

    override fun onBindViewHolder(holder: MiViewHolder, position: Int) {
        val platoItem = listaPlatos[position]
        holder.setValor(platoItem)

        holder.verComentarios.setOnClickListener {
            val intent = Intent(context,ComentarioActivity::class.java)
            intent.putExtra("id",listaPlatos[position].id)
            intent.putExtra("nombre",listaPlatos[position].nombre)

            TempStoragePlato.idPlato = listaPlatos[position].id
            TempStoragePlato.idRestaurante = listaPlatos[position].idRestaurante
            TempStoragePlato.Nombre = listaPlatos[position].nombre.toString()


            context.startActivity(intent)
        }
    }
}
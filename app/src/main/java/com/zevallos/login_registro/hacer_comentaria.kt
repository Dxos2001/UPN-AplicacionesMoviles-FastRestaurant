package com.zevallos.login_registro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.zevallos.login_registro.entidad.Comentario
import com.zevallos.login_registro.entidad.ParamPostComentario
import com.zevallos.login_registro.entidad.TempStoragePlato
import com.zevallos.login_registro.entidad.TempStorageUsuario
import com.zevallos.login_registro.servicio.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class hacer_comentaria : AppCompatActivity() {

    private lateinit var comUsuario:TextView
    private lateinit var comComentario:EditText
    private lateinit var guardarComentario:Button
    lateinit var idUsuario:String
    lateinit var Usuario:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hacer_comentaria)
        asignarReferencias()
    }

    private fun asignarReferencias(){
        comUsuario = findViewById(R.id.comUsuario)
        comComentario = findViewById(R.id.comComentario)
        guardarComentario = findViewById(R.id.guardarComentario)
        comUsuario.text = TempStorageUsuario.usuario

        guardarComentario.setOnClickListener {
            registrarComentario()

            val intent = Intent(this@hacer_comentaria, ComentarioActivity::class.java)
            startActivity(intent)
        }
    }

    private fun registrarComentario() {
        val usuario = TempStorageUsuario.usuario
        val idUsuario = TempStorageUsuario.idUsuario
        val idPlato = TempStoragePlato.idPlato
        val idRestaurante = TempStoragePlato.idPlato
        val comentario = comComentario.text.toString()
        val valoracion = 3


        // Obtener la fecha y hora actual
        val currentDate = Calendar.getInstance().time

        if (comentario.isEmpty()){
            Toast.makeText(this, "Por favor, haga un comentario para guardar", Toast.LENGTH_SHORT).show()
            return
        }

        val nuevoComentario = ParamPostComentario(0, idUsuario, 5, comentario, idRestaurante, idPlato)

        try {
            CoroutineScope(Dispatchers.IO).launch {
                var rpta = RetrofitClient.webService.agregarComentario(nuevoComentario)
                runOnUiThread {
                    if(rpta.isSuccessful){
                        Toast.makeText(this@hacer_comentaria, "Comentario registrado exitosamente", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }catch (e:Exception){
            Toast.makeText(this@hacer_comentaria, "Comentario registrado exitosamente", Toast.LENGTH_SHORT).show()
        }

    }
}
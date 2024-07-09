package com.zevallos.login_registro

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.zevallos.login_registro.entidad.TempStorageUsuario
import com.zevallos.login_registro.entidad.Usuario
import com.zevallos.login_registro.entidad.VerificarLogin
import com.zevallos.login_registro.servicio.RetrofitClient
import com.zevallos.login_registro.hacer_comentaria
import com.zevallos.login_registro.servicio.WebService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.Console

class MainActivity : AppCompatActivity() {

    private lateinit var txtCorreo: EditText
    private lateinit var txtPassword: EditText
    private lateinit var btnIngresar: Button
    private lateinit var btnRegistrar: Button

    //private var verifica:Boolean = false
    //lateinit var usuarioActual: Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        asignarReferencias()
    }

    private fun asignarReferencias() {
        txtCorreo = findViewById(R.id.txtCorreo)
        txtPassword = findViewById(R.id.txtPassword)
        btnIngresar = findViewById(R.id.btnIngresar)
        btnRegistrar = findViewById(R.id.btnRegistrarse)

        btnIngresar.setOnClickListener {
            verificarUsuario()
        }

        btnRegistrar.setOnClickListener {
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
        }
    }

    private fun verificarUsuario(){
        val correo = txtCorreo.text.toString()
        val password = txtPassword.text.toString()

        if (correo.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Ingrese correo y contraseña", Toast.LENGTH_SHORT).show()
            return
        }

        val login = VerificarLogin(correo, password)

        CoroutineScope(Dispatchers.IO).launch {
            val rpta = RetrofitClient.webService.loginUsuario(login)
            runOnUiThread {
                if (rpta.isSuccessful) {
                    Toast.makeText(this@MainActivity, rpta.body().toString(), Toast.LENGTH_SHORT).show()
                    Log.d("Respuesta API", rpta.body()?.login?.map { it.usuario }.toString())


                    TempStorageUsuario.idUsuario = rpta.body()?.login?.map { it.id }.toString().replace("[","").replace("]","").toInt()
                    TempStorageUsuario.usuario = rpta.body()?.login?.map { it.usuario }.toString().replace("[","").replace("]","")

                    val intent = Intent(this@MainActivity, RestaurantesActivity::class.java)
                    startActivity(intent)
                } else {
                    // Credenciales incorrectas, mostrar mensaje de error
                    Toast.makeText(
                        this@MainActivity,
                        "Correo o contraseña incorrectos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun mostrarMensaje(mensaje:String){
        val ventana=AlertDialog.Builder(this)
        ventana.setTitle("Información")
        ventana.setMessage(mensaje)
        ventana.setPositiveButton("Aceptar",DialogInterface.OnClickListener{dialog,which->
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        })
        ventana.create().show()
    }

}


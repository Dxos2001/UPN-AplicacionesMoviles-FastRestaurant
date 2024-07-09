package com.zevallos.login_registro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.zevallos.login_registro.entidad.Usuario
import com.zevallos.login_registro.servicio.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Signup : AppCompatActivity() {

    private lateinit var sigNombre: EditText
    private lateinit var sigCorreo: EditText
    private lateinit var sigContra: EditText
    private lateinit var sigApell: EditText
    private lateinit var sigUsu:EditText
    private lateinit var btnGuardar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        asignarReferencias()
    }

    private fun asignarReferencias() {
        sigNombre = findViewById(R.id.SigNombres)
        sigApell = findViewById(R.id.SigApellidos)
        sigContra = findViewById(R.id.SigPassword)
        sigCorreo = findViewById(R.id.SigEmail)
        sigUsu = findViewById(R.id.sigUsuario)
        btnGuardar = findViewById(R.id.btnGuardar)

        btnGuardar.setOnClickListener {
            registrarUsuario()

            val intent = Intent(this@Signup, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun registrarUsuario() {
        val usuario = sigUsu.text.toString()
        val nombres = sigNombre.text.toString()
        val apellidos = sigApell.text.toString()
        val correo = sigCorreo.text.toString()
        val password = sigContra.text.toString()

        if (usuario.isEmpty() || nombres.isEmpty() || apellidos.isEmpty() || correo.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        val nuevoUsuario = Usuario(0, usuario, correo, password, nombres, apellidos,1)

        CoroutineScope(Dispatchers.IO).launch {
            var rpta = RetrofitClient.webService.agregarUsuario(nuevoUsuario)
            runOnUiThread {
                if(rpta.isSuccessful){
                    Toast.makeText(this@Signup, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

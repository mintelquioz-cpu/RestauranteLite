package com.miproyecto.restaurantelite

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etCorreo = findViewById<EditText>(R.id.etCorreo)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val usuario = etCorreo.text.toString().trim()
            val password = etPassword.text.toString()

            when {
                // ROL MESERO
                usuario == "mesero" && password == "1234" -> {
                    Toast.makeText(this, "Bienvenido Mesero", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MenuActivity::class.java))
                    finish()
                }
                // ROL COCINA
                usuario == "cocina" && password == "1234" -> {
                    Toast.makeText(this, "Panel de Cocina", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, CocinaActivity::class.java))
                    finish()
                }
                // ROL CAJERO
                usuario == "cajero" && password == "1234" -> {
                    Toast.makeText(this, "Módulo de Caja", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, CajeroActivity::class.java))
                    finish()
                }
                // ROL ADMIN (Activado)
                usuario == "admin" && password == "1234" -> {
                    Toast.makeText(this, "Panel Administrativo", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, AdminActivity::class.java)) // ¡Ahora funciona!
                    finish()
                }
                else -> {
                    Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
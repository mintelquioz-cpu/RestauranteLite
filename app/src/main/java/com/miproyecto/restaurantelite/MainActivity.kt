package com.miproyecto.restaurantelite // Tu nuevo package name

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Encontrar el botón por su ID (el que definimos en el XML)
        val btnIngresar = findViewById<Button>(R.id.btnIngresar)

        // 2. Configurar qué pasa al hacer clic
        btnIngresar.setOnClickListener {
            // Código para navegar a la siguiente pantalla
            val intent = android.content.Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
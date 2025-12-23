package com.miproyecto.restaurantelite

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        // Referencias
        val cardHamburguesa = findViewById<LinearLayout>(R.id.cardHamburguesa)
        val cardLimonada = findViewById<LinearLayout>(R.id.cardLimonada)
        val btnCerrar = findViewById<Button>(R.id.btnCerrarSesion)
        val btnVerCarrito = findViewById<Button>(R.id.btnVerCarrito) // Nuevo botón

        // Clic en Hamburguesa
        cardHamburguesa.setOnClickListener {
            irADetalle("Hamburguesa Élite", "Deliciosa carne 100% res con tocineta, queso y vegetales frescos.", "$ 25.000")
        }

        // Clic en Limonada
        cardLimonada.setOnClickListener {
            irADetalle("Limonada de Coco", "Refrescante bebida natural preparada al instante con crema de coco.", "$ 8.000")
        }

        // --- NUEVO: Clic en Ver Carrito ---
        btnVerCarrito.setOnClickListener {
            val intent = Intent(this, CarritoActivity::class.java)
            startActivity(intent)
        }

        // Botón cerrar sesión
        btnCerrar.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun irADetalle(nombre: String, descripcion: String, precio: String) {
        val intent = Intent(this, DetalleActivity::class.java)
        intent.putExtra("nombre", nombre)
        intent.putExtra("desc", descripcion)
        intent.putExtra("precio", precio)
        startActivity(intent)
    }
}
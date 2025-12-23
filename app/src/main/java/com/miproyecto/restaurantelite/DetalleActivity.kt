package com.miproyecto.restaurantelite

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DetalleActivity : AppCompatActivity() {

    // Variable para llevar la cuenta (Empieza en 1)
    private var cantidad = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        // 1. Encontrar los elementos
        val tvNombre = findViewById<TextView>(R.id.tvNombrePlato)
        val tvDesc = findViewById<TextView>(R.id.tvDescripcionPlato)
        val tvPrecio = findViewById<TextView>(R.id.tvPrecioPlato)
        val btnPedir = findViewById<Button>(R.id.btnPedir)
        val btnVolver = findViewById<Button>(R.id.btnVolver)

        // Elementos del selector de cantidad
        val btnMas = findViewById<Button>(R.id.btnMas)
        val btnMenos = findViewById<Button>(R.id.btnMenos)
        val tvCantidad = findViewById<TextView>(R.id.tvCantidad)

        // 2. Recibir datos
        val nombreRecibido = intent.getStringExtra("nombre") ?: "Sin Nombre"
        val descRecibida = intent.getStringExtra("desc") ?: "Sin Descripción"
        val precioRecibido = intent.getStringExtra("precio") ?: "$ 0"

        // 3. Mostrar datos
        tvNombre.text = nombreRecibido
        tvDesc.text = descRecibida
        tvPrecio.text = precioRecibido

        // 4. LÓGICA DEL CONTADOR
        // Botón Más (+)
        btnMas.setOnClickListener {
            cantidad++ // Aumenta en 1
            tvCantidad.text = cantidad.toString() // Muestra el nuevo número
        }

        // Botón Menos (-)
        btnMenos.setOnClickListener {
            if (cantidad > 1) { // Solo baja si es mayor a 1 (no queremos 0 ni negativos)
                cantidad--
                tvCantidad.text = cantidad.toString()
            }
        }

        // 5. LÓGICA DE GUARDADO (Ahora con bucle)
        btnPedir.setOnClickListener {
            // Creamos el objeto Plato
            val nuevoPlato = Plato(nombreRecibido, descRecibida, precioRecibido)

            // Repetimos la acción de agregar tantas veces como diga 'cantidad'
            repeat(cantidad) {
                Carrito.agregarPlato(nuevoPlato)
            }

            // Mensaje de confirmación
            val totalCarrito = Carrito.cantidad()
            Toast.makeText(this, "Agregaste $cantidad items. Total en carrito: $totalCarrito", Toast.LENGTH_SHORT).show()
        }

        // 6. Volver
        btnVolver.setOnClickListener {
            finish()
        }
    }
}
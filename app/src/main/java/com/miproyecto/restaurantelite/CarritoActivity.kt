package com.miproyecto.restaurantelite

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.util.Locale

class CarritoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)

        // Referencias a los elementos de la pantalla
        val tvLista = findViewById<TextView>(R.id.tvListaPedidos)
        val tvTotal = findViewById<TextView>(R.id.tvTotal)
        val btnFinalizar = findViewById<Button>(R.id.btnFinalizar)
        val btnVolver = findViewById<Button>(R.id.btnVolverMenu)

        // 1. GENERAR LA LISTA Y CALCULAR EL TOTAL
        val listaTexto = StringBuilder()
        var totalDinero = 0 // Variable para acumular el precio

        // Recorremos la lista de pedidos actual
        for (plato in Carrito.listaPedidos) {
            // A. Construimos el texto visual
            listaTexto.append("• ${plato.nombre}\n")
            listaTexto.append("   ${plato.precio}\n\n")

            // B. Calculamos el precio (Limpiamos el texto "$ 25.000" -> 25000)
            val precioLimpio = plato.precio.replace("[^0-9]".toRegex(), "")

            if (precioLimpio.isNotEmpty()) {
                totalDinero += precioLimpio.toInt()
            }
        }

        // 2. MOSTRAR EN PANTALLA
        if (Carrito.listaPedidos.isNotEmpty()) {
            tvLista.text = listaTexto.toString()

            // Formatear el total con puntos de mil (Ej: 50.000)
            val formatoMoneda = NumberFormat.getNumberInstance(Locale.GERMANY)
            val totalFormateado = formatoMoneda.format(totalDinero)

            tvTotal.text = "Total: $ $totalFormateado"
        } else {
            tvLista.text = "Tu carrito está vacío."
            tvTotal.text = "Total: $ 0"
        }

        // 3. BOTÓN CONFIRMAR PEDIDO
        btnFinalizar.setOnClickListener {
            if (Carrito.listaPedidos.isEmpty()) {
                Toast.makeText(this, "El carrito está vacío", Toast.LENGTH_SHORT).show()
            } else {
                // A. Enviamos los datos a la lista de Cocina
                // (Esta función mueve los datos y limpia el carrito del mesero)
                Carrito.enviarPedidoACocina()

                // B. Navegamos a la pantalla de éxito
                val intent = Intent(this, PedidoExitosoActivity::class.java)
                startActivity(intent)
                finish() // Cerramos la actividad del carrito
            }
        }

        // 4. BOTÓN VOLVER
        btnVolver.setOnClickListener {
            finish()
        }
    }
}
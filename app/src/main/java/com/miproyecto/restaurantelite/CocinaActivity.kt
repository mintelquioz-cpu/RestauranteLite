package com.miproyecto.restaurantelite

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CocinaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cocina)
        actualizarPantalla()
    }

    private fun actualizarPantalla() {
        val contenedor = findViewById<LinearLayout>(R.id.contenedorPedidos)

        // Limpiamos pantalla (dejando el título)
        if (contenedor.childCount > 1) contenedor.removeViews(1, contenedor.childCount - 1)

        if (Carrito.listaCocina.isEmpty()) {
            val aviso = TextView(this)
            aviso.text = "Todo limpio por aquí."
            aviso.textSize = 18f
            aviso.setPadding(0, 20, 0, 0)
            contenedor.addView(aviso)
        }

        for (item in Carrito.listaCocina) {
            // Tarjeta del pedido
            val card = LinearLayout(this)
            card.orientation = LinearLayout.VERTICAL
            card.setPadding(40, 40, 40, 40)
            card.setBackgroundColor(Color.WHITE)
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(0, 0, 0, 30)
            card.layoutParams = params

            // Nombre
            val tvNombre = TextView(this)
            tvNombre.text = item.nombre
            tvNombre.textSize = 22f
            tvNombre.setTypeface(null, android.graphics.Typeface.BOLD)
            card.addView(tvNombre)

            // Estado
            val tvEstado = TextView(this)
            tvEstado.text = item.estado
            tvEstado.textSize = 16f
            tvEstado.setPadding(0, 10, 0, 20)
            card.addView(tvEstado)

            // Botón de Acción
            val btnAccion = Button(this)
            when (item.estado) {
                "En Espera" -> {
                    btnAccion.text = "COCINAR"
                    btnAccion.setBackgroundColor(Color.parseColor("#FFC107")) // Amarillo
                    btnAccion.setOnClickListener {
                        item.estado = "Preparando..."
                        actualizarPantalla()
                    }
                }
                "Preparando..." -> {
                    btnAccion.text = "TERMINAR"
                    btnAccion.setBackgroundColor(Color.parseColor("#4CAF50")) // Verde
                    btnAccion.setOnClickListener {
                        item.estado = "Listo a Entregar"
                        actualizarPantalla()
                    }
                }
                "Listo a Entregar" -> {
                    btnAccion.text = "ENTREGAR A MESA"
                    btnAccion.setBackgroundColor(Color.parseColor("#2196F3")) // Azul
                    btnAccion.setOnClickListener {
                        // AQUÍ ESTÁ EL CAMBIO CLAVE:
                        Carrito.enviarACaja(item)
                        Toast.makeText(this, "Enviado a Caja para cobro", Toast.LENGTH_SHORT).show()
                        actualizarPantalla()
                    }
                }
            }
            card.addView(btnAccion)
            contenedor.addView(card)
        }

        // Botón Salir
        val btnSalir = Button(this)
        btnSalir.text = "Salir"
        btnSalir.setBackgroundColor(Color.RED)
        btnSalir.setTextColor(Color.WHITE)
        btnSalir.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        contenedor.addView(btnSalir)
    }
}
package com.miproyecto.restaurantelite

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CajeroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cajero)
        actualizarCaja()
    }

    private fun actualizarCaja() {
        val contenedor = findViewById<LinearLayout>(R.id.contenedorCaja)

        // Limpiar pantalla
        if (contenedor.childCount > 1) contenedor.removeViews(1, contenedor.childCount - 1)

        if (Carrito.listaPorCobrar.isEmpty()) {
            val aviso = TextView(this)
            aviso.text = "No hay mesas pendientes de cobro."
            aviso.textSize = 18f
            contenedor.addView(aviso)
        }

        // Generar lista
        for (item in Carrito.listaPorCobrar) {
            val card = LinearLayout(this)
            card.orientation = LinearLayout.VERTICAL
            card.setPadding(30, 30, 30, 30)
            card.setBackgroundColor(Color.WHITE)
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(0, 0, 0, 20)
            card.layoutParams = params

            // Nombre y Precio
            val tvInfo = TextView(this)
            tvInfo.text = "${item.nombre}\n${item.precio}"
            tvInfo.textSize = 20f
            tvInfo.setTextColor(Color.BLACK)
            card.addView(tvInfo)

            // Botón Cobrar
            val btnCobrar = Button(this)
            btnCobrar.text = "COBRAR"
            btnCobrar.setBackgroundColor(Color.parseColor("#009688")) // Verde azulado
            btnCobrar.setTextColor(Color.WHITE)

            btnCobrar.setOnClickListener {
                Carrito.cobrarItem(item) // Mágia: Mueve a Ventas y borra de aquí
                Toast.makeText(this, "¡Cobrado exitosamente!", Toast.LENGTH_SHORT).show()
                actualizarCaja() // Refrescar pantalla
            }
            card.addView(btnCobrar)

            contenedor.addView(card)
        }

        // Botón Salir
        val btnSalir = Button(this)
        btnSalir.text = "Cerrar Caja"
        btnSalir.setBackgroundColor(Color.RED)
        btnSalir.setTextColor(Color.WHITE)
        btnSalir.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        contenedor.addView(btnSalir)
    }
}
package com.miproyecto.restaurantelite

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.util.Locale

class AdminActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        val tvTotal = findViewById<TextView>(R.id.tvTotalVentas)
        val tvHistorial = findViewById<TextView>(R.id.tvHistorial)
        val btnSalir = findViewById<Button>(R.id.btnCerrarSesion)

        // 1. CALCULAR TOTALES
        var sumaTotal = 0
        val reporte = StringBuilder()

        if (Carrito.listaVentas.isEmpty()) {
            tvHistorial.text = "Aún no se han realizado cobros."
        } else {
            // Recorremos las ventas guardadas
            for (venta in Carrito.listaVentas) {
                sumaTotal += venta.precio

                // Formato de moneda para cada línea
                val formato = NumberFormat.getNumberInstance(Locale.GERMANY)
                val precioBonito = formato.format(venta.precio)

                reporte.append("✅ ${venta.nombre} - $ $precioBonito\n")
            }
            tvHistorial.text = reporte.toString()
        }

        // 2. MOSTRAR EL GRAN TOTAL
        val formatoTotal = NumberFormat.getNumberInstance(Locale.GERMANY)
        tvTotal.text = "$ ${formatoTotal.format(sumaTotal)}"

        // 3. SALIR
        btnSalir.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
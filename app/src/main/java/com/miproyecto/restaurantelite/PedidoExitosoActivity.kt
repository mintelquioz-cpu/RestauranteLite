package com.miproyecto.restaurantelite

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PedidoExitosoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedido_exitoso)

        val btnNuevo = findViewById<Button>(R.id.btnNuevoPedido)

        btnNuevo.setOnClickListener {
            // Volvemos al Menú Principal
            val intent = Intent(this, MenuActivity::class.java)
            // Estas "flags" o banderas sirven para borrar el historial de pantallas anteriores
            // Así el usuario no puede volver a la pantalla de "Pedido Exitoso" con el botón atrás
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}
package com.miproyecto.restaurantelite

// Plato del Menú
data class Plato(
    val nombre: String,
    val descripcion: String,
    val precio: String
)

// Item que viaja por Cocina y Caja (Ahora con PRECIO)
data class ItemCocina(
    val nombre: String,
    val precio: String, // ¡Nuevo! Necesitamos el precio para cobrar después
    var estado: String = "En Espera"
)

// Item de Venta Finalizada (Para el Admin)
data class Venta(
    val nombre: String,
    val precio: Int,
    val hora: Long = System.currentTimeMillis()
)

object Carrito {
    val listaPedidos = mutableListOf<Plato>()      // Carrito (Mesero)
    val listaCocina = mutableListOf<ItemCocina>()  // Cocina
    val listaPorCobrar = mutableListOf<ItemCocina>() // Caja (Nuevo)
    val listaVentas = mutableListOf<Venta>()       // Historial Admin (Nuevo)

    fun agregarPlato(plato: Plato) {
        listaPedidos.add(plato)
    }

    fun cantidad(): Int = listaPedidos.size

    // Mesero -> Cocina
    fun enviarPedidoACocina() {
        for (plato in listaPedidos) {
            // Ahora pasamos también el precio
            val itemNuevo = ItemCocina(plato.nombre, plato.precio)
            listaCocina.add(itemNuevo)
        }
        listaPedidos.clear()
    }

    // Cocina -> Caja
    fun enviarACaja(item: ItemCocina) {
        listaCocina.remove(item)
        item.estado = "Por Cobrar"
        listaPorCobrar.add(item)
    }

    // Caja -> Ventas (Dinero real)
    fun cobrarItem(item: ItemCocina) {
        listaPorCobrar.remove(item)

        // Limpiamos el precio ($ 25.000 -> 25000) para guardarlo como número
        val precioLimpio = item.precio.replace("[^0-9]".toRegex(), "").toIntOrNull() ?: 0

        // Guardamos en el historial
        listaVentas.add(Venta(item.nombre, precioLimpio))
    }
}
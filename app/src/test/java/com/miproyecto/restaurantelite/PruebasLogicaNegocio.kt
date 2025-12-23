package com.miproyecto.restaurantelite

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

/**
 * Pruebas Unitarias para validar el flujo de datos del restaurante.
 * Estas pruebas se ejecutan en milisegundos sin necesidad de emulador.
 */
class PruebasLogicaNegocio {

    // Esta función se ejecuta antes de CADA prueba para asegurar que empezamos limpios
    @Before
    fun limpiarDatos() {
        Carrito.listaPedidos.clear()
        Carrito.listaCocina.clear()
        Carrito.listaPorCobrar.clear()
        Carrito.listaVentas.clear()
    }

    @Test
    fun `Prueba 1 - Agregar plato aumenta el contador del carrito`() {
        // 1. PREPARACIÓN: Creamos un plato falso
        val plato = Plato("Hamburguesa Test", "Desc", "$ 20.000")

        // 2. ACCIÓN: Lo agregamos al carrito
        Carrito.agregarPlato(plato)

        // 3. VERIFICACIÓN: El carrito debe tener exactamente 1 elemento
        assertEquals("El carrito debería tener 1 elemento", 1, Carrito.cantidad())
    }

    @Test
    fun `Prueba 2 - Enviar a cocina vacia el carrito y llena la cocina`() {
        // 1. El mesero pide
        Carrito.agregarPlato(Plato("Sopa", "Desc", "$ 10.000"))

        // 2. Envía a cocina
        Carrito.enviarPedidoACocina()

        // 3. Validamos que el plato saltó de una lista a la otra
        assertEquals("El carrito del mesero debe quedar vacío", 0, Carrito.cantidad())
        assertEquals("La cocina debe recibir el pedido", 1, Carrito.listaCocina.size)

        // Validamos que llegue con el estado correcto
        assertEquals("El estado inicial debe ser En Espera", "En Espera", Carrito.listaCocina[0].estado)
    }

    @Test
    fun `Prueba 3 - Flujo completo de cobro y calculo de dinero`() {
        // Escenario: Un cliente pide una Langosta de $ 50.000
        // Nota: Probamos con puntos y signo $ para ver si el sistema sabe limpiar el texto
        val platoCaro = Plato("Langosta", "Deliciosa", "$ 50.000")

        // 1. Mesero pide
        Carrito.agregarPlato(platoCaro)

        // 2. Mesero envía a Cocina
        Carrito.enviarPedidoACocina()
        val itemEnCocina = Carrito.listaCocina[0] // Obtenemos la referencia del ticket en cocina

        // 3. Cocina entrega (Envía a Caja)
        Carrito.enviarACaja(itemEnCocina)
        assertEquals("Cocina debe quedar libre", 0, Carrito.listaCocina.size)
        assertEquals("Caja debe tener el cobro pendiente", 1, Carrito.listaPorCobrar.size)

        // 4. Cajero cobra
        val itemEnCaja = Carrito.listaPorCobrar[0]
        Carrito.cobrarItem(itemEnCaja)

        // 5. VALIDACIÓN FINAL DE DINERO
        assertEquals("Caja debe quedar vacía", 0, Carrito.listaPorCobrar.size)
        assertEquals("Debe haber 1 venta en el historial", 1, Carrito.listaVentas.size)

        // Verificamos la conversión de texto a número: "$ 50.000" -> 50000
        assertEquals("El dinero registrado debe ser 50000", 50000, Carrito.listaVentas[0].precio)
    }
}
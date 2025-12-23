# Restaurante Lite - App Móvil

Sistema de Gestión de Restaurante (ERP) desarrollado en Android nativo con Kotlin. La aplicación gestiona el ciclo completo de un pedido: desde la toma de la orden por el mesero, pasando por la preparación en cocina, hasta el cobro en caja y el reporte administrativo.

## Características Principales
* **Gestión de Roles:** Sistema de Login con 4 perfiles distintos (Mesero, Cocina, Cajero, Admin).
* **Persistencia de Datos:** Uso de Patrón Singleton para mantener el estado de los pedidos en memoria RAM.
* **Ciclo de Estados:** Control de estados del pedido (En Espera → Preparando → Listo → Por Cobrar → Cobrado).
* **Cálculos Financieros:** Generación automática de totales y reportes de ventas.

## Tecnologías Utilizadas
* **Lenguaje:** Kotlin
* **IDE:** Android Studio Iguana/Jellyfish
* **Diseño:** XML Layouts (ConstraintLayout, LinearLayout, ScrollView)
* **Control de Versiones:** Git & GitHub
* **Testing:** JUnit para pruebas unitarias de lógica de negocio.

## Módulos del Sistema

### 1. Mesero
* Visualización del Menú.
* Detalle de productos con selector de cantidad.
* Carrito de compras y confirmación de pedido.

### 2. Cocina
* Recepción de pedidos en tiempo real.
* Cambio de estados (Botones dinámicos: "Cocinar", "Terminar", "Entregar").

### 3. Caja
* Recepción de pedidos entregados.
* Cierre de venta (Cobro final).

### 4. Administrador
* Visualización del total de ventas del día.
* Historial detallado de transacciones.

## Credenciales de Acceso (Demo)
Para probar la aplicación, utilice los siguientes usuarios. La contraseña para todos es `1234`.

| Rol | Usuario | Contraseña |
| :--- | :--- | :--- |
| **Mesero** | `mesero` | `1234` |
| **Cocina** | `cocina` | `1234` |
| **Cajero** | `cajero` | `1234` |
| **Admin** | `admin` | `1234` |

## Pruebas Unitarias
El proyecto incluye pruebas automatizadas en `PruebasLogicaNegocio.kt` para validar:
1.  Agregado de items al carrito.
2.  Transición de datos entre Mesero y Cocina.
3.  Cálculo matemático de ingresos y flujo de caja.

---
**Desarrollado por:** Seeler Minel Quiroz Bustamante
**Curso:** Análisis y Desarrollo de Software
**Año:** 2025

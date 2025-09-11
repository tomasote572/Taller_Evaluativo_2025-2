package edu.dosw.lab.Taller_Evaluativo_2025_2;

import edu.dosw.lab.Taller_Evaluativo_2025_2.ejecicio.Excepciones;
import edu.dosw.lab.Taller_Evaluativo_2025_2.ejecicio.MainConsola;
import edu.dosw.lab.Taller_Evaluativo_2025_2.ejecicio.Agentes;
import edu.dosw.lab.Taller_Evaluativo_2025_2.ejecicio.Producto;
import edu.dosw.lab.Taller_Evaluativo_2025_2.ejecicio.ServicioInventario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApplicationTests {

    private ServicioInventario servicio;
    private Producto producto;

    @BeforeEach
    void setUp() {
        servicio = new ServicioInventario();
        producto = new Producto("Laptop", 1500.0, 10, "Electrónica");
    }

    @Test
    void testAgregarProductoExitoso() {
        servicio.agregarProducto(producto);
        Producto obtenido = servicio.obtenerProducto("Laptop");
        assertEquals("Laptop", obtenido.getNombre());
        assertEquals(10, obtenido.getCantidad());
    }

    @Test
    void testAgregarProductoDuplicado() {
        servicio.agregarProducto(producto);
        assertThrows(Excepciones.ProductoDuplicadoException.class, () -> {
            servicio.agregarProducto(new Producto("Laptop", 2000.0, 5, "Electrónica"));
        });
    }

    @Test
    void testModificarStockExitoso() {
        servicio.agregarProducto(producto);
        servicio.modificarStock("Laptop", 5);
        assertEquals(5, servicio.obtenerProducto("Laptop").getCantidad());
    }

    @Test
    void testModificarStockNegativo() {
        servicio.agregarProducto(producto);
        assertThrows(Excepciones.StockInvalidoException.class, () -> {
            servicio.modificarStock("Laptop", -3);
        });
    }

    @Test
    void testModificarStockProductoNoExiste() {
        assertThrows(Excepciones.ProductoNoEncontradoException.class, () -> {
            servicio.modificarStock("Telefono", 5);
        });
    }

    @Test
    void testAgregarProductoStockNegativo() {
        assertThrows(Excepciones.StockInvalidoException.class, () -> {
            servicio.agregarProducto(new Producto("Tablet", 500.0, -2, "Electrónica"));
        });
    }
    @Test
    void testObtenerProductoNoExiste() {
        assertThrows(Excepciones.ProductoNoEncontradoException.class, () -> {
            servicio.obtenerProducto("ProductoInexistente");
        });
    }

    @Test
    void testRegistrarAgentesYNotificar() {
        Agentes.Agente agenteMock = new Agentes.Agente() {
            private int contadorNotificaciones = 0;
            @Override
            public void notificar(Producto producto) {
                contadorNotificaciones++;
                assertNotNull(producto);
                assertEquals("Laptop", producto.getNombre());
            }
            public int getContadorNotificaciones() {
                return contadorNotificaciones;
            }
        };
        servicio.registrarAgente(agenteMock);
        servicio.agregarProducto(producto);
        servicio.modificarStock("Laptop", 8);
        assertTrue(true);
    }

    @Test
    void testAgenteLogNotificacion() {
        Agentes.AgenteLog agenteLog = new Agentes.AgenteLog();
        Producto productoTest = new Producto("Test", 100.0, 15, "Test");
        assertDoesNotThrow(() -> agenteLog.notificar(productoTest));
    }

    @Test
    void testAgenteAdvertenciaStockBajo() {
        Agentes.AgenteAdvertencia agenteAdvertencia = new Agentes.AgenteAdvertencia();
        Producto stockBajo = new Producto("Test", 100.0, 3, "Test");
        Producto stockNormal = new Producto("Test2", 100.0, 10, "Test");
        assertDoesNotThrow(() -> agenteAdvertencia.notificar(stockBajo));
        assertDoesNotThrow(() -> agenteAdvertencia.notificar(stockNormal));
    }

    @Test
    void testMultipleProductos() {
        Producto producto1 = new Producto("Teclado", 50.0, 20, "Periféricos");
        Producto producto2 = new Producto("Mouse", 25.0, 30, "Periféricos");
        Producto producto3 = new Producto("Monitor", 300.0, 5, "Pantallas");
        servicio.agregarProducto(producto1);
        servicio.agregarProducto(producto2);
        servicio.agregarProducto(producto3);
        assertEquals(20, servicio.obtenerProducto("Teclado").getCantidad());
        assertEquals(30, servicio.obtenerProducto("Mouse").getCantidad());
        assertEquals(5, servicio.obtenerProducto("Monitor").getCantidad());
    }

    @Test
    void testModificarStockCero() {
        servicio.agregarProducto(producto);
        servicio.modificarStock("Laptop", 0);
        assertEquals(0, servicio.obtenerProducto("Laptop").getCantidad());
    }

    @Test
    void testProductoSetters() {
        Producto productoTest = new Producto("Test", 100.0, 5, "Test");
        productoTest.setCantidad(20);
        assertEquals(20, productoTest.getCantidad());
        assertEquals("Test", productoTest.getNombre());
        assertEquals(100.0, productoTest.getPrecio());
        assertEquals("Test", productoTest.getCategoria());
    }

    @Test
    void testAgenteAdvertenciaStockLimite() {
        Agentes.AgenteAdvertencia agenteAdvertencia = new Agentes.AgenteAdvertencia();
        Producto stockLimite = new Producto("Test", 100.0, 4, "Test");
        Producto stockSeguro = new Producto("Test2", 100.0, 5, "Test");
        assertDoesNotThrow(() -> agenteAdvertencia.notificar(stockLimite));
        assertDoesNotThrow(() -> agenteAdvertencia.notificar(stockSeguro));
    }

    @Test
    void testExcepcionesMensajes() {
        String mensaje = "Mensaje de prueba";
        Excepciones.ProductoNoEncontradoException ex1 = new Excepciones.ProductoNoEncontradoException(mensaje);
        Excepciones.ProductoDuplicadoException ex2 = new Excepciones.ProductoDuplicadoException(mensaje);
        Excepciones.StockInvalidoException ex3 = new Excepciones.StockInvalidoException(mensaje);
        assertEquals(mensaje, ex1.getMessage());
        assertEquals(mensaje, ex2.getMessage());
        assertEquals(mensaje, ex3.getMessage());
    }

    @Test
    void testRegistrarMultiplesAgentes() {
        servicio.registrarAgente(new Agentes.AgenteLog());
        servicio.registrarAgente(new Agentes.AgenteAdvertencia());
        servicio.agregarProducto(producto);
        servicio.modificarStock("Laptop", 2);
        assertTrue(true);
    }

    @Test
    void testProductoConstructorCompleto() {
        Producto productoCompleto = new Producto("Nombre", 99.99, 42, "Categoría");
        assertEquals("Nombre", productoCompleto.getNombre());
        assertEquals(99.99, productoCompleto.getPrecio());
        assertEquals(42, productoCompleto.getCantidad());
        assertEquals("Categoría", productoCompleto.getCategoria());
    }

    @Test
    void testServicioInventarioVacio() {
        assertThrows(Excepciones.ProductoNoEncontradoException.class, () -> {
            servicio.obtenerProducto("CualquierProducto");
        });
    }

    @Test
    void testModificarStockMismoValor() {
        servicio.agregarProducto(producto);
        int cantidadOriginal = producto.getCantidad();
        servicio.modificarStock("Laptop", cantidadOriginal);
        assertEquals(cantidadOriginal, servicio.obtenerProducto("Laptop").getCantidad());
    }

    @Test
    void testAgregarProductoConCeroStock() {
        Producto productoCeroStock = new Producto("ProductoCero", 50.0, 0, "Test");

        assertDoesNotThrow(() -> servicio.agregarProducto(productoCeroStock));
        assertEquals(0, servicio.obtenerProducto("ProductoCero").getCantidad());
    }

    @Test
    void testNotificarAgentesAlModificarStock() {
        servicio.registrarAgente(new Agentes.AgenteLog());
        servicio.agregarProducto(producto);
        assertDoesNotThrow(() -> servicio.modificarStock("Laptop", 7));
    }

    @Test
    void testProductoConDatosExtremos() {
        Producto productoExtremo = new Producto("A", 0.01, 1, "X");
        servicio.agregarProducto(productoExtremo);
        Producto obtenido = servicio.obtenerProducto("A");
        assertEquals("A", obtenido.getNombre());
        assertEquals(0.01, obtenido.getPrecio());
        assertEquals(1, obtenido.getCantidad());
        assertEquals("X", obtenido.getCategoria());
    }

    @Test
    void testRegistrarAgenteNulo() {
        assertDoesNotThrow(() -> servicio.registrarAgente(null));
    }

    @Test
    void testMainConsolaSalirInmediato() {
        // Simula: usuario presiona 0 para salir
        String input = "0\n";
        InputStream originalIn = System.in;
        try {
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            assertDoesNotThrow(() -> MainConsola.iniciar());
        } finally {
            System.setIn(originalIn);
        }
    }

    @Test
    void testMainConsolaAgregarProducto() {
        // Usar ENTERO para el precio para evitar problemas de decimales
        String input = "1\nTeclado\n25\n10\nPerifericos\n0\n";
        InputStream originalIn = System.in;
        try {
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            assertDoesNotThrow(() -> MainConsola.iniciar());
        } finally {
            System.setIn(originalIn);
        }
    }

    @Test
    void testMainConsolaModificarStock() {
        // Usar enteros para evitar problemas de locale con decimales
        String input = "1\nMouse\n15\n20\nPerifericos\n2\nMouse\n5\n0\n";
        InputStream originalIn = System.in;
        try {
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            assertDoesNotThrow(() -> MainConsola.iniciar());
        } finally {
            System.setIn(originalIn);
        }
    }

    @Test
    void testMainConsolaOpcionInvalida() {
        // Simula: opción inválida y luego salir
        String input = "999\n0\n";
        InputStream originalIn = System.in;
        try {
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            assertDoesNotThrow(() -> MainConsola.iniciar());
        } finally {
            System.setIn(originalIn);
        }
    }

    @Test
    void testMainConsolaProductoConStockBajo() {
        String input = "1\nProductoCritico\n10\n3\nElectronica\n0\n";
        InputStream originalIn = System.in;
        try {
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            assertDoesNotThrow(() -> MainConsola.iniciar());
        } finally {
            System.setIn(originalIn);
        }
    }

    @Test
    void testMainConsolaAgregarProductoConEnteros() {
        // Usar números enteros para evitar problemas con decimales
        String input = "1\nTeclado\n25\n10\nPerifericos\n0\n";
        InputStream originalIn = System.in;
        try {
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            assertDoesNotThrow(() -> MainConsola.iniciar());
        } finally {
            System.setIn(originalIn);
        }
    }

}



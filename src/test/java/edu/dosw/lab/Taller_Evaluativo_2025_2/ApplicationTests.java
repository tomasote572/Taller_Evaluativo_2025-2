package edu.dosw.lab.Taller_Evaluativo_2025_2;

import edu.dosw.lab.Taller_Evaluativo_2025_2.ejecicio.Excepciones;
import edu.dosw.lab.Taller_Evaluativo_2025_2.ejecicio.Producto;
import edu.dosw.lab.Taller_Evaluativo_2025_2.ejecicio.ServicioInventario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
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
}


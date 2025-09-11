package ejecicio;

import java.util.*;

/**
 * Servicio principal para la gestión de inventario de productos.
 */
public class ServicioInventario {

    private final Map<String, Producto> inventario = new HashMap<>();
    private final List<Agentes.Agente> agentes = new ArrayList<>();

    public void registrarAgente(Agentes.Agente agente) {
        agentes.add(agente);
    }

    public void agregarProducto(Producto producto) {
        if (inventario.containsKey(producto.getNombre())) {
            throw new Excepciones.ProductoDuplicadoException(
                    "El producto '" + producto.getNombre() + "' ya existe.");
        }
        if (producto.getCantidad() < 0) {
            throw new Excepciones.StockInvalidoException(
                    "El stock inicial no puede ser negativo.");
        }
        inventario.put(producto.getNombre(), producto);
    }
    public void modificarStock(String nombre, int nuevaCantidad) {
        Producto producto = inventario.get(nombre);
        if (producto == null) {
            throw new Excepciones.ProductoNoEncontradoException(
                    "El producto '" + nombre + "' no existe en el inventario.");
        }
        if (nuevaCantidad < 0) {
            throw new Excepciones.StockInvalidoException(
                    "El stock no puede ser negativo.");
        }
        producto.setCantidad(nuevaCantidad);
        notificarAgentes(producto);
    }
    public Producto obtenerProducto(String nombre) {
        Producto producto = inventario.get(nombre);
        if (producto == null) {
            throw new Excepciones.ProductoNoEncontradoException(
                    "El producto '" + nombre + "' no existe.");
        }
        return producto;
    }
    private void notificarAgentes(Producto producto) {
        for (Agentes.Agente agente : agentes) {
            agente.notificar(producto);
        }
    }
}

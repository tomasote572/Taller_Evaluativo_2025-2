package edu.dosw.lab.Taller_Evaluativo_2025_2.ejecicio;

import java.util.*;

/**
 * Servicio principal para la gestión de inventario de productos.
 * Permite agregar productos, modificar el stock, obtener productos y notificar agentes ante cambios en el inventario.
 * Utiliza un mapa para almacenar los productos y una lista para gestionar los agentes observadores.
 */
public class ServicioInventario {

    private final Map<String, Producto> inventario = new HashMap<>();
    private final List<Agentes.Agente> agentes = new ArrayList<>();

    /**
     * Registra un nuevo agente que será notificado ante cambios en el stock de productos.
     * @param agente Instancia del agente a registrar
     */
    public void registrarAgente(Agentes.Agente agente) {
        agentes.add(agente);
    }

    /**
     * Agrega un producto al inventario.
     * Lanza una excepción si el producto ya existe o si el stock inicial es negativo.
     * @param producto Producto a agregar
     */
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

    /**
     * Modifica la cantidad en stock de un producto existente.
     * Lanza una excepción si el producto no existe o si la nueva cantidad es negativa.
     * Notifica a los agentes registrados sobre el cambio.
     * @param nombre Nombre del producto a modificar
     * @param nuevaCantidad Nueva cantidad en stock
     */
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

    /**
     * Obtiene un producto del inventario por su nombre.
     * Lanza una excepción si el producto no existe.
     * @param nombre Nombre del producto a obtener
     * @return Producto encontrado
     */
    public Producto obtenerProducto(String nombre) {
        Producto producto = inventario.get(nombre);
        if (producto == null) {
            throw new Excepciones.ProductoNoEncontradoException(
                    "El producto '" + nombre + "' no existe.");
        }
        return producto;
    }

    /**
     * Notifica a todos los agentes registrados sobre un cambio en el stock de un producto.
     * @param producto Producto que ha cambiado
     */
    private void notificarAgentes(Producto producto) {
        for (Agentes.Agente agente : agentes) {
            agente.notificar(producto);
        }
    }
}

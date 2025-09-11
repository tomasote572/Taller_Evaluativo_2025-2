package edu.dosw.lab.Taller_Evaluativo_2025_2.ejecicio;

/**
 * Representa un producto dentro del inventario.
 * Contiene información relevante como nombre, precio, cantidad disponible y categoría.
 * Proporciona métodos para acceder y modificar estos atributos.
 */
public class Producto {
    private String nombre;
    private double precio;
    private int cantidad;
    private String categoria;

    /**
     * Crea una nueva instancia de Producto con los datos especificados.
     * @param nombre Nombre del producto
     * @param precio Precio unitario del producto
     * @param cantidad Cantidad disponible en stock
     * @param categoria Categoría del producto
     */
    public Producto(String nombre, double precio, int cantidad, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.categoria = categoria;
    }

    /**
     * Obtiene el nombre del producto.
     * @return Nombre del producto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el precio unitario del producto.
     * @return Precio del producto
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Obtiene la cantidad disponible en stock del producto.
     * @return Cantidad disponible
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Obtiene la categoría del producto.
     * @return Categoría del producto
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Modifica la cantidad disponible en stock del producto.
     * @param cantidad Nueva cantidad disponible
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}


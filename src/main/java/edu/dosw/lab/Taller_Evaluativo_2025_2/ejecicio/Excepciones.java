package edu.dosw.lab.Taller_Evaluativo_2025_2.ejecicio;


/**
 * Clase contenedora de excepciones personalizadas para la gestión de productos en el inventario.
 * Permite definir diferentes tipos de errores que pueden ocurrir durante las operaciones de inventario.
 */
public class Excepciones {
    /**
     * Excepción lanzada cuando no se encuentra un producto en el inventario.
     * Útil para indicar que la búsqueda de un producto ha fallado.
     */
    public static class ProductoNoEncontradoException extends RuntimeException {
        public ProductoNoEncontradoException(String mensaje) {
            super(mensaje);
        }
    }

    /**
     * Excepción lanzada cuando se intenta agregar un producto que ya existe en el inventario.
     * Permite evitar duplicados en la gestión de productos.
     */
    public static class ProductoDuplicadoException extends RuntimeException {
        public ProductoDuplicadoException(String mensaje) {
            super(mensaje);
        }
    }

    /**
     * Excepción lanzada cuando el stock de un producto es inválido (por ejemplo, negativo).
     * Ayuda a mantener la integridad de los datos del inventario.
     */
    public static class StockInvalidoException extends RuntimeException {
        public StockInvalidoException(String mensaje) {
            super(mensaje);
        }
    }
}


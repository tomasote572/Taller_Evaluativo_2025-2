package ejecicio;

public class Excepciones {

    public static class ProductoNoEncontradoException extends RuntimeException {
        public ProductoNoEncontradoException(String mensaje) {
            super(mensaje);
        }
    }

    public static class ProductoDuplicadoException extends RuntimeException {
        public ProductoDuplicadoException(String mensaje) {
            super(mensaje);
        }
    }

    public static class StockInvalidoException extends RuntimeException {
        public StockInvalidoException(String mensaje) {
            super(mensaje);
        }
    }
}


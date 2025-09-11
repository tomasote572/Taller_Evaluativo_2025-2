package edu.dosw.lab.Taller_Evaluativo_2025_2.ejecicio;

import java.util.logging.Logger;

/**
 * Clase contenedora de agentes que reaccionan ante cambios en el stock de productos.
 * Permite definir diferentes comportamientos cuando se modifica la cantidad disponible de un producto.
 */
public class Agentes {

    private static final Logger LOGGER = Logger.getLogger(Agentes.class.getName());

    /**
     * Interfaz que representa un agente capaz de recibir notificaciones sobre cambios en productos.
     */
    public interface Agente {
        void notificar(Producto producto);
    }

    /**
     * Implementación de Agente que registra en consola el nombre y la cantidad disponible
     * de un producto cada vez que se modifica su stock.
     */
    public static class AgenteLog implements Agentes.Agente {
        @Override
        public void notificar(Producto producto) {
            LOGGER.info("Producto: " + producto.getNombre() + " -> " + producto.getCantidad() + " unidades disponibles");
        }
    }



    /**
     * Implementación de Agente que lanza una advertencia en el log si la cantidad de un producto
     * es menor a 5 unidades.
     */
    public static class AgenteAdvertencia implements Agente {
        @Override
        public void notificar(Producto producto) {
            if (producto.getCantidad() < 5) {
                LOGGER.warning("ALERTA !!! El stock del producto: "
                        + producto.getNombre() + " es muy bajo, solo quedan "
                        + producto.getCantidad() + " unidades.");
            }
        }
    }
}

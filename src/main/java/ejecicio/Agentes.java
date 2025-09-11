package ejecicio;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contenedor de agentes que reaccionan a cambios en el stock.
 */
public class Agentes {

    private static final Logger LOGGER = Logger.getLogger(Agentes.class.getName());

    public interface Agente {
        void notificar(Producto producto);
    }

    /**
     * Agente que imprime en consola el stock disponible al modificar un producto.
     */
    public static class AgenteLog implements Agente {
        @Override
        public void notificar(Producto producto) {
            LOGGER.info("Producto: " + producto.getNombre() + " -> " + producto.getCantidad() + " unidades disponibles");
        }
    }

    /**
     * Agente que lanza alerta cuando el stock es menor a 5.
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

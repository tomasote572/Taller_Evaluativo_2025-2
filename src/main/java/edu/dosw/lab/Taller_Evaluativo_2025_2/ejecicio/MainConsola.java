package edu.dosw.lab.Taller_Evaluativo_2025_2.ejecicio;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase principal para la interacción con el usuario a través de la consola.
 * Permite agregar productos, modificar el stock y salir del programa mediante un menú simple.
 * Utiliza la clase ServicioInventario para gestionar los productos y notificar a los agentes registrados.
 */
public class MainConsola {

    private static final Logger logger = LoggerFactory.getLogger(MainConsola.class);

    /**
     * Inicia el menú de la aplicación en consola.
     * Registra agentes para la notificación de cambios en el inventario.
     * Permite al usuario realizar operaciones como agregar productos y modificar el stock.
     * Finaliza cuando el usuario selecciona la opción de salir.
     */
    public static void iniciar() {
        ServicioInventario servicio = new ServicioInventario();
        servicio.registrarAgente(new Agentes.AgenteLog());
        servicio.registrarAgente(new Agentes.AgenteAdvertencia());
        Scanner sc = new Scanner(System.in);
        while (true) {
            logger.info("\n--- MENÚ ---");
            logger.info("1. Agregar producto");
            logger.info("2. Modificar stock");
            logger.info("0. Salir");
            logger.info("Opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();
            if (opcion == 1) {
                logger.info("Nombre: ");
                String nombre = sc.nextLine();
                logger.info("Precio: ");
                double precio = sc.nextDouble();
                logger.info("Cantidad: ");
                int cantidad = sc.nextInt();
                sc.nextLine();
                logger.info("Categoría: ");
                String categoria = sc.nextLine();
                servicio.agregarProducto(new Producto(nombre, precio, cantidad, categoria));
            } else if (opcion == 2) {
                logger.info("Nombre del producto: ");
                String nombre = sc.nextLine();
                logger.info("Nueva cantidad: ");
                int cantidad = sc.nextInt();
                servicio.modificarStock(nombre, cantidad);
            } else if (opcion == 0) {
                logger.info("Saliendo...");
                break;
            }
        }
        sc.close();
    }
}

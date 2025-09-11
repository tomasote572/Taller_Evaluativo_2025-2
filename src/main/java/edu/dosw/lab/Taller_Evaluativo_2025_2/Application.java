package edu.dosw.lab.Taller_Evaluativo_2025_2;

import ejecicio.MainConsola;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        MainConsola.iniciar();
    }
}

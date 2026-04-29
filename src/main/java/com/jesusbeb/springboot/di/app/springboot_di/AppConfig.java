package com.jesusbeb.springboot.di.app.springboot_di;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

// Esta clase se encarga de la configuración de la aplicación. La anotación @Configuration indica que esta clase contiene definiciones de beans y configuraciones para la aplicación.
// La anotación @PropertySource se utiliza para cargar un archivo de propiedades externo (en este caso, "config.properties") que contiene configuraciones adicionales para la aplicación. 
// Esto permite separar las configuraciones del código y facilita su mantenimiento.
@Configuration
@PropertySource("classpath:config.properties")
public class AppConfig {

}

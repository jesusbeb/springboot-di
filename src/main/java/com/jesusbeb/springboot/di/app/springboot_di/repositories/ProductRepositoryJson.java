package com.jesusbeb.springboot.di.app.springboot_di.repositories;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.jesusbeb.springboot.di.app.springboot_di.models.Product;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

// Esta clase es una implementación del repositorio que obtiene los productos desde un archivo JSON ubicado en el classpath.
public class ProductRepositoryJson implements ProductRepository {

    private List<Product> list;

    // Constructor. Lee el archivo JSON y mapea su contenido a una lista de productos utilizando ObjectMapper de Jackson.
    // Resource se utiliza para acceder al archivo JSON, indicandole el path.
    // ObjectMapper lee el contenido del archivo y lo convierte en un objeto de Java.
    // Se crea una lista a partir del Array que objectMapper devuelve usando readValue, que recibe como parametros el archivo y la clase a la que se desea convertir.
    public ProductRepositoryJson() {
        Resource resource = new ClassPathResource("json/product.json");
        ObjectMapper objectMapper = new ObjectMapper(); 
        try {
            list = Arrays.asList(objectMapper.readValue(resource.getFile(), Product[].class));
        } catch (JacksonException e) { // JacksonException es una excepción que puede ocurrir durante el proceso de mapeo de JSON a objetos Java, por ejemplo, si el formato del JSON es incorrecto o no coincide con la estructura de la clase Product.
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) { // IOException es una excepción que puede ocurrir durante la lectura del archivo JSON, por ejemplo, si el archivo no existe o no se puede acceder.
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> findAll() {
        return list;
    }

    @Override
    public Product findById(Long id) {
        return list.stream().filter(p -> p.getId().equals(id))
            .findFirst() // findFirst devuelve un Optional, que es una clase contenedora que puede contener un valor o estar vacía.
            .orElse(null); // orElse devuelve el valor contenido en el Optional si está presente, o el valor especificado (en este caso, null) si el Optional está vacío. Esto permite manejar el caso en que no se encuentra un producto con el ID dado sin lanzar una excepción.
    }

}

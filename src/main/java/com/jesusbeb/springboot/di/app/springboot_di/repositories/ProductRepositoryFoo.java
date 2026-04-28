package com.jesusbeb.springboot.di.app.springboot_di.repositories;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.jesusbeb.springboot.di.app.springboot_di.models.Product;

// Esta clase es una implementación alternativa del repositorio de productos. En lugar de simular una base de datos con una lista de productos, 
// esta implementación devuelve un producto fijo o una lista con un solo producto. Esto se hace para ilustrar cómo se pueden tener múltiples 
// implementaciones de un mismo repositorio y cómo Spring puede inyectar la implementación correcta según la configuración.
// La anotación @Primary indica que esta implementación debe ser la preferida cuando se inyecte el repositorio. Si no se usara @Primary, Spring lanzaría una excepción al no saber qué implementación inyectar.
@Primary
@Repository
public class ProductRepositoryFoo implements ProductRepository {

    @Override
    public List<Product> findAll() {
        return Collections.singletonList(new Product(1L, "Monitor Asus 27", 600L));
    }

    @Override
    public Product findById(Long id) {
        return new Product(id, "Monitor Asus 27", 600L);
    }

}

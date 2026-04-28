package com.jesusbeb.springboot.di.app.springboot_di.repositories;

import java.util.List;

import com.jesusbeb.springboot.di.app.springboot_di.models.Product;

// Repositorio se encarga de la gestión de datos. Aqui se definen los métodos para acceder a los datos de los productos y que deberan ser implementados por una clase concreta (en este caso, ProductRepositoryImpl).
public interface ProductRepository {
    
    List<Product> findAll();

    Product findById(Long id);

}

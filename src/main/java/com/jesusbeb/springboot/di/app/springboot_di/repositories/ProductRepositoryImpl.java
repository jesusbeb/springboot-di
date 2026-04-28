package com.jesusbeb.springboot.di.app.springboot_di.repositories;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jesusbeb.springboot.di.app.springboot_di.models.Product;

@Component
public class ProductRepositoryImpl implements ProductRepository {

    List<Product> data;

    // Constructor. En este caso, se simula una base de datos con una lista de productos.
    public ProductRepositoryImpl() {
        this.data = Arrays.asList(
            new Product(1L, "Memoria corsair 32", 300L),
            new Product(2L, "Cpu Intel Core i9", 850L),
            new Product(3L, "Teclado Razer Mini 60%", 180L),
            new Product(4L, "Motherboard Gigabyte", 490L)
        );
    }

    // Método para obtener todos los productos
    @Override
    public List<Product> findAll() {
        return this.data;
    }

    @Override
    public Product findById(Long id) {
        return data.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst()
            .orElse(null);
    }


}

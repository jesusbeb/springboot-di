package com.jesusbeb.springboot.di.app.springboot_di.services;

import java.util.List;
import java.util.stream.Collectors;

import com.jesusbeb.springboot.di.app.springboot_di.models.Product;
import com.jesusbeb.springboot.di.app.springboot_di.repositories.ProductRepository;

// Service se encarga de la lógica de negocio relacionada con los productos. Manipula los datos obtenidos del repositorio y los devuelve al controlador.
public class ProductService {

    // En este caso, se instancia el repositorio directamente dentro del servicio. Esto no es lo ideal, pero se hace así para ilustrar el concepto de inyección de dependencias.
    private ProductRepository repository = new ProductRepository();

    // Obtenemos toda la lista de Productos, aplicamos stream para mapear cada producto y agregarle el impuesto del 16% al precio, 
    // Clonamos el producto original para no modificarlo, le agregamos el nuevo precio (convertido a Long) con el impuesto
    // Como map devuelve un stream, lo convertimos a una lista con collect(Collectors.toList()).
    public List<Product> findAll() {
        return repository.findAll().stream().map(p -> {
            Double priceTax = p.getPrice() * 1.16; 
            Product newProd = (Product) p.clone();
            newProd.setPrice(priceTax.longValue());
            return newProd; 
        }).collect(Collectors.toList());
    }

    public Product findById(Long id) {
        return repository.findById(id);
    }

}

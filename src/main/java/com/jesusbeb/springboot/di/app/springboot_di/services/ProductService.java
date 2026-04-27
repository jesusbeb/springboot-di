package com.jesusbeb.springboot.di.app.springboot_di.services;

import java.util.List;
import java.util.stream.Collectors;

import com.jesusbeb.springboot.di.app.springboot_di.models.Product;
import com.jesusbeb.springboot.di.app.springboot_di.repositories.ProductRepository;

// Service se encarga de la lógica de negocio relacionada con los productos. Manipula los datos obtenidos del repositorio y los devuelve al controlador.
public class ProductService {

    // En este caso, se instancia el repositorio directamente dentro del servicio. Esto no es lo ideal, pero se hace así para ilustrar el concepto de inyección de dependencias.
    private ProductRepository repository = new ProductRepository();

    // Obtenemos toda la lista de productos y con stream() convertimos la lista en un flujo de datos, lo que nos permite aplicar operaciones funcionales como map() y collect().
    // map() iterará sobre cada producto de la lista. priceImp es el nuevo precio con el impuesto del 16% aplicado.
    // Luego, se actualiza el precio del producto con el nuevo valor. Se utiliza long.value() para convertir a tipo Long
    // map devuelve un stream por lo que se necesita collect() para convertirlo nuevamente a una lista.
    public List<Product> findAll() {
        return repository.findAll().stream().map(p -> {
            Double priceImp = p.getPrice() * 1.16; 
            p.setPrice(priceImp.longValue());
            return p;
        }).collect(Collectors.toList());
    }

    public Product findById(Long id) {
        return repository.findById(id);
    }

}

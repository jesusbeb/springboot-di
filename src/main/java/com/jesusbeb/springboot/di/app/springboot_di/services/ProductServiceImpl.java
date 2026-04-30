package com.jesusbeb.springboot.di.app.springboot_di.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.jesusbeb.springboot.di.app.springboot_di.models.Product;
import com.jesusbeb.springboot.di.app.springboot_di.repositories.ProductRepository;

// Service se encarga de la lógica de negocio relacionada con los productos. Manipula los datos obtenidos del repositorio y los devuelve al controlador.
@Service
public class ProductServiceImpl implements ProductService {

    // Se inyecta el repositorio mediante atributo con @Autowired. Tambien se podría inyectar mediante constructor o setter. Con setter tambien se usa @Autowired, pero con constructor no es necesario.
    // Qualifier inyecta el repositorio indicado (con su nombre escrito todo en minusculas), sin importar que exista otra implementación marcada como @Primary.
    @Autowired
    //@Qualifier("productRepositoryImpl")
    private ProductRepository repository;

    // Se inyecta el entorno para obtener las propiedades definidas en el archivo de configuración. Esto permite acceder a la propiedad "config.price.tax".
    @Autowired
    private Environment environment;

    // Obtenemos toda la lista de Productos, aplicamos stream para mapear cada producto y agregarle el impuesto del 16% al precio, 
    // Clonamos el producto original para no modificarlo, le agregamos el nuevo precio (convertido a Long) con el impuesto
    // Como map devuelve un stream, lo convertimos a una lista con collect(Collectors.toList()).
    @Override
    public List<Product> findAll() {
        return repository.findAll().stream().map(p -> {
            Double priceTax = p.getPrice() * environment.getProperty("config.price.tax", Double.class); // Se obtiene el precio con impuesto multiplicando el precio original por el valor de la propiedad "config.price.tax" obtenida del entorno.
            Product newProd = (Product) p.clone();
            newProd.setPrice(priceTax.longValue());
            return newProd; 
        }).collect(Collectors.toList());
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id);
    }

}

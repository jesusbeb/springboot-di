package com.jesusbeb.springboot.di.app.springboot_di.models;

// Implementamos de Clonable para poder clonar los objetos y no modificar el original al agregarle el impuesto, ya que en cada peticion http que se hace,
// va incrementando el precio del producto original, lo que no es correcto. Al clonar el producto, se crea una nueva instancia con los mismos datos.
public class Product implements Cloneable {
    
    // Atributos
    private Long id;
    private String name;
    private Long price;


    // Constructors

    public Product() {
    }

    public Product(Long id, String name, Long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    // Método para clonar el objeto. Si no se puede clonar, se crea un nuevo objeto con los mismos datos.
    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return new Product(id, getName(), this.getPrice()); // Diferentes formas de accesar a los atributos de la clase en que estamos.
        }
    }
}

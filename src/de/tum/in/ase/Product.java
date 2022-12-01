package de.tum.in.ase;

public class Product<T> {

    private final String name;
    private final double price;

    public Product(String name, double price) {
        if (name == null || price <= 0) {
            throw new IllegalArgumentException();
        } else {
            this.name = name;
            this.price = price;
        }
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        return "Product: " + name + " Price: " + price;
    }
}

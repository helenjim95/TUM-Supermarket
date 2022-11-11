package de.tum.in.ase;

public interface Queue<Product> extends DataStructure {

    void enqueue(Product t);

    Product dequeue();
}

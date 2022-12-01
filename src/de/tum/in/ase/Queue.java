package de.tum.in.ase;

public interface Queue<T> extends DataStructure {

    void enqueue(T t);

    T dequeue();
}

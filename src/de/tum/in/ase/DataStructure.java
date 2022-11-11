package de.tum.in.ase;

public interface DataStructure {
    int size();

    default boolean isEmpty() {
        return size() == 0;
    }
}

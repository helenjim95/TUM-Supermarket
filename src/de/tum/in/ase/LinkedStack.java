package de.tum.in.ase;

public class LinkedStack<Product> implements Stack<Product> {

    private List<Product> first = null;

    @Override
    public void push(Product t) {
        first = new List<>(t, first);
    }

    @Override
    public Product pop() {
        if (first == null)
            return null;
        Product info = first.getInfo();
        first = first.getNext();
        return info;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public int size() {
        if (isEmpty())
            return 0;
        return first.length();
    }

    @Override
    public String toString() {
        if (isEmpty())
            return "";
        return first.toString();
    }
}

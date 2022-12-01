package de.tum.in.ase;

public class LinkedStack<T> implements Stack<T> {

    private List<T> first = null;

    @Override
    public void push(T t) {
        first = new List<>(t, first);
    }

    @Override
    public T pop() {
        if (first == null)
            return null;
        T info = first.getInfo();
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

package de.tum.in.ase;


public class LinkedQueue<T> implements Queue<T> {
    private List<T> first;
    private List<T> last;

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public int size() {
        if (first == null)
            return 0;
        return first.length();
    }

    @Override
    public void enqueue(T t) {
        if (first == null) {
            first = new List<>(t);
            last = first;
        } else {
            last.insert(t);
            last = last.getNext();
        }
    }

    @Override
    public T dequeue() {
        if (first == null)
            return null;
        T t = first.getInfo();
        if (first == last)
            last = null;
        first = first.getNext();
        return t;
    }

    @Override
    public String toString() {
        if (isEmpty())
            return "[]";
        return first.toString();
    }
}

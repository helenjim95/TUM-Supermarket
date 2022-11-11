package de.tum.in.ase;


public class LinkedQueue<Product> implements Queue<Product> {
    private List<Product> first;
    private List<Product> last;

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
    public void enqueue(Product t) {
        if (first == null) {
            first = new List<>(t);
            last = first;
        } else {
            last.insert(t);
            last = last.getNext();
        }
    }

    @Override
    public Product dequeue() {
        if (first == null)
            return null;
        Product t = first.getInfo();
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

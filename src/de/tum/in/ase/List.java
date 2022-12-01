package de.tum.in.ase;

public class List<T> {
    private final T info;
    private List<T> next;

    public List(T info) {
        this(info, null);
    }

    public List(T info, List<T> next) {
        this.info = info;
        this.next = next;
    }

    public void insert(T x) {
        next = new List<>(x, next);
    }

    public void delete() {
        if (next != null) {
            next = next.next;
        }
    }

    public T getInfo() {
        return info;
    }

    public List<T> getNext() {
        return next;
    }

    public int length() {
        int result = 1;
        for (List<T> temp = next; temp != null; temp = temp.next) {
            result++;
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[" + info);
        for (List<T> temp = next; temp != null; temp = temp.next) {
            result.append(", ").append(temp.info);
        }
        return result + "]";
    }
}

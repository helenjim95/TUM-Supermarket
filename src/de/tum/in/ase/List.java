package de.tum.in.ase;

public class List<Product> {
    public Product info;
    public List<Product> next;

    public List(Product info) {
        this(info, null);
    }

    public List(Product info, List<Product> next) {
        this.info = info;
        this.next = next;
    }

    public void insert(Product x) {
        next = new List<>(x, next);
    }

    public void delete() {
        if (next != null) {
            next = next.next;
        }
    }

    public Product getInfo() {
        return info;
    }

    public List<Product> getNext() {
        return next;
    }

    public int length() {
        int result = 1;
        for (List<Product> temp = next; temp != null; temp = temp.next) {
            result++;
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[" + info);
        for (List<Product> temp = next; temp != null; temp = temp.next) {
            result.append(", ").append(temp.info);
        }
        return result + "]";
    }
}

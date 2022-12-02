package de.tum.in.ase;

public class Customer {
    //TODO Add attribute TsInBasket
    private Stack<Product> productsInBasket;
    private String name;
    private double money;

    public Customer(String name, double money) {
        if (name == null || money < 0) {
            throw new IllegalArgumentException();
        } else {
            this.name = name;
            this.money = money;
        }
    }

    public String getName() {
        return name;
    }

    public double getMoney() {
        return money;
    }

    public Stack<Product> getProductsInBasket() {
        return productsInBasket;
    }

    public boolean hasMoney() {
        return money > 0;
    }

    //    TODO:  java.lang.NullPointerException: Cannot invoke "de.tum.in.ase.Stack.push(Object)" because "this.productsInBasket" is null
    public void addProductToBasket(Product product) {
        productsInBasket.push(product);
    }
    public void placeAllProductsOnBand(Queue<Product> band) {
        while (!productsInBasket.isEmpty()) {
            Product product = productsInBasket.pop();
            band.enqueue(product);
        }
    }

//    TODO:  java.lang.NullPointerException: Cannot invoke "de.tum.in.ase.Stack.push(Object)" because "this.productsInBasket" is null
    public void takeAllProductsFromBand(Queue<Product> band) {
        while (!band.isEmpty()) {
            Product product = band.dequeue();
            if (productsInBasket != null) {
                addProductToBasket(product);
            }
        }
    }
    public void pay(double amount) throws UnsupportedOperationException {
        if (amount < 0 || money < amount) {
            throw new UnsupportedOperationException();
        } else {
            money -= amount;
        }
    }
    public void goToCheckout(TUMSupermarket supermarket) throws IllegalArgumentException {
        Checkout[] checkouts = supermarket.getCheckouts();
        if (checkouts == null) {
            throw new IllegalArgumentException();
        } else {
            for (Checkout checkout : checkouts) {
                if (checkout == null) {
                    throw new IllegalArgumentException();
                }
            }
            Checkout checkoutWithShortestQueue = supermarket.getCheckoutWithSmallestQueue();
            Queue<Customer> customers = checkoutWithShortestQueue.getCustomers();
            customers.enqueue(this);
        }
    }

    @Override
    public String toString() {
        return "Customer: " + name + ", money: " + money + "\n" + productsInBasket;
    }
}

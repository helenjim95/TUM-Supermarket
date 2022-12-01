package de.tum.in.ase;

public class Customer {
    //TODO Add attribute TsInBasket
    private Stack<Product> ProductsInBasket;
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
        return ProductsInBasket;
    }

    //TODO implement methods
    public boolean hasMoney() {
        return money > 0;
    }

    public void addProductToBasket(Product product) {
        ProductsInBasket.push(product);
    }
    public void placeAllProductsOnBand(Queue<Product> band) {
        while (!ProductsInBasket.isEmpty()) {
            Product product = ProductsInBasket.pop();
            band.enqueue(product);
        }
    }
    public void takeAllProductsFromBand(Queue<Product> band) {
        while (!band.isEmpty()) {
            Product product = band.dequeue();
            addProductToBasket(product);
        }
    }
    public void pay(double amount) {
        if (amount < 0 || money < amount) {
            throw new UnsupportedOperationException();
        } else {
            money -= amount;
        }
    }
    public void goToCheckout(TUMSupermarket supermarket) {
        Checkout[] checkouts = supermarket.getCheckouts();
        if (checkouts == null) {
            throw new IllegalStateException();
        } else {
            for (Checkout checkout : checkouts) {
                if (checkout == null) {
                    throw new IllegalStateException();
                }
            }
            Checkout checkoutWithShortestQueue = supermarket.getCheckoutWithSmallestQueue();
            LinkedQueue<Customer> customers = checkoutWithShortestQueue.getCustomers();
            customers.enqueue(this);
        }
    }

    @Override
    public String toString() {
        return "Customer: " + name + ", money: " + money + "\n" + ProductsInBasket;
    }
}

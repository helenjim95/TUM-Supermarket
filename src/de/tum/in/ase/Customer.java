package de.tum.in.ase;

public class Customer {
    private Stack<Product> productsInBasket;
    private String name;
    private double money;

    public Customer(String name, double money) throws IllegalArgumentException {
        if (name == null || money < 0) {
            throw new IllegalArgumentException();
        } else {
            this.name = name;
            this.money = money;
            this.productsInBasket = new LinkedStack<>();
        }
    }

    public String getName() {
        return this.name;
    }

    public double getMoney() {
        return this.money;
    }

    public Stack<Product> getProductsInBasket() {
        return this.productsInBasket;
    }

    public boolean hasMoney() {
        return money > 0;
    }

    public void addProductToBasket(Product product) {
        if (product != null) {
            if (this.productsInBasket == null) {
                this.productsInBasket = new LinkedStack<>();
            } else {
                this.productsInBasket.push(product);
            }
        }
    }
    public void placeAllProductsOnBand(Queue<Product> band) {
        while (!this.productsInBasket.isEmpty()) {
            Product product = this.productsInBasket.pop();
            band.enqueue(product);
        }
    }

    public void takeAllProductsFromBand(Queue<Product> band) {
//        TODO: only put the products (originally from basket) bach to basket
        while(!band.isEmpty()) {
            Product product = band.dequeue();
            if (this.productsInBasket != null) {
                this.addProductToBasket(product);
            }
        }
    }
    public void pay(double amount) throws UnsupportedOperationException {
        if (amount < 0 || this.money < amount) {
            throw new UnsupportedOperationException();
        } else {
            this.money -= amount;
        }
    }
    public void goToCheckout(TUMSupermarket supermarket) throws IllegalArgumentException {
        if (supermarket == null) {
            throw new IllegalArgumentException();
        } else {
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
    }

    @Override
    public String toString() {
        return "Customer: " + name + ", money: " + money + "\n" + productsInBasket;
    }
}

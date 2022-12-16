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
        return this.money > 0;
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
        Product product = this.productsInBasket.pop();
        while(product != null) {
            band.enqueue(product);
            product = this.productsInBasket.pop();
        }
    }

    public void takeAllProductsFromBand(Queue<Product> band) {
//        TODO: only put the products (originally from basket) bach to basket
        Product product = band.dequeue();
        while(product != null) {
            this.productsInBasket.push(product);
            product = band.dequeue();
        }
    }
    public void pay(double amount) {
//        System.out.println("Paying:" + amount);
        if (amount < 0 || this.money < amount) {
//            TODO: need to delete
//            System.out.println("Not enough money");
//            System.out.println("Amount left:" + this.money);
            throw new UnsupportedOperationException();
        } else {
            this.money -= amount;
//            TODO: need to delete
//            System.out.println("Amount left:" + this.money);
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
        return "Customer: " + this.name + ", money: " + this.money + "\n" + this.productsInBasket;
    }
}

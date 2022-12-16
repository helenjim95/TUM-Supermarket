package de.tum.in.ase;

public class Checkout {

    private Queue<Customer> customers;
    private Queue<Product> bandBeforeCashier;
    private Queue<Product> bandAfterCashier;

    public Checkout() {
        this.customers = new LinkedQueue<>();
        this.bandBeforeCashier = new LinkedQueue<>();
        this.bandAfterCashier = new LinkedQueue<>();
    }

    public Queue<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(LinkedQueue<Customer> customers) {
        this.customers = customers;
    }

    public Queue<Product> getBandBeforeCashier() {
        return this.bandBeforeCashier;
    }

    public void setBandBeforeCashier(LinkedQueue<Product> bandBeforeCashier) {
        this.bandBeforeCashier = bandBeforeCashier;
    }

    public Queue<Product> getBandAfterCashier() {
        return this.bandAfterCashier;
    }

    public void setBandAfterCashier(LinkedQueue<Product> bandAfterCashier) {
        this.bandAfterCashier = bandAfterCashier;
    }

    public int customerQueueLength() {
        return this.customers.size();
    }

    public void enqueueCustomers(Queue<Customer> newCustomers) {
        while (!this.customers.isEmpty()) {
            this.customers.enqueue(newCustomers.dequeue());
        }
    }

    public void serveNextCustomer() {
        if (this.customers.size() == 0) {
            throw new UnsupportedOperationException();
        } else {
            Customer customer = this.customers.dequeue();
            Stack<Product> products = customer.getProductsInBasket();
            customer.placeAllProductsOnBand(this.bandBeforeCashier);
            Product product = this.bandBeforeCashier.dequeue();
            double totalPrice = 0;
            while (product != null) {
                this.bandAfterCashier.enqueue(product);
                totalPrice += product.getPrice();
                product = this.bandBeforeCashier.dequeue();
            }
            customer.takeAllProductsFromBand(this.bandAfterCashier);
            customer.pay(totalPrice);
        }
    }
}

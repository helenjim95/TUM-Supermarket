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
        return bandBeforeCashier;
    }

    public void setBandBeforeCashier(LinkedQueue<Product> bandBeforeCashier) {
        this.bandBeforeCashier = bandBeforeCashier;
    }

    public Queue<Product> getBandAfterCashier() {
        return bandAfterCashier;
    }

    public void setBandAfterCashier(LinkedQueue<Product> bandAfterCashier) {
        this.bandAfterCashier = bandAfterCashier;
    }

    public int customerQueueLength() {
        return customers.size();
    }

    public void enqueueCustomers(Queue<Customer> newCustomers) {
        while (!customers.isEmpty()) {
            this.customers.enqueue(newCustomers.dequeue());
        }
    }

    public void serveNextCustomer() throws UnsupportedOperationException {
        if (this.customerQueueLength() == 0 || this.customers == null || this.customers.size() == 0) {
            throw new UnsupportedOperationException();
        } else {
            Customer customer = customers.dequeue();
//            TODO: productsInBasket is empty somehow
            Stack<Product> products = customer.getProductsInBasket();
            customer.placeAllProductsOnBand(bandBeforeCashier);
//            TODO: only put the products customer put (bandAfter - bandBefore)
//            while (bandBeforeCashier.size() > bandBeforeCashier.size())
            customer.takeAllProductsFromBand(bandBeforeCashier);
            int totalPrice = 0;
            for (int i = 0; i < products.size(); i++) {
                Product product = products.pop();
                totalPrice += product.getPrice();
            }
//            try {
            customer.pay(totalPrice);
//            } catch (UnsupportedOperationException e) {
//                System.out.println("Customer has insufficient fund");
//            }
        }
    }
}

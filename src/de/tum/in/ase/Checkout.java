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

//    TODO: java.lang.NullPointerException: Cannot invoke "Object.getClass()" because "o" is null
    public void serveNextCustomer() {
        if (customerQueueLength() == 0) {
            throw new UnsupportedOperationException();
        } else {
            Customer customer = customers.dequeue();
            Stack products = customer.getProductsInBasket();
            customer.placeAllProductsOnBand(bandAfterCashier);
//            TODO: only put the products customer put (bandAfter - bandBefore)
            customer.takeAllProductsFromBand(bandAfterCashier);
            int totalPrice = 0;
            for (int i = 0; i < products.size(); i++) {
                Product product = (Product) products.pop();
                totalPrice += product.getPrice();
            }
            customer.pay(totalPrice);
        }
    }
}

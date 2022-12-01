package de.tum.in.ase;

public class Checkout {

    private LinkedQueue<Customer> customers;
    private LinkedQueue<Product> bandBeforeCashier;
    private LinkedQueue<Product> bandAfterCashier;

    public Checkout(LinkedQueue<Customer> customers, LinkedQueue<Product> bandBeforeCashier, LinkedQueue<Product> bandAfterCashier) {
        this.customers = customers;
        this.bandBeforeCashier = bandBeforeCashier;
        this.bandAfterCashier = bandAfterCashier;
    }

    public LinkedQueue<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(LinkedQueue<Customer> customers) {
        this.customers = customers;
    }

    public LinkedQueue<Product> getBandBeforeCashier() {
        return bandBeforeCashier;
    }

    public void setBandBeforeCashier(LinkedQueue<Product> bandBeforeCashier) {
        this.bandBeforeCashier = bandBeforeCashier;
    }

    public LinkedQueue<Product> getBandAfterCashier() {
        return bandAfterCashier;
    }

    public void setBandAfterCashier(LinkedQueue<Product> bandAfterCashier) {
        this.bandAfterCashier = bandAfterCashier;
    }

    public int customerQueueLength() {
        return customers.size();
    }

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

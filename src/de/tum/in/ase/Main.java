package de.tum.in.ase;

public class Main {

    public static void main(String[] args) {
        Queue<Customer> customers = generateCustomers(5);
        Checkout[] checkouts1 = generateCheckouts(5);
        Checkout checkout1 = checkouts1[0];
        Checkout[] checkouts2 = generateCheckouts(2);
        TUMSupermarket supermarket = new TUMSupermarket(2);
        Customer customer1 = checkout1.getCustomers().dequeue();
        Stack<Product> productsInBasket = generateProducts(5);
        while (productsInBasket.size() > 0) {
            Product product = productsInBasket.pop();
            customer1.addProductToBasket(product);
        }
        Queue<Product> band = new LinkedQueue<>();
        checkout1.serveNextCustomer();
        System.out.println(supermarket.getCheckouts().length);
        supermarket.closeCheckout(0);
        System.out.println(supermarket.getCheckouts().length);
    }

    protected static Stack<Product> generateProducts(int number) {
        Stack<Product> products = new LinkedStack<Product>();
        for (int i = 0; i < number; i++) {
            Product product = new Product("product" + i, Math.random() * 100);
            products.push(product);
        }
        return products;
    }

    protected static Queue<Customer> generateCustomers(int number) {
        Queue<Customer> customers = new LinkedQueue<>();
        for (int i = 0; i < number; i++) {
            Customer customer = new Customer("customer" + i, Math.random() * 100);
            customers.enqueue(customer);
        }
        return customers;
    }

    protected static Checkout[] generateCheckouts(int number) {
        Checkout [] checkouts = new Checkout[number];
        for (int i = 0; i < number; i++) {
            Checkout checkout = new Checkout();
            Queue<Customer> customers = generateCustomers(number);
            checkout.setCustomers((LinkedQueue<Customer>) customers);
            checkouts[i] = checkout;
        }
        return checkouts;
    }

}

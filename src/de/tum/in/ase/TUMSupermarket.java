package de.tum.in.ase;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TUMSupermarket {
    private Checkout [] checkouts;

    public TUMSupermarket(int numberOfCheckouts) throws IllegalArgumentException {
        if (numberOfCheckouts <= 0) {
            throw new IllegalArgumentException();
        } else {
            this.checkouts = new Checkout[numberOfCheckouts];
            Checkout checkout = new Checkout();
            Arrays.fill(this.checkouts, checkout);
        }
    }

    public Checkout[] getCheckouts() {
        return this.checkouts;
    }

    public Checkout getCheckoutWithSmallestQueue() {
        Checkout currentCheckout = this.checkouts[0];
        int smallestQueueSize = currentCheckout.customerQueueLength();
        for (Checkout checkout : this.checkouts) {
            if (checkout.customerQueueLength() < smallestQueueSize) {
                currentCheckout = checkout;
                smallestQueueSize = checkout.customerQueueLength();
            }
        }
        return currentCheckout;
    }

//    TODO: took too long
    public void closeCheckout(int index) throws IllegalArgumentException {
        int checkoutLength = this.checkouts.length;
        if (index < 0 || index >= checkoutLength) {
            throw new IllegalArgumentException();
        } else {
            Checkout removedCheckout = this.checkouts[index];
            Checkout checkoutWithShortestQueue = this.getCheckoutWithSmallestQueue();
            checkoutWithShortestQueue.enqueueCustomers(removedCheckout.getCustomers());

            Checkout [] newCheckouts = (Checkout[]) Arrays.stream(this.checkouts)
                    .filter(checkout -> !checkout.equals(removedCheckout))
                    .toArray();

            this.checkouts = newCheckouts;
        }
    }


    public void serveCustomers() throws UnsupportedOperationException {
        for (Checkout checkout : this.checkouts) {
                checkout.serveNextCustomer();
        }
    }
}

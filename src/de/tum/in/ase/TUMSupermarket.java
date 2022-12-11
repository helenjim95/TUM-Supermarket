package de.tum.in.ase;

import java.util.Arrays;

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
            Checkout [] newCheckouts = new Checkout[checkoutLength - 1];
//            If the index is the last element
            if (index == checkoutLength - 1) {
                System.arraycopy(this.checkouts, 0, newCheckouts, 0, newCheckouts.length);
            } else if (index == 0) {
                System.arraycopy(this.checkouts, 1, newCheckouts, 0, newCheckouts.length);
            } else {
                System.arraycopy(this.checkouts, 0, newCheckouts, 0, index);
                if (newCheckouts.length - index >= 0)
                    System.arraycopy(this.checkouts, index + 1, newCheckouts, index, newCheckouts.length - index);
            }
            this.checkouts = newCheckouts;
        }
    }


    public void serveCustomers() throws UnsupportedOperationException {
        for (Checkout checkout : this.checkouts) {
                checkout.serveNextCustomer();
        }
    }
}

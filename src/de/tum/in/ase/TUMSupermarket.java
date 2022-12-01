package de.tum.in.ase;

public class TUMSupermarket {
    private Checkout [] checkouts;

    public TUMSupermarket(int numberOfCheckouts) {
        if (numberOfCheckouts <= 0) {
            throw new IllegalArgumentException();
        } else {
            this.checkouts = new Checkout[numberOfCheckouts];
            Checkout checkout = new Checkout();
            for (int i = 0; i < checkouts.length; i++) {
                this.checkouts[i] = checkout;
            }
        }
    }

    public Checkout[] getCheckouts() {
        return checkouts;
    }

    public Checkout getCheckoutWithSmallestQueue() {
        Checkout currentCheckout = checkouts[0];
        int smallestQueueSize = currentCheckout.customerQueueLength();
        for (Checkout checkout : checkouts) {
            if (checkout.customerQueueLength() < smallestQueueSize) {
                currentCheckout = checkout;
                smallestQueueSize = checkout.customerQueueLength();
            }
        }
        return currentCheckout;
    }

//    TODO: delete the Checkout-object with the given index
    public void closeCheckout(int index) {
        if (index < 0 || index >= checkouts.length) {
            throw new IllegalArgumentException();
        } else {
//            the size of the Checkout array must be decreased by 1
//            (-> there has to be no null-value in it) and
//            it should contain all the other Checkout-objects in the same order as before.
//            Then all Customers from the removed Checkout must go to the next-shortest Queue.
//            NOTE:Pay attention that the last Customer of the deleted Checkout is the first who leaves the Queue.
        }
    }

    public void serveCustomers() {
        for (Checkout checkout : checkouts) {
            checkout.serveNextCustomer();
        }
    }

}

package de.tum.in.ase;

public class TUMSupermarket {
    private Checkout [] checkouts;

    public TUMSupermarket(int numberOfCheckouts) throws IllegalArgumentException {
        if (numberOfCheckouts <= 0) {
            throw new IllegalArgumentException();
        } else {
            this.checkouts = new Checkout[numberOfCheckouts];
            Checkout checkout = new Checkout();
            for (int i = 0; i < this.checkouts.length; i++) {
                this.checkouts[i] = checkout;
            }
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

//    TODO: loop didn't end?
    public void closeCheckout(int index) throws IllegalArgumentException {
        if (index < 0 || index >= this.checkouts.length) {
            throw new IllegalArgumentException();
        } else {
            Checkout removedCheckout = this.checkouts[index];
            Checkout checkoutWithShortestQueue = this.getCheckoutWithSmallestQueue();
            checkoutWithShortestQueue.enqueueCustomers(removedCheckout.getCustomers());
            int checkoutLength = this.checkouts.length;
//            If the index is the last element
            if (index == checkoutLength - 1) {
                Checkout [] newCheckouts = new Checkout[checkoutLength - 1];
                for (int i = 0; i < newCheckouts.length; i++) {
                    newCheckouts[i] = this.checkouts[i];
                }
                this.checkouts = newCheckouts;
            } else {
                Checkout [] newCheckouts = new Checkout[checkoutLength - 1];
                for (int i = 0; i < index; i++) {
                    newCheckouts[i] = this.checkouts[i];
                }
                for (int i = index; i < newCheckouts.length; i++) {
                    newCheckouts[i] = this.checkouts[i + 1];
                }
                this.checkouts = newCheckouts;
            }
        }
    }


    public void serveCustomers() throws UnsupportedOperationException {
        for (Checkout checkout : this.checkouts) {
                checkout.serveNextCustomer();
        }
    }
}

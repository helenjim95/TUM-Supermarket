package de.tum.in.ase;

public class TUMSupermarket {
    private Checkout [] checkouts;

    public TUMSupermarket(int numberOfCheckouts) throws IllegalArgumentException {
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
    public void closeCheckout(int index) throws IllegalArgumentException {
        if (index < 0 || index >= this.checkouts.length) {
            throw new IllegalArgumentException();
        } else {
            Checkout removedCheckout = this.checkouts[index];
            Checkout checkoutWithShortestQueue = this.getCheckoutWithSmallestQueue();
            checkoutWithShortestQueue.enqueueCustomers(removedCheckout.getCustomers());

            for (int i = index; i < this.checkouts.length - 1; i++) {
                System.out.println("closing" + i + " checkout");
                this.checkouts[i] = this.checkouts[i + 1];
                System.out.println("checkout" + i + ": " + this.checkouts[i]);
                System.out.println("checkout" + (i + 1) + ": " + this.checkouts[i + 1]);
            }

            Checkout[] newCheckouts = new Checkout[this.checkouts.length - 1];
            for (int i = 0; i < newCheckouts.length; i++) {
                newCheckouts[i] = this.checkouts[i];
            }
            this.checkouts = newCheckouts;
            System.out.println("checkout" + (this.checkouts.length - 1) + ": " + this.checkouts[this.checkouts.length - 1]);
        }
    }


    public void serveCustomers() throws UnsupportedOperationException {
        for (Checkout checkout : checkouts) {
            try {
                checkout.serveNextCustomer();
            } catch (UnsupportedOperationException e) {
                System.out.println("No customer is waiting");
            }
        }
    }
}

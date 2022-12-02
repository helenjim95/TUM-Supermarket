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
//            for (int i = index; i < this.checkouts.length - 1; i++) {
//                this.checkouts[i] = this.checkouts[i + 1];
//            }

            int count =0;
            for(int i = 0; i < checkouts.length; i++){
                if(i == index){
                    count++;

                    // shifting elements
                    for(int k = i; k < checkouts.length - 1; k++){
                        checkouts[k] = checkouts[k+1];
                    }
                    i--;
                    // break;
                }
            }


//            the size of the Checkout array must be decreased by 1
//            (-> there has to be no null-value in it) and
//            it should contain all the other Checkout-objects in the same order as before.
//            Then all Customers from the removed Checkout must go to the next-shortest Queue.
//            NOTE:Pay attention that the last Customer of the deleted Checkout is the first who leaves the Queue.
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

package ChainOfResponsibility;

// Handler: Abstract class
abstract class Handler {
    protected Handler nextHandler;

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleOrder(int orderSize);
}

// Concrete Handler: Kitchen (Rotis in batch of 10)
class KitchenHandler extends Handler {
    private static final int BATCH_SIZE = 10;
    private int batchPool = 0; // Rotis available in the current batch

    @Override
    public void handleOrder(int orderSize) {
        while (orderSize > 0) {
            if (batchPool == 0) { // If no rotis are available, prepare a new batch
                prepareBatch();
            }

            int servedRotis = Math.min(orderSize, batchPool);
            batchPool -= servedRotis;
            orderSize -= servedRotis;

            System.out.println("Serving " + servedRotis + " rotis from the batch. Rotis left in batch: " + batchPool);

            // Pass served rotis to the waiter
            if (nextHandler != null) {
                nextHandler.handleOrder(servedRotis);
            }
        }
    }

    private void prepareBatch() {
        try {
            System.out.println("Preparing a new batch of " + BATCH_SIZE + " rotis...");
            Thread.sleep(2000); // Simulate batch preparation time
        } catch (InterruptedException e) {
            e.fillInStackTrace();
        }
        batchPool = BATCH_SIZE;
        System.out.println("Batch prepared. " + batchPool + " rotis are now ready.");
    }
}

// Concrete Handler: Waiter
class WaiterHandler extends Handler {
    @Override
    public void handleOrder(int orderSize) {
        System.out.println("Waiter serving: " + orderSize + " rotis");
    }
}

// Main Class
public class RotiHotel {
    public static void main(String[] args) {
        int[] orders = {4, 5, 3, 10}; // Customer orders

        // Set up the chain of responsibility
        Handler kitchenHandler = new KitchenHandler();
        Handler waiterHandler = new WaiterHandler();
        kitchenHandler.setNextHandler(waiterHandler);

        // Process each customer order
        for (int i = 0; i < orders.length; i++) {
            System.out.println("\nProcessing order for Customer " + (i + 1) + ": " + orders[i] + " rotis");
            kitchenHandler.handleOrder(orders[i]);
        }
    }
}

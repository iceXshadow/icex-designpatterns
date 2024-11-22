package Strategy;

interface PaymentStrategy {
    void pay(int amt);
}

class CreditCard implements PaymentStrategy {
    @Override
    public void pay(int amt) {
        System.out.println("Payment by cc for amount: " + amt + " completed.");
    }
}

class WireTransfer implements PaymentStrategy {
    @Override
    public void pay(int amt) {
        System.out.println("Payment by bank for amount: " + amt + " completed.");
    }
}

class PhonePay implements PaymentStrategy {
    @Override
    public void pay(int amt) {
        System.out.println("Payment by upi for amount: " + amt + " completed.");
    }
}

class PaymentContext {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy ps) {
        this.paymentStrategy = ps;
    }

    public void makePayment(int amount) {
        if (paymentStrategy == null) {
            System.out.println("set a payment strategy.");
        } else {
            paymentStrategy.pay(amount);
        }
    }
}

public class PaymentStrategyDemo {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        context.setPaymentStrategy(new PhonePay());
        context.makePayment(100);

        context.setPaymentStrategy(new WireTransfer());
        context.makePayment(10000000);
    }
}

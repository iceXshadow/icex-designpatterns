package Observer;

import java.util.ArrayList;
import java.util.List;

// Observer interface
interface Observer {
    void update(float price);
}

// Subject interface
interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyAllObservers();
}

// Concrete Subject class
class Stock implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private float price;

    public void setPrice(float price) {
        this.price = price;
        notifyAllObservers();
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (Observer obs : observers) {
            obs.update(price);
        }
    }
}

// Concrete Observers
class Investor implements Observer {
    private String name;
    public Investor (String name) {
        this.name = name;
    }

    @Override
    public void update(float price) {
        System.out.println("Investor " + name + " notified! Price updated to: " + price);
    }
}

class StockBroker implements Observer {
    @Override
    public void update(float price) {
        System.out.println("Stock Broker notified! Price updated to: " + price);
    }
}

public class StockMarket {
    public static void main(String[] args) {
        Stock stock = new Stock();

        Observer investor1 = new Investor("Alice");
        Observer investor2 = new Investor("Bob");
        Observer sb = new StockBroker();

        stock.attach(investor1);
        stock.attach(investor2);
        stock.attach(sb);

        stock.setPrice(110.00f);
        stock.detach(investor2);

        stock.setPrice(150.00f);
    }
}

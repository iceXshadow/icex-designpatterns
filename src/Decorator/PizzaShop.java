package Decorator;

abstract class Pizza {
    String desc = "Unknown pizza";

    public String getDesc() {
        return desc;
    }

    public abstract double cost();
}

class ThinCrust extends Pizza {
    public ThinCrust() {
        desc = "Thin Crust";
    }

    @Override
    public double cost() {
        return 5.00;
    }
}

class RegularCrust extends Pizza {
    public RegularCrust() {
        desc = "Regular";
    }

    @Override
    public double cost() {
        return 6.00;
    }
}

abstract class ToppingsDecorator extends Pizza {
    public abstract String getDesc();
}

class Cheese extends ToppingsDecorator {
    Pizza base;
    public Cheese (Pizza pizza) {
        this.base = pizza;
    }

    public String getDesc() {
        return base.getDesc() + ", Cheese";
    }

    @Override
    public double cost() {
        return base.cost() + 1.00;
    }
}

class Olives extends ToppingsDecorator {
    Pizza base;
    public Olives (Pizza pizza) {
        this.base = pizza;
    }

    public String getDesc() {
        return base.getDesc() + ", Olives";
    }

    @Override
    public double cost() {
        return base.cost() + 1.50;
    }
}

public class PizzaShop {
    public static void main(String[] args) {
        Pizza order1 = new RegularCrust();
        order1 = new Cheese(order1);
        System.out.println(order1.getDesc() + " => $" + order1.cost());

        order1 = new Olives(order1);
        System.out.println(order1.getDesc() + " => $" + order1.cost());
    }
}
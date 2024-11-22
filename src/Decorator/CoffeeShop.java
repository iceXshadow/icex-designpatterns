package Decorator;

abstract class Beverage {
    String desc = "Unknown beverage";

    public String getDesc() {
        return desc;
    }

    public abstract double cost();
}

class Espresso extends Beverage {
    public Espresso() {
        desc = "Espresso";
    }

    @Override
    public double cost() {
        return 2.50;
    }
}

class Latte extends Beverage {
    public Latte() {
        desc = "Latte";
    }

    @Override
    public double cost() {
        return 3.00;
    }
}

abstract class AddOnDecorator extends Beverage{
    public abstract String getDesc();
}

class Milk extends AddOnDecorator {
    Beverage base;
    public Milk(Beverage bev) {
        this.base = bev;
    }

    @Override
    public String getDesc() {
        return base.getDesc() + ", Milk";
    }

    public double cost() {
        return base.cost() + 1.50;
    }
}

class Sugar extends AddOnDecorator {
    Beverage base;
    public Sugar(Beverage bev) {
        this.base = bev;
    }

    @Override
    public String getDesc() {
        return base.getDesc() + ", Sugar";
    }

    public double cost() {
        return base.cost() + 1.00;
    }
}

public class CoffeeShop {
    public static void main(String[] args) {
        Beverage order1 = new Espresso();
        order1 = new Milk(order1);
        System.out.println(order1.getDesc() + " => $" + order1.cost());

        Beverage order2 = new Latte();
        order2 = new Milk(order2);
        order2 = new Sugar(order2);
        System.out.println(order2.getDesc() + " => $" + order2.cost());
    }
}

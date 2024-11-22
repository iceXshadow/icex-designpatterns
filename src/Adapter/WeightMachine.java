package Adapter;

// target interface
interface WeightInKg {
    double getWeightInKg();
}

// adaptee : machine outputs wt in ponuds
class WtMachine {
    public double getWeightInPounds() {
        return 150.0; // sample weight
    }
}

// adaptor : converts pounds to kgs for client
class WeightAdaptor implements WeightInKg {
    private WtMachine wtmc;

    public WeightAdaptor(WtMachine wtmc) {
        this.wtmc = wtmc;
    }

    @Override
    public double getWeightInKg() {
        double weightInPounds = wtmc.getWeightInPounds();
        return convertPoundsToKg(weightInPounds);
    }

    private double convertPoundsToKg(double wt) {
        return wt * 0.453592;
    }
}

public class WeightMachine {
    public static void main(String[] args) {
        WtMachine machine = new WtMachine();

        WeightInKg wtadapter = new WeightAdaptor(machine);
        double wt = wtadapter.getWeightInKg();
        System.out.println("Converted weight: " + wt + "kg");
    }
}

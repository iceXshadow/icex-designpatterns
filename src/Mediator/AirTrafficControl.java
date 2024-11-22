package Mediator;

import java.util.ArrayList;
import java.util.List;

// interface mediator
interface ATC {
    void sendMsg(String msg, Flight sender);
    void addFlight(Flight f);
}

// concrete mediator
class ATCMediator implements ATC {
    private List<Flight> flights;

    public ATCMediator() {
        this.flights = new ArrayList<>();
    }

    @Override
    public void sendMsg(String msg, Flight sender) {
        for (Flight f : flights) {
            if (f != sender) {
                f.receive(msg, sender);
            }
        }
    }

    @Override
    public void addFlight(Flight f) {
        flights.add(f);
    }
}

class Flight {
    private String fno;
    private ATC mediator;

    public Flight(String fno, ATC mediator) {
        this.fno = fno;
        this.mediator = mediator;
    }

    public void send(String msg) {
        System.out.println(fno + " sending message: " + msg);
        mediator.sendMsg(msg, this);
    }

    public void receive(String msg, Flight sender) {
        System.out.println(fno + " received message from: " + sender.getFlightNo() + ": " + msg);
    }

    public String getFlightNo() {
        return fno;
    }
}

public class AirTrafficControl {
    public static void main(String[] args) {
        ATC mediator = new ATCMediator();

        Flight f1 = new Flight("F01", mediator);
        Flight f2 = new Flight("F02", mediator);
        Flight f3 = new Flight("F03", mediator);
        Flight f4 = new Flight("F04", mediator);
        Flight f5 = new Flight("F05", mediator);

        mediator.addFlight(f1);
        mediator.addFlight(f2);
        mediator.addFlight(f3);
        mediator.addFlight(f4);
        mediator.addFlight(f5);

        f1.send("Requesting permission for landing.");
        f2.send("Takeoff successful on route 5.");

    }
}

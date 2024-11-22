package ChainOfResponsibility;

// Handler
abstract class AuthHandler {
    protected AuthHandler nextHandler;

    public void setNextHandler(AuthHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public boolean handle (Request req) {
        if (nextHandler != null) {
            return nextHandler.handle(req);
        }
        return true;
    }
}

// Request class
class Request {
    private String email;
    private String password;
    private String payment;

    public Request (String email, String password, String payment) {
        this.email = email;
        this.password = password;
        this.payment = payment;
    }

    public String getEmail() {
        return email;
    }

    public String getPayment() {
        return payment;
    }

    public String getPassword() {
        return password;
    }
}

// Concrete Handlers

class EmailHandler extends AuthHandler {
    @Override
    public boolean handle(Request req) {
        System.out.println("Validating Email...");
        if (!req.getEmail().contains("@") || !req.getEmail().contains(".")) {
            System.out.println("Enter valid email!");
            return false;
        }
        return super.handle(req);
    }
}

class PasswordHandler extends AuthHandler {
    @Override
    public boolean handle(Request req) {
        System.out.println("Validating password...");
        if (req.getPassword().length() < 6) {
            System.out.println("Password length should be longer!");
            return false;
        }
        return super.handle(req);
    }
}

class PaymentHandler extends AuthHandler {
    @Override
    public boolean handle(Request req) {
        System.out.println("Validating payment details...");
        if (req.getPayment().length() < 12) {
            System.out.println("Recheck credentials!");
            return false;
        }
        return super.handle(req);
    }
}

public class Authentication {
    public static void main(String[] args) {
        Request userRequest = new Request("saket@gmail.com", "password", "123123123123");

        AuthHandler emailH = new EmailHandler();
        AuthHandler passH = new PasswordHandler();
        AuthHandler payH = new PaymentHandler();

        emailH.setNextHandler(passH);
        passH.setNextHandler(payH);

        System.out.println("Processing Order...");
        if (emailH.handle(userRequest)) {
            System.out.println("Order placed successfully!");
        } else {
            System.out.println("Order placement failed due to validation errors.");
        }
    }
}

package Proxy;

interface FileAccess {
    void accessFile(String filename) throws Exception;
}

class RealFileAccess implements FileAccess {
    @Override
    public void accessFile(String filename) {
        System.out.println("Accessing the file: " + filename);
    }
}

class ProxyFileAccess implements FileAccess {
    private RealFileAccess real;
    private String userRole;

    public ProxyFileAccess (String userRole) {
        this.userRole = userRole;
        this.real = new RealFileAccess();
    }

    @Override
    public void accessFile(String filename) throws Exception {
        if ("ADMIN".equalsIgnoreCase(userRole)) {
            real.accessFile(filename);
        } else {
            throw new Exception("Access denied");
        }
    }
}

public class FileAccessControlSystem {
    public static void main(String[] args) {
        try {
            FileAccess admin = new ProxyFileAccess("admin");
            admin.accessFile("confidential.txt");

            FileAccess user = new ProxyFileAccess("user");
            user.accessFile("confidential.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

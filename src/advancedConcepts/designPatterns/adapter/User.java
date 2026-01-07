package advancedConcepts.designPatterns.adapter;

public class User {
    private String name;
    private String password;
    private String upiId;
    private int upiPin;

    public User(String name, String password, String upiId, int upiPin) {
        this.name = name;
        this.password = password;
        this.upiId = upiId;
        this.upiPin = upiPin;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUpiId() {
        return upiId;
    }

    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }

    public int getUpiPin() {
        return upiPin;
    }

    public void setUpiPin(int upiPin) {
        this.upiPin = upiPin;
    }
}

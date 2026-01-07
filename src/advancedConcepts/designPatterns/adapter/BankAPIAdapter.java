package advancedConcepts.designPatterns.adapter;


//these functionalities we will get from some bank
public interface BankAPIAdapter {
    double checkBalance(User user);
    int doTransaction(User fromUser, User toUser, double amount);
    boolean changePin(User user, int currentPin, int newPin);
}

package advancedConcepts.designPatterns.adapter;

public class AxisBankAPIAdapter implements BankAPIAdapter{
    @Override
    public double checkBalance(User user) {
        return 0;
    }

    @Override
    public int doTransaction(User fromUser, User toUser, double amount) {
        return 0;
    }

    @Override
    public boolean changePin(User user, int currentPin, int newPin) {
        return false;
    }
}

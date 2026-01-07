package advancedConcepts.designPatterns.adapter;

import advancedConcepts.designPatterns.adapter.yesbank.YesBankAPI;

public class YesBankAPIAdapter implements BankAPIAdapter {

    private YesBankAPI yesBankAPI;

    public YesBankAPIAdapter() {
        this.yesBankAPI = new YesBankAPI();
    }

    @Override
    public double checkBalance(User user) {
        long bal = yesBankAPI.getBalance(user.getName(), user.getPassword());
        double balD = (double) bal;
        return balD;
    }

    @Override
    public int doTransaction(User fromUser, User toUser, double amount) {
        char status = yesBankAPI.doTransaction(fromUser.getName(), toUser.getName(), fromUser.getPassword(), amount);
        if(status == 'Y') {
            return 1;
        } else if(status == 'N') {
            return 0;
        } else {
            return 2;
        }
    }

    @Override
    public boolean changePin(User user, int currentPin, int newPin) {
        return false;
    }
}

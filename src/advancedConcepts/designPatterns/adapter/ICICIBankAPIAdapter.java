package advancedConcepts.designPatterns.adapter;

import advancedConcepts.designPatterns.adapter.iciciBank.ICICIBankAPI;

public class ICICIBankAPIAdapter implements BankAPIAdapter {

    private ICICIBankAPI iciciBankAPI;

    public ICICIBankAPIAdapter() {
        this.iciciBankAPI = new ICICIBankAPI();
    }

    @Override
    public double checkBalance(User user) {
        String userToken = generateUserToken(user);
        double bal = iciciBankAPI.checkBalance(userToken);
        return bal;
    }

    @Override
    public int doTransaction(User fromUser, User toUser, double amount) {
        String fromUserToken = generateUserToken(fromUser);
        String toUserToken = generateUserToken(toUser);
        return iciciBankAPI.transferMoney(fromUserToken, toUserToken, amount);
    }

    @Override
    public boolean changePin(User user, int currentPin, int newPin) {
        return false;
    }

    private String generateUserToken(User user){
        return "userTokenString";
    }
}

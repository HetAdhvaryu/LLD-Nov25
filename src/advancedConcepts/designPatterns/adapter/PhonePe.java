package advancedConcepts.designPatterns.adapter;

public class PhonePe {

    private BankAPIAdapter bankAPIAdapter;

    public PhonePe() {
        this.bankAPIAdapter = new AxisBankAPIAdapter();
    }

    /*
    // very bad idea to directly depend on bank APIs
    //private YesBankAPI yesBankAPI;
    //private ICICIBankAPI iciciBankAPI;

     */

    public double checkBalance(User user) {
        /*
//        yesBankAPI.getBalance(user.getName(), user.getPassword());
//
//        String userToken = "somehow generate the token here";
//        iciciBankAPI.checkBalance(userToken);

         */


        double d = bankAPIAdapter.checkBalance(user);
        System.out.println("Balance = " + d);
        return d;
    }

    public void transferMoney(User fromUser, User toUser, double amount) {
        if(bankAPIAdapter.checkBalance(fromUser) < amount){
            System.out.println("Transaction failed");
            return;
        }

        int status = bankAPIAdapter.doTransaction(fromUser, toUser, amount);
        switch(status){
            case 1 :
                System.out.println("Transaction Successful");
                break;
            case 0 :
                System.out.println("Transaction Failed");
                break;
            case 2 :
                System.out.println("Transaction IN PROGRESS");
                break;
        }
    }
}

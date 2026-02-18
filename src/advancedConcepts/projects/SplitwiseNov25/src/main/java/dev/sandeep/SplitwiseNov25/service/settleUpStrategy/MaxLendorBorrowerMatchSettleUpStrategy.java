package dev.sandeep.SplitwiseNov25.service.settleUpStrategy;

import dev.sandeep.SplitwiseNov25.entity.*;

import java.util.*;

public class MaxLendorBorrowerMatchSettleUpStrategy implements SettleUpStrategy {

    @Override
    public List<Transaction> settleUp(List<Expense> expenses) {
        //Calculating the outstanding
        HashMap<User, Double> userOutstandingMap = new HashMap<>();
        for(Expense expense : expenses) {
            for(PayoutLedger payoutLedger : expense.getPayoutLedgers()) {
                User user = payoutLedger.getUser();
                if(!userOutstandingMap.containsKey(user)) {
                    userOutstandingMap.put(user, 0.0);
                }
                if(payoutLedger.getPayoutType().equals(PayoutType.PAID)){
                    userOutstandingMap.put(
                            user,
                            userOutstandingMap.get(user) + payoutLedger.getAmount()
                    );
                } else {
                    userOutstandingMap.put(
                            user,
                            userOutstandingMap.get(user) - payoutLedger.getAmount()
                    );
                }
            }
        }
        //min Heap for borrowers and max heap for lenders
        PriorityQueue<UserExpensePair> borrowerHeap = new PriorityQueue<>(
                Comparator.comparingDouble(UserExpensePair::getAmount));
        PriorityQueue<UserExpensePair> lenderHeap = new PriorityQueue<>(
                Comparator.comparingDouble(UserExpensePair::getAmount).reversed());

        for(Map.Entry<User, Double> entry : userOutstandingMap.entrySet()) {
            if(entry.getValue() > 0.0) {
                lenderHeap.add(new UserExpensePair(entry.getKey(), entry.getValue()));
            } else if(entry.getValue() < 0.0) {
                borrowerHeap.add(new UserExpensePair(entry.getKey(), entry.getValue()));
            } else { // value is 0.0
                System.out.println("Congratulations, " + entry.getKey().getName() + ", you are already settled");
            }
        }

        //Start settling by picking the min from borrowers and max from lendors
        List<Transaction> transactions = new ArrayList<>();
        while (!borrowerHeap.isEmpty() && !lenderHeap.isEmpty()) {
            UserExpensePair borrowerPair = borrowerHeap.remove();
            UserExpensePair lenderPair = lenderHeap.remove();
            if(Math.abs(borrowerPair.getAmount()) > lenderPair.getAmount()) { // user1,-750   and user2,500
                //creation of transaction
                Transaction t = new Transaction();
                t.setAmount(lenderPair.getAmount());
                t.setPaidBy(borrowerPair.getUser());
                t.setReceivedBy(lenderPair.getUser());
                transactions.add(t);

                //cleanup
                //lender is settled here
                //update borrowers amount
                borrowerPair.setAmount(borrowerPair.getAmount() + lenderPair.getAmount()); // -ve + +ve => subtraction
                borrowerHeap.add(borrowerPair);
            } else if(Math.abs(borrowerPair.getAmount()) < lenderPair.getAmount()) { // user1,-500  and  user2,750
                //creation of transaction
                Transaction t = new Transaction();
                t.setAmount(Math.abs(borrowerPair.getAmount()));
                t.setPaidBy(borrowerPair.getUser());
                t.setReceivedBy(lenderPair.getUser());
                transactions.add(t);

                //cleanup
                //borrower is settled here
                //update lender's amount
                lenderPair.setAmount(borrowerPair.getAmount() + lenderPair.getAmount()); // -ve + +ve => subtraction
                lenderHeap.add(lenderPair);
            } else { //both are equal  // user1,-500 and user2,500
                //creation of transaction
                Transaction t = new Transaction();
                t.setAmount(lenderPair.getAmount());
                t.setPaidBy(borrowerPair.getUser());
                t.setReceivedBy(lenderPair.getUser());
                transactions.add(t);

                //cleanup for both
            }
        }

        return transactions;
    }
}
/*
        user1, -500           user2, 750
 */
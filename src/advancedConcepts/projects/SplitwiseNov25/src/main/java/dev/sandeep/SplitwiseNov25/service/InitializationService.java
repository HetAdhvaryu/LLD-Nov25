package dev.sandeep.SplitwiseNov25.service;

import dev.sandeep.SplitwiseNov25.entity.*;
import dev.sandeep.SplitwiseNov25.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class InitializationService {

    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private PayoutLedgerRepository payoutLedgerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    private final Random random = new Random();

    @Transactional
    public void initialize() {
        // 1. create users
        List<User> users = createUsers();

        // 2. create a group and add users
        Group group = createGroup(users);

        // 3. create expenses for the group (each with payout ledgers)
        List<Expense> expenses = createExpensesForGroup(users, 10);

        // 4. persist payout ledgers and expenses, then associate expenses to group
        List<Expense> savedExpenses = new ArrayList<>();
        for (Expense expense : expenses) {
            // save payout ledgers first so they become managed
            List<PayoutLedger> ledgers = expense.getPayoutLedgers();
            List<PayoutLedger> savedLedgers = payoutLedgerRepository.saveAll(ledgers);
            expense.setPayoutLedgers(savedLedgers);

            // save expense
            Expense savedExpense = expenseRepository.save(expense);
            savedExpenses.add(savedExpense);
        }

        // associate expenses with group and save group so FK column (group_id) is updated
        group.setExpenses(savedExpenses);
        groupRepository.save(group);
    }

    // --- helper methods for readability ---

    private List<User> createUsers() {
        List<User> users = new ArrayList<>();
        // 10 realistic Indian names with simple email and password
        String[][] persons = new String[][]{
                {"Arjun Sharma", "arjun.sharma@example.com", "password1"},
                {"Priya Patel", "priya.patel@example.com", "password2"},
                {"Rohit Kumar", "rohit.kumar@example.com", "password3"},
                {"Sneha Rao", "sneha.rao@example.com", "password4"},
                {"Vikram Singh", "vikram.singh@example.com", "password5"},
                {"Anita Desai", "anita.desai@example.com", "password6"},
                {"Karan Mehta", "karan.mehta@example.com", "password7"},
                {"Neha Iyer", "neha.iyer@example.com", "password8"},
                {"Suresh Nair", "suresh.nair@example.com", "password9"},
                {"Divya Gupta", "divya.gupta@example.com", "password10"}
        };

        for (String[] p : persons) {
            User u = new User();
            u.setName(p[0]);
            u.setEmail(p[1]);
            u.setPassword(p[2]);
            users.add(u);
        }

        // persist and return managed users
        return userRepository.saveAll(users);
    }

    private Group createGroup(List<User> users) {
        Group g = new Group();
        g.setName("Day Outing - Lakeside Picnic");
        // add all users to the group
        g.setUsers(users);
        // save to get an id; expenses/transactions will be associated later
        return groupRepository.save(g);
    }

    private List<Expense> createExpensesForGroup(List<User> users, int count) {
        List<Expense> expenses = new ArrayList<>();

        for (int i = 1; i <= count; i++) {
            // random amount between 300 and 5000 (two decimal precision)
            int minCents = 300 * 100;
            int maxCents = 5000 * 100;
            long totalCents = minCents + Math.abs(random.nextLong()) % (maxCents - minCents + 1);
            double amount = totalCents / 100.0;

            Expense expense = new Expense();
            expense.setAmount(amount);
            expense.setDescription("Expense #" + i + " - Day outing: miscellaneous");

            // pick random participants (at least 2)
            List<User> shuffled = new ArrayList<>(users);
            Collections.shuffle(shuffled, random);
            int k = 2 + random.nextInt(users.size() - 1); // between 2 and users.size()
            List<User> participants = new ArrayList<>(shuffled.subList(0, k));

            // pick number of payers between 1 and k-1 to ensure at least one ower
            int numPayers = 1 + random.nextInt(k - 1);
            Collections.shuffle(participants, random);
            List<User> payers = new ArrayList<>(participants.subList(0, numPayers));
            List<User> owers = new ArrayList<>(participants.subList(numPayers, participants.size()));

            // split total into cents for paid and owed groups
            int[] paidSplit = splitCents((int) totalCents, numPayers);
            int[] owedSplit = splitCents((int) totalCents, owers.size());

            List<PayoutLedger> ledgers = new ArrayList<>();
            // create payer ledgers
            for (int pi = 0; pi < payers.size(); pi++) {
                PayoutLedger pl = new PayoutLedger();
                pl.setUser(payers.get(pi));
                pl.setAmount(paidSplit[pi] / 100.0);
                pl.setPayoutType(PayoutType.PAID);
                ledgers.add(pl);
            }
            // create ower ledgers
            for (int oi = 0; oi < owers.size(); oi++) {
                PayoutLedger pl = new PayoutLedger();
                pl.setUser(owers.get(oi));
                pl.setAmount(owedSplit[oi] / 100.0);
                pl.setPayoutType(PayoutType.OWED);
                ledgers.add(pl);
            }

            expense.setPayoutLedgers(ledgers);
            expenses.add(expense);
        }

        return expenses;
    }

    /**
     * Split `totalCents` into `parts` positive integer cents that sum to totalCents.
     * Returns an int array of length `parts`.
     */
    private int[] splitCents(int totalCents, int parts) {
        if (parts <= 0) return new int[0];
        int[] result = new int[parts];
        if (parts == 1) {
            result[0] = totalCents;
            return result;
        }

        int[] weights = new int[parts];
        int sumWeights = 0;
        for (int i = 0; i < parts; i++) {
            // ensure at least 1 weight
            weights[i] = 1 + random.nextInt(100);
            sumWeights += weights[i];
        }

        int allocated = 0;
        for (int i = 0; i < parts; i++) {
            // allocate proportionally; final part gets the remainder
            if (i == parts - 1) {
                result[i] = totalCents - allocated;
            } else {
                int partCents = (int) ((long) totalCents * weights[i] / sumWeights);
                // ensure at least 1 cent
                if (partCents < 1) partCents = 1;
                result[i] = partCents;
                allocated += partCents;
            }
        }

        // Fix rounding edge-cases: adjust if sum != total
        int sum = 0;
        for (int v : result) sum += v;
        int diff = totalCents - sum;
        int idx = 0;
        while (diff != 0) {
            result[idx % parts] += (diff > 0) ? 1 : -1;
            diff += (diff > 0) ? -1 : 1;
            idx++;
        }

        return result;
    }

}

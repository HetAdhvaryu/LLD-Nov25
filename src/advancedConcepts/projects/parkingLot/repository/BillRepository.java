package advancedConcepts.projects.parkingLot.repository;

import advancedConcepts.projects.parkingLot.model.Bill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BillRepository {
    // IN-MEMORY DATABASE
    private HashMap<Integer, Bill> bills;
    private static int idCounter = 1;

    public BillRepository() {
        this.bills = new HashMap<>();
    }

    public Bill getBillById(int id) {
        if (bills.containsKey(id)) {
            return bills.get(id);
        } else {
            throw new RuntimeException("Bill with id " + id + " not found");
        }
    }

    public Bill save(Bill bill) { // upsert
        if (!bills.containsKey(bill.getId())) {
            bill.setId(idCounter++);
        }
        bills.put(bill.getId(), bill);
        return bill;
    }

    public void delete(int billId) {
        bills.remove(billId);
    }

    public List<Bill> getAllBills() {
        return new ArrayList<>(bills.values());
    }
}

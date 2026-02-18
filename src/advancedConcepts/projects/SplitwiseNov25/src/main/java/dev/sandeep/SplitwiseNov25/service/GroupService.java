package dev.sandeep.SplitwiseNov25.service;

import dev.sandeep.SplitwiseNov25.entity.Group;
import dev.sandeep.SplitwiseNov25.entity.Transaction;
import dev.sandeep.SplitwiseNov25.repository.GroupRepository;
import dev.sandeep.SplitwiseNov25.service.settleUpStrategy.SettleUpStrategy;
import dev.sandeep.SplitwiseNov25.service.settleUpStrategy.SettlupStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public Group createGroup(Group group) {
        return null;
    }

    public List<Group> getAllGroups() {
        return null;
    }

    public Group getAllExpensesByGroupId(int groupId) {
        return null;
    }

    public List<Transaction> settleUpByGroupId(int groupId) {
        Group group = groupRepository.findById(groupId).get();
        SettleUpStrategy settleUpStrategy = SettlupStrategyFactory.getSettlupStrategy();
        List<Transaction> settleUpTransaction = settleUpStrategy.settleUp(group.getExpenses());
        return settleUpTransaction;
    }
}

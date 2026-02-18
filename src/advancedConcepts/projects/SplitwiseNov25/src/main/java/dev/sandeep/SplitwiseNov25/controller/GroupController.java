package dev.sandeep.SplitwiseNov25.controller;

import dev.sandeep.SplitwiseNov25.entity.Transaction;
import dev.sandeep.SplitwiseNov25.service.GroupService;
import dev.sandeep.SplitwiseNov25.service.InitializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GroupController {

    @Autowired
    private InitializationService initializationService;
    @Autowired
    private GroupService groupService;

    @GetMapping("/initialize")
    public String initialize() {
        initializationService.initialize();
        return "Data initialized successfully!";
    }

    @GetMapping("/settle/{groupId}")
    public List<Transaction> settleGroup(@PathVariable int groupId) {
        return groupService.settleUpByGroupId(groupId);
    }
}

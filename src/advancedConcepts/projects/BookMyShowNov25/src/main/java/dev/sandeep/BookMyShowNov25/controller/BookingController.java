package dev.sandeep.BookMyShowNov25.controller;

import dev.sandeep.BookMyShowNov25.service.BookingService;
import dev.sandeep.BookMyShowNov25.service.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    @Autowired // this will handle the dependency injection automatically
    private BookingService bookingService;

    @GetMapping("/hello/{username}")
    public String hello(@PathVariable("username") String username) {
        return bookingService.hello(username);
    }

}

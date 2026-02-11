package dev.sandeep.BookMyShowNov25.service;

import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService{

    //private ShowSeatRepo showSeatRepo;


    @Override
    public String hello(String username) {
        System.out.println("Hello " + username);
        return "Hello! How are you doing?  " + username;
    }
}

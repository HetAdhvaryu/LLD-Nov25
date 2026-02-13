package dev.sandeep.BookMyShowNov25.controller;

import dev.sandeep.BookMyShowNov25.dto.CreateUserReqDTO;
import dev.sandeep.BookMyShowNov25.dto.CreateUserRespDTO;
import dev.sandeep.BookMyShowNov25.entity.User;
import dev.sandeep.BookMyShowNov25.mapper.UserDTOMapper;
import dev.sandeep.BookMyShowNov25.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/user")
    public ResponseEntity<CreateUserRespDTO> addUser(@RequestBody CreateUserReqDTO createUserReqDTO) {
        //generic validations -> user is null or not
        // user email and password is null or not
        // password has 8 characters, capital and small letters etc.
        // email is properly defined or not
        //TODO: add the above mentioned validation methods in the controller layer, and validate
        User reqUser = UserDTOMapper.getUser(createUserReqDTO);
        User savedUser = userService.createUser(reqUser);
        CreateUserRespDTO respUser = UserDTOMapper.getCreateUserRespDTO(savedUser);
        return ResponseEntity.ok(respUser);
    }
}

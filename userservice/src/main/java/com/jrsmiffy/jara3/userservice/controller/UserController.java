package com.jrsmiffy.jara3.userservice.controller;

import com.jrsmiffy.jara3.userservice.model.User;
import com.jrsmiffy.jara3.userservice.model.UserResponse;
import com.jrsmiffy.jara3.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /** Register User */
    @PostMapping(path = "/register")
    public ResponseEntity<UserResponse> register(@RequestBody final User potentialUser) {

        // Check that this potential user is valid - if so, register them; else, return err
        ResponseEntity<UserResponse> response;
        final UserResponse userResponse = userService.register(potentialUser);

        if(userResponse.getUser().isPresent())
            response = ResponseEntity.status(HttpStatus.OK).body(userResponse);
        else
            response = ResponseEntity.status(HttpStatus.CONFLICT).body(userResponse);

        log.info(response.toString());
        return response;
    }


    /** Authenticate User */
    @GetMapping(path = "/authenticate/{username}/{password}")
    public ResponseEntity<UserResponse> authenticate(@PathVariable("username") final String username, @PathVariable("password") final String password) {

        // Check that these user credentials match with a valid user - if so, return success; else, return err
        ResponseEntity<UserResponse> response;

        final UserResponse userResponse = userService.authenticate(username, password);

        if(userResponse.getUser().isPresent())
            response = ResponseEntity.status(HttpStatus.OK).body(userResponse);
        else
            response = ResponseEntity.status(HttpStatus.CONFLICT).body(userResponse);

        log.info(response.toString());
        return response;
    }

    /** Get All Users - Dev Use Only */
    @RequestMapping(path = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
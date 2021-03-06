package com.jrsmiffy.jara3.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;

@Data
@AllArgsConstructor
public class UserResponse {

    private Optional<AppUser> user;
    private String response;
}
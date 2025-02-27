package com.test.domain.user.controller;

import com.test.domain.user.dto.UserRequest;
import com.test.domain.user.dto.UserResponse;
import com.test.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/user")
    public UserResponse addUser(@RequestBody UserRequest request) {
        UserResponse response = userService.addUser(request);
        return response;
    }
}

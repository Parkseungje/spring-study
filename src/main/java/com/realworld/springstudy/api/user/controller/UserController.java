package com.realworld.springstudy.api.user.controller;

import com.realworld.springstudy.api.user.dto.UserRequest;
import com.realworld.springstudy.api.user.dto.UserUpdateRequest;
import com.realworld.springstudy.api.user.entity.User;
import com.realworld.springstudy.api.user.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public void addUsers(@RequestBody UserRequest userRequest){
        userService.addUsers(userRequest);
    }

    @PutMapping("/user/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest userUpdateRequest){
        userService.updateUserById(id, userUpdateRequest);
    }

    @GetMapping("/user")
    public User getCurrentUser() {
        return userService.getCurrentUser();
    }

}



package com.example.ibmvacationrentalapi.controller;

import com.example.ibmvacationrentalapi.domain.User;
import com.example.ibmvacationrentalapi.dto.UserDto;
import com.example.ibmvacationrentalapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@Validated @RequestBody UserDto userDto){
        User user = userService.fromDto(userDto);
        userService.insert(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@Validated @RequestBody UserDto userDto){
        User user = userService.fromDto(userDto);
        User foundUser = userService.find(user.getEmail());
        if (foundUser != null) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }



}

package com.example.ibmvacationrentalapi.controller;

import com.example.ibmvacationrentalapi.domain.UserProfile;
import com.example.ibmvacationrentalapi.dto.UserDto;
import com.example.ibmvacationrentalapi.service.UserService;
import com.example.ibmvacationrentalapi.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@Validated @RequestBody UserDto userDto){
        UserProfile userProfile = userService.fromDto(userDto);
        userService.insert(userProfile);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



}

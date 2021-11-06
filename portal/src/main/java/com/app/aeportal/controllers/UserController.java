package com.app.aeportal.controllers;

import com.app.aeportal.services.UserService;
import com.app.aeportal.dto.request.UserRequestDto;
import com.app.aeportal.dto.response.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserResponseDto[]> getAllUsers() {
        return ResponseEntity.ok().body(this.userService.getUsers());
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> addNewUser(@Valid @RequestBody UserRequestDto request) {
        return new ResponseEntity<>(this.userService.addNewUser(request), HttpStatus.CREATED);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable("id") Long id,
            @RequestBody UserRequestDto request
    ) {
        return ResponseEntity.ok().body(this.userService.updateUserInfo(id, request));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(this.userService.getUserById(id));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<UserResponseDto> deleteUserById(@PathVariable("id") Long id) {
        this.userService.deleteUserInfo(id);
        return ResponseEntity.noContent().build();
    }
}

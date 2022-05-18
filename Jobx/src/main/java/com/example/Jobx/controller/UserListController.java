package com.example.Jobx.controller;

import com.example.Jobx.security.entity.User;
import com.example.Jobx.service.UserListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserListController {

    @Autowired
    UserListService service;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user-list")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> userList = service.getUsersList();
            return new ResponseEntity<>(userList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

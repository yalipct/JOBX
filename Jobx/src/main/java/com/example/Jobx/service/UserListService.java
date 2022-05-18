package com.example.Jobx.service;

import com.example.Jobx.security.entity.Role;
import com.example.Jobx.security.entity.User;
import com.example.Jobx.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserListService {

    @Autowired
    UserRepository repository;


    public List<User> getUsersList() {

        List<User> usersList = repository.findAll();

        for (int i = 0; i < usersList.size(); i++) {

            for(Role r: usersList.get(i).getRoles()){
                if (r.getRolename().name().equalsIgnoreCase("admin")){
                    usersList.remove(i);
                }
            }
        }

        return usersList;
    }
}
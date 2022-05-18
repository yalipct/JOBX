package com.example.Jobx.util;

import com.example.Jobx.security.entity.Role;
import com.example.Jobx.security.enums.RoleName;
import com.example.Jobx.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class CreateRoles implements CommandLineRunner {

    @Autowired
    RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        Role roleAdmin = new Role(RoleName.ROLE_USER);
        Role roleUser = new Role(RoleName.ROLE_ADMIN);
        roleService.save(roleAdmin);
        roleService.save(roleUser);
        
    }
}

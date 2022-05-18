package com.example.Jobx.security.service;

import com.example.Jobx.security.entity.Role;
import com.example.Jobx.security.enums.RoleName;
import com.example.Jobx.security.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public Optional<Role> getByRoleName(RoleName rolename){
        return roleRepository.findByRolename(rolename);
    }

    public void save(Role role){
        roleRepository.save(role);
    }
}

package com.example.Jobx.security.repository;

import com.example.Jobx.security.entity.Role;
import com.example.Jobx.security.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRolename(RoleName rolename);
}

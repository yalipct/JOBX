package com.example.Jobx.security.entity;

import javax.persistence.*;

import com.example.Jobx.security.enums.RoleName;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Enumerated(EnumType.STRING)

    private RoleName rolename;

    public Role() {
    }

    public Role(RoleName rolname) {
        this.rolename = rolname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoleName getRolename() {
        return rolename;
    }

    public void setRolename(RoleName rolename) {
        this.rolename = rolename;
    }
}


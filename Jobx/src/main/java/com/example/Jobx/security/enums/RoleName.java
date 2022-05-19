package com.example.Jobx.security.enums;

import java.util.stream.Stream;

public enum RoleName {
    ROLE_ADMIN("admin"), ROLE_USER("user");

    private String name;

    private RoleName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static RoleName from(String value) {
        return Stream.of(RoleName.values()).filter(targetEnum -> targetEnum.getName().equalsIgnoreCase(value))
                .findFirst().orElse(null);

    }
}

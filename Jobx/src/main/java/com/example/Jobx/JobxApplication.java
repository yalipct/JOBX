package com.example.Jobx;

import com.example.Jobx.security.entity.Role;
import com.example.Jobx.security.entity.User;
import com.example.Jobx.security.enums.RoleName;
import com.example.Jobx.security.service.RoleService;
import com.example.Jobx.security.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;


@SpringBootApplication
public class JobxApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobxApplication.class, args);
	}


	//Bean to read json file and add it to de database

	@Bean
	CommandLineRunner runner(UserService userService, RoleService roleService) {
		return args -> {
			// read json and write to db
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<User>> typeReference = new TypeReference<List<User>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/jobx-users.json");
			try {
				List<User> users = mapper.readValue(inputStream,typeReference);

				Set<Role> newRole = new HashSet<>();
				newRole.add(roleService.getByRoleName(RoleName.ROLE_USER).get());

				for (User u: users) {
					u.setRoles(newRole);
					try {
						userService.save(u);
					}catch (Exception e){
						e.getMessage();
					}
				}
				System.out.println("Users Saved!");
			} catch (IOException e){
				System.out.println("Unable to save users: " + e.getMessage());
			}
		};
	}
}

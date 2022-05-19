package com.example.Jobx.security.controller;

import java.util.*;

import com.example.Jobx.security.dto.JwtDto;
import com.example.Jobx.security.dto.LoginUser;
import com.example.Jobx.security.dto.NewUser;
import com.example.Jobx.security.entity.Role;
import com.example.Jobx.security.entity.User;
import com.example.Jobx.security.enums.RoleName;
import com.example.Jobx.security.jwt.JwtProvider;
import com.example.Jobx.security.service.RoleService;
import com.example.Jobx.security.service.UserService;
import com.example.Jobx.util.Mensaje;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    JwtProvider jwtProvider;

    @ApiOperation(value = "This method is used to register users.")
    @PostMapping("/new")
    public ResponseEntity<?> signup(@Valid @RequestBody NewUser newUser, BindingResult bindingResult){

        if(bindingResult.hasFieldErrors("username"))
            return new ResponseEntity(new Mensaje("invalid username"), HttpStatus.BAD_REQUEST);
        if(bindingResult.hasFieldErrors("email"))
            return new ResponseEntity(new Mensaje("invalid email"), HttpStatus.BAD_REQUEST);
        if (bindingResult.hasFieldErrors("password")) return new ResponseEntity(new Mensaje("invalid password"), HttpStatus.BAD_REQUEST);
        if(userService.existsByUserName(newUser.getUsername()))
            return new ResponseEntity(new Mensaje("that username already exists"), HttpStatus.BAD_REQUEST);
        if(userService.existsByEmail(newUser.getEmail()))
            return new ResponseEntity(new Mensaje("that email already exists"), HttpStatus.BAD_REQUEST);

        User user =
                new User(newUser.getUsername(), newUser.getEmail(),
                        passwordEncoder.encode(newUser.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getByRoleName(RoleName.ROLE_USER).get());
        if(newUser.getRoles().contains("admin"))
            roles.add(roleService.getByRoleName(RoleName.ROLE_ADMIN).get());
        user.setRoles(roles);
        userService.save(user);

        return new ResponseEntity(new Mensaje("saved user"), HttpStatus.CREATED);
    }

    @ApiOperation(value = "This method is used for user login.")
    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@RequestBody LoginUser loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("wrong fields"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getUsername(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }

}

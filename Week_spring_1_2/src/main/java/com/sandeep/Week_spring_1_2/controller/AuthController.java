package com.sandeep.Week_spring_1_2.controller;

import com.sandeep.Week_spring_1_2.entity.UserEntity;
import com.sandeep.Week_spring_1_2.repository.UserRepository;
import com.sandeep.Week_spring_1_2.security.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Authentication APIs")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository repo,
                          PasswordEncoder encoder,
                          JwtUtil jwtUtil) {
        this.userRepository = repo;
        this.passwordEncoder = encoder;
        this.jwtUtil = jwtUtil;
    }

    @Operation(summary = "Register new user")
    @PostMapping("/register")
    public String register(@RequestBody UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered";
    }

    @Operation(summary = "Login and generate JWT")
    @PostMapping("/login")
    public String login(@RequestBody UserEntity user) {
        UserEntity dbUser = userRepository
                .findByUsername(user.getUsername())
                .orElseThrow();

        if (passwordEncoder.matches(
                user.getPassword(),
                dbUser.getPassword())) {

            return jwtUtil.generateToken(user.getUsername());
        }
        throw new RuntimeException("Invalid credentials");
    }
}


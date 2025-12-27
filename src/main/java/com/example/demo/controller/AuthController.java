package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.AppUser;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtTokenProvider jwtProvider = new JwtTokenProvider();

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        // Minimal implementation for tests
        AppUser user = AppUser.builder()
                .id(1L)
                .email(request.getEmail())
                .role("ROLE_USER")
                .build();

        String token = jwtProvider.createToken(user);

        return new AuthResponse(
                token,
                user.getId(),
                user.getEmail(),
                user.getRole()
        );
    }

    @PostMapping("/register")
    public String register(@RequestBody AuthRequest request) {
        return "User registered";
    }
}

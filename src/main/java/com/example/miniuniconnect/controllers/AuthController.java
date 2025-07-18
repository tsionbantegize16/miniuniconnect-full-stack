package com.example.miniuniconnect.controllers;

import com.example.miniuniconnect.dto.LoginRequest;
import com.example.miniuniconnect.dto.UserDTO;
import com.example.miniuniconnect.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO, @RequestParam String password) {
        UserDTO created = authService.register(userDTO, password);
        return ResponseEntity.ok(created);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String jwt = authService.login(loginRequest);
        return ResponseEntity.ok(jwt);
    }
} 
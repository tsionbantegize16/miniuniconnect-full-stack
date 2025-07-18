package com.example.miniuniconnect.services;

import com.example.miniuniconnect.config.JwtUtil;
import com.example.miniuniconnect.dto.LoginRequest;
import com.example.miniuniconnect.dto.UserDTO;
import com.example.miniuniconnect.exceptions.ResourceNotFoundException;
import com.example.miniuniconnect.models.User;
import com.example.miniuniconnect.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    public UserDTO register(UserDTO userDTO, String rawPassword) {
        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            throw new IllegalArgumentException("Email already registered");
        }
        User user = User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .passwordHash(passwordEncoder.encode(rawPassword))
                .role(User.Role.valueOf(userDTO.getRole()))
                .bio(userDTO.getBio())
                .skills(userDTO.getSkills())
                .build();
        user = userRepository.save(user);
        return toDTO(user);
    }

    public String login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email or password");
        }
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if (user == null) throw new ResourceNotFoundException("User not found");
        return jwtUtil.generateToken(user.getEmail());
    }

    public UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .bio(user.getBio())
                .skills(user.getSkills())
                .build();
    }
} 
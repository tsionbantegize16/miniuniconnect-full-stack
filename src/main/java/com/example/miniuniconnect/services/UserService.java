package com.example.miniuniconnect.services;

import com.example.miniuniconnect.dto.UserDTO;
import com.example.miniuniconnect.exceptions.ResourceNotFoundException;
import com.example.miniuniconnect.models.User;
import com.example.miniuniconnect.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return toDTO(user);
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setName(userDTO.getName());
        user.setBio(userDTO.getBio());
        user.setSkills(userDTO.getSkills());
        user = userRepository.save(user);
        return toDTO(user);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) throw new ResourceNotFoundException("User not found");
        userRepository.deleteById(id);
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
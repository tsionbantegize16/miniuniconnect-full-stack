package com.example.miniuniconnect.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String passwordHash;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String bio;
    private String skills;

    public enum Role {
        STUDENT, INSTRUCTOR, ADMIN
    }
    // TODO: Add relationships to projects, applications, resources, discussions, comments
} 
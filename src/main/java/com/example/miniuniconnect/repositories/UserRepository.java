package com.example.miniuniconnect.repositories;

import com.example.miniuniconnect.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query methods if needed
    User findByEmail(String email);
} 
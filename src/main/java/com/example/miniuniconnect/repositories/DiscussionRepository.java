package com.example.miniuniconnect.repositories;

import com.example.miniuniconnect.models.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscussionRepository extends JpaRepository<Discussion, Long> {
    // Custom query methods if needed
} 
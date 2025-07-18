package com.example.miniuniconnect.repositories;

import com.example.miniuniconnect.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    // Custom query methods if needed
} 
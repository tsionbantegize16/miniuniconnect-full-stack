package com.example.miniuniconnect.repositories;

import com.example.miniuniconnect.models.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    // Custom query methods if needed
} 
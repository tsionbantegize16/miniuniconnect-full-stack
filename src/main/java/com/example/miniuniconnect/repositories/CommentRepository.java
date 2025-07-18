package com.example.miniuniconnect.repositories;

import com.example.miniuniconnect.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Custom query methods if needed
} 
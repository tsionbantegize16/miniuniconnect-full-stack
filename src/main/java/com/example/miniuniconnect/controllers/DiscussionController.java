package com.example.miniuniconnect.controllers;

import com.example.miniuniconnect.models.Comment;
import com.example.miniuniconnect.models.Discussion;
import com.example.miniuniconnect.services.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discussions")
public class DiscussionController {
    @Autowired
    private DiscussionService discussionService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Discussion>> getAllDiscussions() {
        return ResponseEntity.ok(discussionService.getAllDiscussions());
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Discussion> getDiscussionById(@PathVariable Long id) {
        return ResponseEntity.ok(discussionService.getDiscussionById(id));
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Discussion> createDiscussion(@RequestBody Discussion discussion, @RequestParam Long userId) {
        return ResponseEntity.ok(discussionService.createDiscussion(discussion, userId));
    }

    @PostMapping("/{id}/comments")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Comment> addComment(@PathVariable Long id, @RequestParam String comment, @RequestParam Long userId) {
        return ResponseEntity.ok(discussionService.addComment(id, comment, userId));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteDiscussion(@PathVariable Long id) {
        discussionService.deleteDiscussion(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/comments/{commentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        discussionService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
} 
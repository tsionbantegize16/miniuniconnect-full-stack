package com.example.miniuniconnect.services;

import com.example.miniuniconnect.exceptions.ResourceNotFoundException;
import com.example.miniuniconnect.models.Comment;
import com.example.miniuniconnect.models.Discussion;
import com.example.miniuniconnect.models.User;
import com.example.miniuniconnect.repositories.CommentRepository;
import com.example.miniuniconnect.repositories.DiscussionRepository;
import com.example.miniuniconnect.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DiscussionService {
    @Autowired
    private DiscussionRepository discussionRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Discussion> getAllDiscussions() {
        return discussionRepository.findAll();
    }

    public Discussion getDiscussionById(Long id) {
        return discussionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Discussion not found"));
    }

    public Discussion createDiscussion(Discussion discussion, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        discussion.setUser(user);
        discussion.setTimestamp(LocalDateTime.now());
        return discussionRepository.save(discussion);
    }

    public Comment addComment(Long discussionId, String commentText, Long userId) {
        Discussion discussion = discussionRepository.findById(discussionId).orElseThrow(() -> new ResourceNotFoundException("Discussion not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Comment comment = Comment.builder()
                .discussion(discussion)
                .user(user)
                .comment(commentText)
                .timestamp(LocalDateTime.now())
                .build();
        return commentRepository.save(comment);
    }

    public void deleteDiscussion(Long id) {
        if (!discussionRepository.existsById(id)) throw new ResourceNotFoundException("Discussion not found");
        discussionRepository.deleteById(id);
    }

    public void deleteComment(Long commentId) {
        if (!commentRepository.existsById(commentId)) throw new ResourceNotFoundException("Comment not found");
        commentRepository.deleteById(commentId);
    }
} 
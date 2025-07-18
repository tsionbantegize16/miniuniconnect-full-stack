package com.example.miniuniconnect.services;

import com.example.miniuniconnect.exceptions.ResourceNotFoundException;
import com.example.miniuniconnect.models.Resource;
import com.example.miniuniconnect.models.User;
import com.example.miniuniconnect.repositories.ResourceRepository;
import com.example.miniuniconnect.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }

    public Resource getResourceById(Long id) {
        return resourceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
    }

    public Resource createResource(Resource resource, Long uploaderId) {
        User uploader = userRepository.findById(uploaderId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        resource.setUploadedBy(uploader);
        return resourceRepository.save(resource);
    }

    public Resource updateResource(Long id, Resource resourceDetails) {
        Resource resource = resourceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        resource.setTitle(resourceDetails.getTitle());
        resource.setDescription(resourceDetails.getDescription());
        resource.setFileUrl(resourceDetails.getFileUrl());
        resource.setCategory(resourceDetails.getCategory());
        return resourceRepository.save(resource);
    }

    public void deleteResource(Long id) {
        if (!resourceRepository.existsById(id)) throw new ResourceNotFoundException("Resource not found");
        resourceRepository.deleteById(id);
    }
} 
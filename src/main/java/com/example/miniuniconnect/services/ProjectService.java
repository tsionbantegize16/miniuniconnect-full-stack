package com.example.miniuniconnect.services;

import com.example.miniuniconnect.dto.ProjectDTO;
import com.example.miniuniconnect.dto.UserDTO;
import com.example.miniuniconnect.exceptions.ResourceNotFoundException;
import com.example.miniuniconnect.models.Project;
import com.example.miniuniconnect.models.User;
import com.example.miniuniconnect.repositories.ProjectRepository;
import com.example.miniuniconnect.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;

    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ProjectDTO getProjectById(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        return toDTO(project);
    }

    public ProjectDTO createProject(ProjectDTO projectDTO, Long creatorId) {
        User creator = userRepository.findById(creatorId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Project project = Project.builder()
                .title(projectDTO.getTitle())
                .description(projectDTO.getDescription())
                .status(projectDTO.getStatus())
                .tags(projectDTO.getTags())
                .fileUrl(projectDTO.getFileUrl())
                .createdBy(creator)
                .build();
        project = projectRepository.save(project);
        return toDTO(project);
    }

    public ProjectDTO updateProject(Long id, ProjectDTO projectDTO) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        project.setTitle(projectDTO.getTitle());
        project.setDescription(projectDTO.getDescription());
        project.setStatus(projectDTO.getStatus());
        project.setTags(projectDTO.getTags());
        project.setFileUrl(projectDTO.getFileUrl());
        project = projectRepository.save(project);
        return toDTO(project);
    }

    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)) throw new ResourceNotFoundException("Project not found");
        projectRepository.deleteById(id);
    }

    public ProjectDTO toDTO(Project project) {
        UserDTO creatorDTO = UserDTO.builder()
                .id(project.getCreatedBy().getId())
                .name(project.getCreatedBy().getName())
                .email(project.getCreatedBy().getEmail())
                .role(project.getCreatedBy().getRole().name())
                .bio(project.getCreatedBy().getBio())
                .skills(project.getCreatedBy().getSkills())
                .build();
        return ProjectDTO.builder()
                .id(project.getId())
                .title(project.getTitle())
                .description(project.getDescription())
                .status(project.getStatus())
                .tags(project.getTags())
                .fileUrl(project.getFileUrl())
                .createdBy(creatorDTO)
                .build();
    }
} 
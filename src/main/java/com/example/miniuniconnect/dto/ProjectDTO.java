package com.example.miniuniconnect.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDTO {
    private Long id;
    private String title;
    private String description;
    private String status;
    private String tags;
    private String fileUrl;
    private UserDTO createdBy;
} 
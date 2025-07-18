package com.example.miniuniconnect.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationDTO {
    private Long id;
    private UserDTO user;
    private ProjectDTO project;
    private String message;
    private String status;
} 
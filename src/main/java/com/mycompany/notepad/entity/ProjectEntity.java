package com.mycompany.notepad.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Data
@Document(collection = "projects")
public class ProjectEntity {

    @Id
    private String id;
    private String projectName;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private String author;
    private List<CanvasObjectEntity> canvasObj = new ArrayList<>();

    // Getters and Setters
}
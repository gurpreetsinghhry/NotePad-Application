package com.mycompany.notepad.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.mycompany.notepad.entity.CanvasObjectEntity;

import lombok.Data;

@Data
public class ProjectModel {
        @Id
    private String id;
    private String projectName;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private String author;
    private List<CanvasObjectEntity> canvasObj = new ArrayList<>();
}
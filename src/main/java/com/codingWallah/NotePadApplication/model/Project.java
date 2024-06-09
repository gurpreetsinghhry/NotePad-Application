package com.codingWallah.NotePadApplication.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "projects")
public class Project {

    @Id
    private String id;
    private String projectName;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private String author = "for_now_guest_user";
    private List<CanvasObject> canvasObj;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<CanvasObject> getCanvasObj() {
        return canvasObj;
    }

    public void setCanvasObj(List<CanvasObject> canvasObj) {
        this.canvasObj = canvasObj;
    }
}

package com.mycompany.notepad.service;

import java.util.List;

import com.mycompany.notepad.entity.ProjectEntity;
import com.mycompany.notepad.model.CanvasObjectModel;
import com.mycompany.notepad.model.ProjectModel;
import org.springframework.http.ResponseEntity;

public interface ProjectService {
    public ProjectEntity createProject(ProjectModel projectModel);

    public ResponseEntity<?> addCanvasObject(String projectId, CanvasObjectModel canvasObjectModel);

    public ResponseEntity<?> getCanvasObjectById(String projectId, String canvasObjectId);

    public ResponseEntity<?> getCanvasObjectsByProjectId(String projectId);

    public ResponseEntity<?> getProjectById(String projectId);

    public List<ProjectEntity> getAllProjects();

    public ResponseEntity<?> updateProject(String projectId, ProjectModel projectModel);

    public ResponseEntity<?> deleteProject(String projectId);

    public ResponseEntity<?> updateCanvasObject(String projectId, String canvasObjectId,
            CanvasObjectModel canvasObjectModel);

    public ResponseEntity<?> deleteCanvasObject(String projectId, String canvasObjectId);
}
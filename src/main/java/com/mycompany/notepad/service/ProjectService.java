package com.mycompany.notepad.service;

import java.util.List;

import com.mycompany.notepad.entity.ProjectEntity;
import com.mycompany.notepad.model.CanvasObjectModel;
import com.mycompany.notepad.model.ProjectModel;
import org.springframework.http.ResponseEntity;

public interface ProjectService {
     ProjectEntity createProject(ProjectModel projectModel);

     ResponseEntity<?> addCanvasObject(String projectId, CanvasObjectModel canvasObjectModel);

     ResponseEntity<?> getCanvasObjectById(String projectId, String canvasObjectId);

     ResponseEntity<?> getCanvasObjectsByProjectId(String projectId);

     ResponseEntity<?> getProjectById(String projectId);

     List<ProjectEntity> getAllProjects();

     ResponseEntity<?> updateProject(String projectId, ProjectModel projectModel);

     ResponseEntity<?> deleteProject(String projectId);

     ResponseEntity<?> updateCanvasObject(String projectId, String canvasObjectId,
            CanvasObjectModel canvasObjectModel);

     ResponseEntity<?> deleteCanvasObject(String projectId, String canvasObjectId);

     ResponseEntity<?> addMultiObjects(String projectId, List<CanvasObjectModel> listOfCanvas);

}
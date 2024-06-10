package com.mycompany.notepad.service;

import java.util.List;

import com.mycompany.notepad.entity.CanvasObjectEntity;
import com.mycompany.notepad.entity.ProjectEntity;
import com.mycompany.notepad.model.CanvasObjectModel;
import com.mycompany.notepad.model.ProjectModel;

public interface ProjectService {
    public ProjectEntity createProject(ProjectModel projectModel);

    public ProjectEntity addCanvasObject(String projectId, CanvasObjectModel canvasObjectModel);

    public CanvasObjectEntity getCanvasObjectById(String projectId, String canvasObjectId);

    public List<CanvasObjectEntity> getCanvasObjectsByProjectId(String projectId);

    public ProjectEntity getProjectById(String projectId);

    public List<ProjectEntity> getAllProjects();

    public ProjectEntity updateProject(String projectId, ProjectModel projectModel);

    public void deleteProject(String projectId);

    public ProjectEntity updateCanvasObject(String projectId, String canvasObjectId,
            CanvasObjectModel canvasObjectModel);

    public void deleteCanvasObject(String projectId, String canvasObjectId);
}
package com.mycompany.notepad.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.notepad.entity.CanvasObjectEntity;
import com.mycompany.notepad.entity.ProjectEntity;
import com.mycompany.notepad.model.CanvasObjectModel;
import com.mycompany.notepad.model.ProjectModel;
import com.mycompany.notepad.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectEntity createProject(ProjectModel projectModel) {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setProjectName(projectModel.getProjectName());
        projectEntity.setCreatedTime(LocalDateTime.now());
        projectEntity.setUpdatedTime(LocalDateTime.now());
        projectEntity.setAuthor(projectModel.getAuthor());
        return projectRepository.save(projectEntity);
    }

    public ProjectEntity addCanvasObject(String projectId, CanvasObjectModel canvasObjectModel) {
        Optional<ProjectEntity> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isPresent()) {
            ProjectEntity projectEntity = optionalProject.get();
            CanvasObjectEntity canvasObjectEntity = new CanvasObjectEntity();
            canvasObjectEntity.setValue(canvasObjectModel.getValue());
            canvasObjectEntity.setId(projectId + System.currentTimeMillis());

            projectEntity.getCanvasObj().add(canvasObjectEntity);
            projectEntity.setUpdatedTime(LocalDateTime.now());

            return projectRepository.save(projectEntity);
        } else {
            throw new RuntimeException("Project not found");
        }
    }

    public List<ProjectEntity> getAllProjects() {
        return projectRepository.findAll();
    }

    public ProjectEntity getProjectById(String projectId) {
        return projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project not found"));
    }

    public List<CanvasObjectEntity> getCanvasObjectsByProjectId(String projectId) {
        ProjectEntity projectEntity = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        return projectEntity.getCanvasObj();
    }

    public CanvasObjectEntity getCanvasObjectById(String projectId, String canvasObjectId) {
        ProjectEntity projectEntity = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        return projectEntity.getCanvasObj().stream()
                .filter(canvasObject -> canvasObjectId.equals(canvasObject.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Canvas object not found"));
    }

    public ProjectEntity updateProject(String projectId, ProjectModel projectModel) {
        ProjectEntity projectEntity = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        projectEntity.setProjectName(projectModel.getProjectName());
        projectEntity.setUpdatedTime(LocalDateTime.now());
        return projectRepository.save(projectEntity);
    }

    public void deleteProject(String projectId) {
        ProjectEntity projectEntity = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        projectRepository.delete(projectEntity);
    }

    public ProjectEntity updateCanvasObject(String projectId, String canvasObjectId,
            CanvasObjectModel canvasObjectModel) {
        ProjectEntity projectEntity = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        for (CanvasObjectEntity canvasObject : projectEntity.getCanvasObj()) {
            if (canvasObjectId.equals(canvasObject.getId())) {
                canvasObject.setId(projectId + System.currentTimeMillis());
                canvasObject.setValue(canvasObjectModel.getValue());
                projectEntity.setUpdatedTime(LocalDateTime.now());
                return projectRepository.save(projectEntity);
            }
        }
        throw new RuntimeException("Canvas object not found");
    }

    public void deleteCanvasObject(String projectId, String canvasObjectId) {
        ProjectEntity projectEntity = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        CanvasObjectEntity canvasObjectToDelete = projectEntity.getCanvasObj().stream()
                .filter(canvasObject -> canvasObjectId.equals(canvasObject.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Canvas object not found"));
        projectEntity.getCanvasObj().remove(canvasObjectToDelete);
        projectEntity.setUpdatedTime(LocalDateTime.now());
        projectRepository.save(projectEntity);
    }

}
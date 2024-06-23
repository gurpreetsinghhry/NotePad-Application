package com.mycompany.notepad.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.mycompany.notepad.exception.CanvasObjectNotFoundException;
import com.mycompany.notepad.exception.ProjectNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<?> addCanvasObject(String projectId, CanvasObjectModel canvasObjectModel) {
        Optional<ProjectEntity> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isPresent()) {
            ProjectEntity projectEntity = optionalProject.get();
            CanvasObjectEntity canvasObjectEntity = new CanvasObjectEntity();
            canvasObjectEntity.setValue(canvasObjectModel.getValue());
            canvasObjectEntity.setId(projectId + System.currentTimeMillis());
            projectEntity.getCanvasObj().add(canvasObjectEntity);
            projectEntity.setUpdatedTime(LocalDateTime.now());
            projectRepository.save(projectEntity);
            CanvasObjectModel localCanvasObjectModel = new CanvasObjectModel();
            BeanUtils.copyProperties(canvasObjectEntity,localCanvasObjectModel);
            return ResponseEntity.ok().body(localCanvasObjectModel);
        } else {
            throw new ProjectNotFoundException("No Project present with this project id.");
        }
    }

    public List<ProjectEntity> getAllProjects() {
        return projectRepository.findAll();
    }

    public ResponseEntity<?> getProjectById(String projectId) {
        Optional<ProjectEntity> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isEmpty()) {
            throw new ProjectNotFoundException("No Project present with this project id.");
        }
        ProjectModel projectModel = new ProjectModel();
        ProjectEntity projectEntity = projectRepository.findById(projectId).get();
        BeanUtils.copyProperties(projectEntity, projectModel);
        return ResponseEntity.ok().body(projectModel);
    }

    public ResponseEntity<?> getCanvasObjectsByProjectId(String projectId) {
        Optional<ProjectEntity> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isEmpty()) {
            throw new ProjectNotFoundException("No Project present with this project id.");
        }
        List<CanvasObjectEntity> canvasObjectEntities =  optionalProject.get().getCanvasObj();
        // Convert each CanvasObjectEntity to CanvasObjectModel
        List<CanvasObjectModel> canvasObjectModelList = canvasObjectEntities.stream()
                .map(this::convertToModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(canvasObjectModelList);
    }

    private CanvasObjectModel convertToModel(CanvasObjectEntity entity) {
        CanvasObjectModel model = new CanvasObjectModel();
        BeanUtils.copyProperties(entity,model);
        return model;
    }

    public ResponseEntity<?> getCanvasObjectById(String projectId, String canvasObjectId) {
        Optional<ProjectEntity> projectOptional = projectRepository.findById(projectId);
        if (projectOptional.isEmpty()) {
            throw new ProjectNotFoundException("No Project present with this project id");
        } else {
            Optional<CanvasObjectEntity> canvasObjectOptional = projectOptional.get().getCanvasObj().stream()
                    .filter(canvasObject -> canvasObjectId.equals(canvasObject.getId()))
                    .findFirst();

            if (canvasObjectOptional.isEmpty()) {
                throw new CanvasObjectNotFoundException("No Canvas with this canvas id present in this project");
            } else {
                CanvasObjectModel canvasObjectModel = new CanvasObjectModel();
                BeanUtils.copyProperties(canvasObjectOptional.get(), canvasObjectModel);
                return ResponseEntity.ok().body(canvasObjectModel);
            }
        }

    }

    public ResponseEntity<?> updateProject(String projectId, ProjectModel projectModel) {
        Optional<ProjectEntity> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isEmpty()) {
            throw new ProjectNotFoundException("No Project present with this project id");
        }
        ProjectEntity projectEntity = optionalProject.get();
        projectEntity.setProjectName(projectModel.getProjectName());
        projectEntity.setUpdatedTime(LocalDateTime.now());
        return ResponseEntity.ok().body(projectEntity);
    }

    public ResponseEntity<?> deleteProject(String projectId) {
        Optional<ProjectEntity> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isEmpty()) {
            throw new ProjectNotFoundException("No Project present with this project id");
        } else {
            projectRepository.deleteById(projectId);
            return ResponseEntity.ok().body("Project deleted successfully");

        }
    }

    public ResponseEntity<?> updateCanvasObject(String projectId, String canvasObjectId,
            CanvasObjectModel canvasObjectModel) {
        Optional<ProjectEntity> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isEmpty()) {
            throw new ProjectNotFoundException("No Project present with this project id");
        } else {
            Optional<CanvasObjectEntity> canvasObjectOptional = projectRepository.findById(projectId).get()
                    .getCanvasObj().stream()
                    .filter(canvasObject -> canvasObjectId.equals(canvasObject.getId()))
                    .findFirst();

            if (canvasObjectOptional.isEmpty()) {
                throw new CanvasObjectNotFoundException("No Canvas with this canvas id present in this project");
            } else {
                CanvasObjectEntity canvasObjectEntity = canvasObjectOptional.get();
                canvasObjectEntity.setValue(canvasObjectModel.getValue());
                canvasObjectEntity.setId(projectId + System.currentTimeMillis());
                projectRepository.save(optionalProject.get());
                return ResponseEntity.ok().body(canvasObjectEntity);
            }
        }
    }

    public ResponseEntity<?> deleteCanvasObject(String projectId, String canvasObjectId) {
        Optional<ProjectEntity> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isEmpty()) {
            throw new ProjectNotFoundException("No Project present with this project id");
        } else {
            Optional<CanvasObjectEntity> canvasObjectOptional = projectRepository.findById(projectId).get()
                    .getCanvasObj().stream()
                    .filter(canvasObject -> canvasObjectId.equals(canvasObject.getId())).findFirst();
            if (canvasObjectOptional.isEmpty()) {
                throw new CanvasObjectNotFoundException("No Canvas with this canvas id present in this project");
            } else {
                ProjectEntity projectEntity = optionalProject.get();
                CanvasObjectEntity canvasObjectEntity = canvasObjectOptional.get();
                projectEntity.getCanvasObj().remove(canvasObjectEntity);
                projectEntity.setUpdatedTime(LocalDateTime.now());
                projectRepository.save(projectEntity);
                return ResponseEntity.ok().body("Canvas deleted successfully");
            }
        }
    }

}
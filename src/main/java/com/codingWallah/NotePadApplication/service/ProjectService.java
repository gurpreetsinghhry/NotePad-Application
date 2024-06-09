package com.codingWallah.NotePadApplication.service;

import com.codingWallah.NotePadApplication.exception.NotePadApplicationException;
import com.codingWallah.NotePadApplication.model.Project;
import com.codingWallah.NotePadApplication.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Optional<Project> getProjectById(String id) {
        return projectRepository.findById(id);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project createProject(Project project) {
        project.setCreatedTime(LocalDateTime.now());
        project.setUpdatedTime(LocalDateTime.now());
        return projectRepository.save(project);
    }

    public Project updateProject(String id, Project project) {
        Optional<Project> existingProject = projectRepository.findById(id);
        if (existingProject.isPresent()) {
            Project updatedProject = existingProject.get();
            updatedProject.setProjectName(project.getProjectName());
            updatedProject.setCanvasObj(project.getCanvasObj());
            updatedProject.setUpdatedTime(LocalDateTime.now());
            return projectRepository.save(updatedProject);
        } else {
            throw new NotePadApplicationException("Project not found with id: " + id);
        }
    }

    public void deleteProjectById(String id) {
        projectRepository.deleteById(id);
    }

    public void deleteAllProjects() {
        projectRepository.deleteAll();
    }
}

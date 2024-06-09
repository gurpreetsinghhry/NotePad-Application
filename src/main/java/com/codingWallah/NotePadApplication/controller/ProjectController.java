package com.codingWallah.NotePadApplication.controller;

import com.codingWallah.NotePadApplication.exception.NotePadApplicationException;
import com.codingWallah.NotePadApplication.exception.UnauthorizedUserException;
import com.codingWallah.NotePadApplication.model.Project;
import com.codingWallah.NotePadApplication.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/projects")
//@RequestMapping(path = "/api/projects", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Operation(summary = "Get a project by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(
            @Parameter(description = "ID of the project to retrieve", required = true) @PathVariable @NotEmpty String id,
            @Parameter(description = "User name for authentication", required = true) @RequestHeader("user_name") @NotEmpty String userName,
            @Parameter(description = "Password for authentication", required = true) @RequestHeader("password") @NotEmpty String password) {
        if (!isValidUser(userName, password)) {
            throw new UnauthorizedUserException("Invalid credentials for guest user");
        }
        Optional<Project> project = projectService.getProjectById(id);
        if (project.isPresent()) {
            return ResponseEntity.ok(project.get());
        } else {
            throw new NotePadApplicationException("Project not found with ID: " + id);
        }
    }

    @Operation(summary = "Get all projects")
    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @Operation(summary = "Create a new project")
    @PostMapping
    public Project createProject(
            @Parameter(description = "Project object to be created", required = true) @Valid @RequestBody Project project) {
        return projectService.createProject(project);
    }

    @Operation(summary = "Update an existing project")
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(
            @Parameter(description = "ID of the project to update", required = true) @PathVariable @NotEmpty String id,
            @Parameter(description = "Updated project object", required = true) @Valid @RequestBody Project project,
            @Parameter(description = "User name for authentication", required = true) @RequestHeader("user_name") @NotEmpty String userName,
            @Parameter(description = "Password for authentication", required = true) @RequestHeader("password") @NotEmpty String password) {
        if (!isValidUser(userName, password)) {
            throw new UnauthorizedUserException("Invalid credentials for guest user");
        }
        Optional<Project> existingProject = projectService.getProjectById(id);
        if (existingProject.isPresent()) {
            return ResponseEntity.ok(projectService.updateProject(id, project));
        } else {
            throw new NotePadApplicationException("Project not found with ID: " + id);
        }
    }

    @Operation(summary = "Delete a project by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjectById(
            @Parameter(description = "ID of the project to delete", required = true) @PathVariable @NotEmpty String id) {
        projectService.deleteProjectById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete all projects")
    @DeleteMapping
    public ResponseEntity<Void> deleteAllProjects() {
        projectService.deleteAllProjects();
        return ResponseEntity.noContent().build();
    }

    private boolean isValidUser(String userName, String password) {
        return "guest_user".equals(userName) && "guest_password".equals(password);
    }
}

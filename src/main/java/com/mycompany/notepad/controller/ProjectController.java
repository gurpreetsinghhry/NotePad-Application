package com.mycompany.notepad.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mycompany.notepad.entity.CanvasObjectEntity;
import com.mycompany.notepad.entity.ProjectEntity;
import com.mycompany.notepad.model.CanvasObjectModel;
import com.mycompany.notepad.model.ProjectModel;
import com.mycompany.notepad.service.ProjectService;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;


    @Operation(summary = "Create new project by providing name and author")
    @PostMapping("/myprojects")
    public ProjectEntity createProject(@RequestBody ProjectModel projectModel) {
        return projectService.createProject(projectModel);
    }

    @Operation(summary = "Create canvas object by providing value")
    @PostMapping("/{projectId}/canvas")
    public ProjectEntity addCanvasObject(@PathVariable String projectId, @RequestBody CanvasObjectModel canvasObjectModel) {
        return projectService.addCanvasObject(projectId, canvasObjectModel);
    }

    @Operation(summary = "To Get all Projects")
    @GetMapping
    public List<ProjectEntity> getAllProjects() {
        return projectService.getAllProjects();
    }

    @Operation(summary = "To Get Project by providing projectId")
    @GetMapping("/{projectId}")
    public ProjectEntity getProjectById(@PathVariable String projectId) {
        return projectService.getProjectById(projectId);
    }

    @Operation(summary = "To Get canvas object by providing projectId")
    @GetMapping("/{projectId}/canvas")
    public List<CanvasObjectEntity> getCanvasObjectsByProjectId(@PathVariable String projectId) {
        return projectService.getCanvasObjectsByProjectId(projectId);
    }

    @Operation(summary = "To Get canvas objectId by providing projectID and canvasObjectId")
    @GetMapping("/{projectId}/canvas/{canvasObjectId}")
    public CanvasObjectEntity getCanvasObjectById(@PathVariable String projectId, @PathVariable String canvasObjectId) {
        return projectService.getCanvasObjectById(projectId, canvasObjectId);
    }

    @Operation(summary = "To Update project name by providing projectID")
    @PutMapping("/{projectId}")
    public ProjectEntity updateProject(@PathVariable String projectId, @RequestBody ProjectModel projectModel) {
        return projectService.updateProject(projectId, projectModel);
    }

    @Operation(summary = "To Delete project by providing projectID")
    @DeleteMapping("/{projectId}")
    public void deleteProject(@PathVariable String projectId) {
        projectService.deleteProject(projectId);
    }

    @Operation(summary = "To Update canvasObjectId by providing projectID and canvasObjectId")
    @PutMapping("/{projectId}/canvas/{canvasObjectId}")
    public ProjectEntity updateCanvasObject(@PathVariable String projectId, @PathVariable String canvasObjectId, @RequestBody CanvasObjectModel canvasObjectModel) {
        return projectService.updateCanvasObject(projectId, canvasObjectId, canvasObjectModel);
    }

    @Operation(summary = "To Delete canvasObjectId by providing projectID and canvasObjectId")
    @DeleteMapping("/{projectId}/canvas/{canvasObjectId}")
    public void deleteCanvasObject(@PathVariable String projectId, @PathVariable String canvasObjectId) {
        projectService.deleteCanvasObject(projectId, canvasObjectId);
    }
}
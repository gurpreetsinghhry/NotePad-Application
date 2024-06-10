package com.mycompany.notepad.controller;

import java.util.List;

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

    @PostMapping
    public String test(){
        return "Done";
    }

    @PostMapping("/myprojects")
    public ProjectEntity createProject(@RequestBody ProjectModel projectModel) {
        return projectService.createProject(projectModel);
    }

    @PostMapping("/{projectId}/canvas")
    public ProjectEntity addCanvasObject(@PathVariable String projectId, @RequestBody CanvasObjectModel canvasObjectModel) {
        return projectService.addCanvasObject(projectId, canvasObjectModel);
    }

     @GetMapping
    public List<ProjectEntity> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{projectId}")
    public ProjectEntity getProjectById(@PathVariable String projectId) {
        return projectService.getProjectById(projectId);
    }

    @GetMapping("/{projectId}/canvas")
    public List<CanvasObjectEntity> getCanvasObjectsByProjectId(@PathVariable String projectId) {
        return projectService.getCanvasObjectsByProjectId(projectId);
    }

    @GetMapping("/{projectId}/canvas/{canvasObjectId}")
    public CanvasObjectEntity getCanvasObjectById(@PathVariable String projectId, @PathVariable String canvasObjectId) {
        return projectService.getCanvasObjectById(projectId, canvasObjectId);
    }

    @PutMapping("/{projectId}")
    public ProjectEntity updateProject(@PathVariable String projectId, @RequestBody ProjectModel projectModel) {
        return projectService.updateProject(projectId, projectModel);
    }

    @DeleteMapping("/{projectId}")
    public void deleteProject(@PathVariable String projectId) {
        projectService.deleteProject(projectId);
    }

    @PutMapping("/{projectId}/canvas/{canvasObjectId}")
    public ProjectEntity updateCanvasObject(@PathVariable String projectId, @PathVariable String canvasObjectId, @RequestBody CanvasObjectModel canvasObjectModel) {
        return projectService.updateCanvasObject(projectId, canvasObjectId, canvasObjectModel);
    }

    @DeleteMapping("/{projectId}/canvas/{canvasObjectId}")
    public void deleteCanvasObject(@PathVariable String projectId, @PathVariable String canvasObjectId) {
        projectService.deleteCanvasObject(projectId, canvasObjectId);
    }
}
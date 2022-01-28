package com.kushnir.tmbtool.web;

import com.kushnir.tmbtool.domain.Project;
import com.kushnir.tmbtool.services.MapValidationErrorService;
import com.kushnir.tmbtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/project")
@CrossOrigin
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult bindingResult, Principal principal) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(bindingResult);
        if (errorMap != null) return errorMap;

        Project project1 = projectService.saveOrUpdateProject(project, principal.getName());
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId) {

        Project project = projectService.findProjectByIdentifier(projectId);

        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Project> getAllProjects() {
        return projectService.findAllProjects();
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId) {
        projectService.deleteProjectByIdentifier(projectId.toUpperCase());

        return new ResponseEntity<String>("Project with ID: '" + projectId + "' was deleted", HttpStatus.OK);
    }

    @PutMapping("{projectId}")
    public ResponseEntity<?> updateProject(@Valid @RequestBody Project project, @PathVariable String projectId, Principal principal) {
        Project project1 = projectService.findProjectByIdentifier(projectId.toUpperCase());

        project1.setProjectName(project.getProjectName());
        project1.setProjectIdentifier(project.getProjectIdentifier());
        project1.setDescription(project.getDescription());

        final Project updatedProject = projectService.saveOrUpdateProject(project1, principal.getName());

        return new ResponseEntity<Project>(updatedProject, HttpStatus.OK);
    }
}

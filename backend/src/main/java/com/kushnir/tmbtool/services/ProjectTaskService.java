package com.kushnir.tmbtool.services;

import com.kushnir.tmbtool.domain.Backlog;
import com.kushnir.tmbtool.domain.Project;
import com.kushnir.tmbtool.domain.ProjectTask;
import com.kushnir.tmbtool.exceptions.ProjectNotFoundException;
import com.kushnir.tmbtool.repositories.BacklogRepository;
import com.kushnir.tmbtool.repositories.ProjectRepository;
import com.kushnir.tmbtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {

        try {
            //PTs to be added to a specific project, project != null, BL exists
            Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
            //Set the Backlog to the PT
            projectTask.setBacklog(backlog);

            Integer backlogSequence = backlog.getPTSequence();
            //Update the Backlog SEQUENCE
            backlogSequence++;

            backlog.setPTSequence(backlogSequence);

            //Add Sequence to the Project Task
            projectTask.setProjectSequence(projectIdentifier + "-" + backlogSequence);
            projectTask.setProjectIdentifier(projectIdentifier);

            //INITIAL priority when priority null
            if (projectTask.getPriority() == 0 || projectTask.getPriority() == null) {
                projectTask.setPriority(3);
            }

            //INITIAL status when status in null
            if (projectTask.getStatus() == "" || projectTask.getStatus() == null) {
                projectTask.setStatus("TO_DO");
            }

            return projectTaskRepository.save(projectTask);
        } catch (Exception e) {
            throw new ProjectNotFoundException("Project not found.");
        }
    }

    public Iterable<ProjectTask> findBacklogById(String id) {

        Project project = projectRepository.findByProjectIdentifier(id);

        if (project == null) {
            throw new ProjectNotFoundException("Project with ID: '" + id + "' does not exist.");
        }

        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
    }

    public ProjectTask findPTByProjectSequence(String backlog_id, String pt_id) {

        //make sure we are searching for an existing project
        Backlog backlog = backlogRepository.findByProjectIdentifier(backlog_id);
        if (backlog == null) {
            throw new ProjectNotFoundException("Project with ID: '" + backlog_id + "' does not exist.");
        }

        //make sure that task exists
        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(pt_id);
        if (projectTask == null) {
            throw new ProjectNotFoundException("Project Task '" + pt_id + "' not found.");
        }

        //make sure that the backlog/project id in the path corresponds to the right project
        if (!projectTask.getProjectIdentifier().equals(backlog_id)) {
            throw new ProjectNotFoundException("Project Task '" + pt_id + "' does not exist in project: " + backlog_id);
        }

        return projectTask;
    }

    public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlog_id, String pt_id) {
        ProjectTask projectTask = findPTByProjectSequence(backlog_id, pt_id);

        projectTask = updatedTask;

        return projectTaskRepository.save(projectTask);
    }

    public void deletePTByProjectSequence(String backlog_id, String pt_id) {
        ProjectTask projectTask = findPTByProjectSequence(backlog_id, pt_id);

        projectTaskRepository.delete(projectTask);
    }
}

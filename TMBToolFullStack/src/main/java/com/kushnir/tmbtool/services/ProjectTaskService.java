package com.kushnir.tmbtool.services;

import com.kushnir.tmbtool.domain.Backlog;
import com.kushnir.tmbtool.domain.ProjectTask;
import com.kushnir.tmbtool.repositories.BacklogRepository;
import com.kushnir.tmbtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {

        //Exceptions: Project not found

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
        if (projectTask.getPriority() == null) {
            projectTask.setPriority(3);
        }

        //INITIAL status when status in null
        if (projectTask.getStatus() == "" || projectTask.getStatus() == null) {
            projectTask.setStatus("TO_DO");
        }

        return projectTaskRepository.save(projectTask);
    }

}

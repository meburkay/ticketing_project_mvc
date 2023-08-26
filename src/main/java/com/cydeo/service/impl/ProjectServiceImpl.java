package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO,String> implements ProjectService {

    private final TaskService taskService;

    public ProjectServiceImpl(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public ProjectDTO save(ProjectDTO project) {

        //Because at the project create form we do not have a status we add it here but we put a condition because if we do not put any condition all the projects become OPEN.
        if(project.getProjectStatus()==null)
            project.setProjectStatus(Status.OPEN);

        return super.save(project.getProjectCode(),project);
    }

    @Override
    public ProjectDTO findById(String projectCode) {
        return super.findById(projectCode);
    }

    @Override
    public List<ProjectDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void update(ProjectDTO object) {

        //When we try to update the object we do not have a place about status. Because of that it came here as null and the app crashed. Because of that we add this if statement and take the objects status from database and assign it to our ProjectDTO object to refrain from null.
        if(object.getProjectStatus()==null){
            object.setProjectStatus(findById(object.getProjectCode()).getProjectStatus());
        }

        super.update(object.getProjectCode(),object);
    }

    @Override
    public void deleteById(String projectCode) {
        super.deleteById(projectCode);
    }

    @Override
    public void complete(ProjectDTO project) {

        project.setProjectStatus(Status.COMPLETE);

    }

    @Override
    public List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager) {

        List<ProjectDTO> projectList =
                findAll()
                        .stream()
                        .filter(project -> project.getAssignedManager().equals(manager))
                        .map(project ->{

                            List<TaskDTO> taskList = taskService.findTasksByManager(manager);

                            int completeTaskCounts = (int) taskList
                                    .stream()
                                    .filter(t -> t.getProject().equals(project) && t.getTaskStatus() == Status.COMPLETE)
                                    .count();
                            int unfinishedTaskCounts = (int) taskList
                                    .stream()
                                    .filter(t -> t.getProject().equals(project) && t.getTaskStatus() != Status.COMPLETE)
                                    .count();


                            project.setCompleteTaskCounts(completeTaskCounts);
                            project.setUnfinishedTaskCounts(unfinishedTaskCounts);


                            return project;

                        })


                        .collect(Collectors.toList());
        return projectList;
    }


}

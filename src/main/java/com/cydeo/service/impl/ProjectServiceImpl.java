package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO,String> implements ProjectService {

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


}

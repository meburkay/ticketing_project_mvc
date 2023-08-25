package com.cydeo.service.impl;

import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskServiceImpl extends AbstractMapService<TaskDTO,Long> implements TaskService {
    @Override
    public TaskDTO save(TaskDTO task) {

        //When we are saving task because of the nonexisting of the Status we assign it here to the object otherwise we have error because of its absent. The second if's logic is the same.
        if(task.getTaskStatus() == null)
            task.setTaskStatus(Status.OPEN);
        if(task.getAssignedDate() == null){
            task.setAssignedDate(LocalDate.now());
        }

        return super.save(task.getId(),task);
    }

    @Override
    public TaskDTO findById(Long id) {
        return super.findById(id);
    }

    @Override
    public List<TaskDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void update(TaskDTO task) {
        super.update(task.getId(),task);
    }
}

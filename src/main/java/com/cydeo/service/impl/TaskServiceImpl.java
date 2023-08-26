package com.cydeo.service.impl;

import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
        //When we saving the new task we have to give the id because even though we do not show it at the UI, we use at as unique field for the tasks.
        if(task.getId()==null)
            task.setId(UUID.randomUUID().getMostSignificantBits());


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

        //We find the not updated task and fill the empty fields with it.
        TaskDTO foundTask = findById(task.getId());

        task.setTaskStatus(foundTask.getTaskStatus());
        task.setAssignedDate(foundTask.getAssignedDate());


//        if(task.getTaskStatus() == null)
//            task.setTaskStatus(Status.OPEN);
//        //TODO We assign LocalDate.now here. But actually this is a update method and we must not change the assigned date when we are updating the project. There is a problem here and above.
//        if(task.getAssignedDate() == null)
//            task.setAssignedDate(findById(task.getId()).getAssignedDate());

        super.update(task.getId(),task);
    }

    @Override
    public List<TaskDTO> findTasksByManager(UserDTO manager) {
        return findAll().stream()
                .filter(task -> task.getProject().getAssignedManager().equals(manager))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> findAllTasksByStatusIsNot(Status status) {
        return findAll().stream().filter(task -> !task.getTaskStatus().equals(status))
                .collect(Collectors.toList());
    }

}

package com.cydeo.controller;

import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/task")
public class TaskController {


    private final UserService userService;
    private final ProjectService projectService;
    private final TaskService taskService;

    public TaskController(UserService userService, ProjectService projectService, TaskService taskService) {
        this.userService = userService;
        this.projectService = projectService;
        this.taskService = taskService;
    }
    @GetMapping("/create")
    public String createTask(Model model){

        model.addAttribute("task",new TaskDTO());
        model.addAttribute("projects",projectService.findAll());
        model.addAttribute("employees",userService.findEmployees());
        model.addAttribute("tasks",taskService.findAll());


        return "task/create";
    }

    @PostMapping("/create")
    public String insertTask(TaskDTO task){

        taskService.save(task);

        return "redirect:/task/create";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") Long id){

        taskService.deleteById(id);

        return "redirect:/task/create";
    }

    @GetMapping("/update/{taskId}")
    public String editTask(@PathVariable("taskId") Long taskId,Model model){

//        System.out.println("taskService.findById(taskId) = " + taskService.findById(taskId));

        model.addAttribute("task",taskService.findById(taskId));
        model.addAttribute("projects",projectService.findAll());
        model.addAttribute("employees",userService.findEmployees());
        model.addAttribute("tasks",taskService.findAll());

        return "/task/update";
    }

    //    @PostMapping("/update/{taskId}")
//    public String updateTask(@PathVariable("taskId") Long taskId,TaskDTO task){
//
//        task.setId(taskId);
//
//        taskService.update(task);
//
//        return "redirect:/task/create";
//    }

    //Normally we did not use path variable at updates of other elements. But here we give the id of the task as a path variable.
    @PostMapping("/update/{id}")//As far as I understand althoudh we send a full object to the thymeleaf when we are getting back that data we only have the variables that we do operation. If we don't operate any object variable it get back as null. Because of that we especially get back the id and assign it to the task object. Because we use id to do operation to task objects.

    //TODO I need to look at here and analyze. Why model attribute did not used for task object instead we directly use task object. We give the objcet to view. And it used it and it update it with the components that it has but the other parts of the object became null after the operation. I do not understand there actually.
    public String updateTask(TaskDTO task){
//        System.out.println("task.toString() = " + task.toString());
        taskService.update(task);

        return "redirect:/task/create";
    }


    @GetMapping("/employee/pending-tasks")
    public String employeePendingTasks(Model model) {
        model.addAttribute("tasks", taskService.findAllTasksByStatusIsNot(Status.COMPLETE));


        return "/task/pending-tasks";

    }

    @GetMapping("/employee/archive")
    public String employeeArchivedTasks(Model model) {
        model.addAttribute("tasks", taskService.findAllTasksByStatus(Status.COMPLETE));
        return "/task/archive";
    }

    @GetMapping("/employee/edit/{id}")
    public String employeeEditTask(@PathVariable Long id, Model model) {
        model.addAttribute("task", taskService.findById(id));
//        model.addAttribute("projects", projectService.findAll());
//        model.addAttribute("employees", userService.findEmployees());
        model.addAttribute("statuses", Status.values());
        model.addAttribute("tasks", taskService.findAllTasksByStatusIsNot(Status.COMPLETE));
        return "/task/status-update";
    }


    @PostMapping("/employee/update/{id}")
    public String employeeUpdateTask(TaskDTO task) {
        taskService.updateStatus(task);
        return "redirect:/task/employee/pending-tasks";
    }

}

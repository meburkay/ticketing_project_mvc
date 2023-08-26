package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.service.ProjectService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private final UserService userService;
    private final ProjectService projectService;

    public ProjectController(UserService userService, ProjectService projectService) {
        this.userService = userService;
        this.projectService = projectService;
    }

    @GetMapping("/create")
    public String createProject(Model model){

        model.addAttribute("project",new ProjectDTO());

        model.addAttribute("managers", userService.findManagers());

        model.addAttribute("projects",projectService.findAll());


        return "/project/create";
    }

    @PostMapping("/create")
    public String insertProject(@ModelAttribute("project") ProjectDTO project){

        projectService.save(project);

        return "redirect:/project/create";
    }

    @GetMapping("/delete/{projectCode}")
    public String deleteProject(@PathVariable("projectCode") String projectCode){

        projectService.deleteById(projectCode);

        return "redirect:/project/create";

    }

    @GetMapping("/complete/{projectCode}")
    public String completeProject(@PathVariable("projectCode") String projectCode){

        projectService.complete(projectService.findById(projectCode));

        return "redirect:/project/create";
    }

    @GetMapping("/update/{projectCode}")
    public String editProject(@PathVariable("projectCode") String projectCode, Model model){

        model.addAttribute("project",projectService.findById(projectCode));
        model.addAttribute("managers",userService.findManagers());
        model.addAttribute("projects",projectService.findAll());

        return "/project/update";
    }

    @PostMapping("/update")
    public String updateProject(@ModelAttribute("project") ProjectDTO project){

        projectService.update(project);

        return "redirect:/project/create";
    }

    @GetMapping("/manager/project-status")
    public String getProjectByManager(Model model){

        //We hardcoded here because we need a manager info but because we do not implement security we can not take it from login. This is temporary.
        UserDTO manager = userService.findById("john@cydeo.com");


        //We need to get the projects which belongs to certain manager because of that we need a new service method.
        List<ProjectDTO> projects = projectService.getCountedListOfProjectDTO(manager);


        model.addAttribute("projects",projects);


        return "/manager/project-status";
    }


}

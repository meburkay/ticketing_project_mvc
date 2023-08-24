package com.cydeo.controller;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final RoleService roleService;
    private final UserService userService;

    public UserController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createUser(Model model){

        model.addAttribute("user",new UserDTO());

        model.addAttribute("roles",roleService.findAll()); //We must take all the roles from DB and give them to view for the dropdown button.

        model.addAttribute("users", userService.findAll());

        return "/user/create";
    }

    //We create this class for the submit button. When we click submit button this method will execute. Because it will send data from the form it is a @PostMapping method. And to take the data from form we use @ModelAttribute.
    @PostMapping("/create")
    public String insertUser(@ModelAttribute("user") UserDTO user, Model model){


        model.addAttribute("user",new UserDTO());
        model.addAttribute("roles",roleService.findAll());

        userService.save(user);

        model.addAttribute("users",userService.findAll());

        return "/user/create";

    }
}

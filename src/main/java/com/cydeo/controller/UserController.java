package com.cydeo.controller;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public String insertUser(@Valid @ModelAttribute("user") UserDTO user, BindingResult bindingResult, Model model){

        //Validation part. We put @Valid to the object that we want to add validation and to control that validation we put BindingResult.
        if (bindingResult.hasErrors()) {

            model.addAttribute("roles",roleService.findAll());
            model.addAttribute("users",userService.findAll());

            return "/user/create";
        }

        userService.save(user);

        return "redirect:/user/create";//By redirect, we go to end point means method. Because of that we do not need the commented parts. We do the save operation and go to user/create endpoint method. This implementations already done there.
    }

    @GetMapping("/update/{username}")//We use here pathvariable to take the data from the UI to here. And by this information we take the user object from the database by userService.finById method. And we use it at the user/update ui html view.
    public String editUser(@PathVariable("username") String username, Model model){

        model.addAttribute("user",userService.findById(username));
        model.addAttribute("roles",roleService.findAll());
        model.addAttribute("users",userService.findAll());

        return "/user/update";
    }

    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute("user") UserDTO user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("roles", roleService.findAll());
            model.addAttribute("users", userService.findAll());

            return "/user/update";

        }

        userService.update(user);

        return "redirect:/user/create";

    }

    @GetMapping("/delete/{username}")
    public String deleteUser(@PathVariable("username") String username){

        userService.deleteById(username);

        return "redirect:/user/create";

    }



}

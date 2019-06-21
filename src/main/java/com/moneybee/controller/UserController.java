package com.moneybee.controller;

import com.moneybee.entity.UserEntity;
import com.moneybee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String listCustomers(Model model) {
        List<UserEntity> users = userService.getUsers();
        model.addAttribute("users", users);
        return "users";
    }


}

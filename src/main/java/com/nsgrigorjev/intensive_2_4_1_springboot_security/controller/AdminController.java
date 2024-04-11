package com.nsgrigorjev.intensive_2_4_1_springboot_security.controller;

import com.nsgrigorjev.intensive_2_4_1_springboot_security.database.entity.User;
import com.nsgrigorjev.intensive_2_4_1_springboot_security.dto.UserCreationDto;
import com.nsgrigorjev.intensive_2_4_1_springboot_security.dto.UserResponseDto;
import com.nsgrigorjev.intensive_2_4_1_springboot_security.mapper.UserMapper;
import com.nsgrigorjev.intensive_2_4_1_springboot_security.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final UserService userService;
    private static final String REDIRECT = "redirect:/";

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "all_users";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        return "user_create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String saveNewUser(@ModelAttribute("user") @Valid UserCreationDto user,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user_create";
        } else {
            userService.persist(UserMapper.toEntity(user));
            return REDIRECT;
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String updateUser(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user_edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String saveUpdateUser(@ModelAttribute("user") @Valid UserResponseDto user,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user_edit";
        } else {
            userService.update(UserMapper.toEntity(user));
            return REDIRECT;
        }
    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String deleteUser(@RequestParam("id") Long id,
                             @ModelAttribute("user") UserResponseDto user) {
        userService.deleteById(id);
        return REDIRECT;
    }
}

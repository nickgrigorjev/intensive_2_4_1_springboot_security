package com.nsgrigorjev.intensive_2_4_1_springboot_security.controller;

import com.nsgrigorjev.intensive_2_4_1_springboot_security.dto.UserCreationDto;
import com.nsgrigorjev.intensive_2_4_1_springboot_security.dto.UserResponseDto;
import com.nsgrigorjev.intensive_2_4_1_springboot_security.service.RoleService;
import com.nsgrigorjev.intensive_2_4_1_springboot_security.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final String REDIRECT_TO_USERS = "redirect:/admin/users";
    private final UserService userService;
    private final RoleService roleService;


    @RequestMapping(value = "", method = GET)
    public String adminPage() {
        return "admin";
    }

    @RequestMapping(value = "/users", method = GET)
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "all_users";
    }

    @RequestMapping(value = "/new", method = GET)
    public String addNewUser(@ModelAttribute("user") UserCreationDto user) {
        return "user_create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String saveNewUser(@ModelAttribute("user") @Valid UserCreationDto user,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/admin/create";
        } else {
            userService.persist(user);
            return REDIRECT_TO_USERS;
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String saveUpdateUser(@ModelAttribute("user") @Valid UserResponseDto user,
                                 BindingResult bindingResult,
                                 @RequestParam(value = "rolesFromView", required = false) List<String> rolesFromView,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roleService.findAll());
            return "user_edit";
        } else {
            userService.update(user, rolesFromView);
            return REDIRECT_TO_USERS;
        }
    }

    @RequestMapping(value = "/edit", method = GET)
    public String updateUser(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("roles", roleService.findAll());
        return "user_edit";
    }

    @RequestMapping(value = "/remove", method = GET)
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteById(id);
        return REDIRECT_TO_USERS;
    }
}

package com.nsgrigorjev.intensive_2_4_1_springboot_security.controller;

import com.nsgrigorjev.intensive_2_4_1_springboot_security.dto.UserCreationDto;
import com.nsgrigorjev.intensive_2_4_1_springboot_security.service.UserService;
import com.nsgrigorjev.intensive_2_4_1_springboot_security.util.UserValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserValidator userValidator;
    private final UserService userService;

    @RequestMapping(value = "/login", method = GET)
    public String loginPage() {
        return "auth/login";
    }

    @RequestMapping(value = "/registration", method = GET)
    public String registrationPage(@ModelAttribute("user") UserCreationDto userDto) {
        return "auth/registration";
    }


    @RequestMapping(value = "/registration", method = POST)
    public String registration(@ModelAttribute("user") @Valid UserCreationDto userDto,
                               BindingResult bindingResult) {

        userValidator.validate(userDto, bindingResult);

        if (bindingResult.hasErrors()) {
            return "auth/registration";
        } else {
            userService.persist(userDto);
            return "redirect:/auth/login";
        }
    }
}

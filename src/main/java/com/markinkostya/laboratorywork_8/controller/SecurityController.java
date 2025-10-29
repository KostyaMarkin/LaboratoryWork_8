package com.markinkostya.laboratorywork_8.controller;


import com.markinkostya.laboratorywork_8.dto.UserDto;
import com.markinkostya.laboratorywork_8.entity.User;
import com.markinkostya.laboratorywork_8.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
public class SecurityController {

    private UserService userService;

    public SecurityController(UserService userService) { this. userService = userService; }

    @GetMapping("/index")
    public String home() {return "index";}

    @GetMapping("/login")
    public String login(){ return "login";}

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());
        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null, "На этот адрес электронной почты уже зарегистрирована учетная запись.");
        }

        if(result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String users(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}

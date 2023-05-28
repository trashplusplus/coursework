package com.coursework.controllers;

import com.coursework.AuthenticationCreator;
import com.coursework.MyUser;
import com.coursework.config.SecurityConfig;
import com.coursework.services.MyUserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private MyUserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationCreator auth;
    public AuthController(MyUserServiceImpl userService,
                          PasswordEncoder passwordEncoder,
                          AuthenticationCreator auth){
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.auth = auth;
    }


    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("myUser", new MyUser());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("myUser") MyUser myUser,
                           RedirectAttributes redirectAttributes){
        if(userService.findByUsername(myUser.getUsername()) == null &&
        userService.findByEmail(myUser.getEmail()) == null){
            String encodedPassword = passwordEncoder.encode(myUser.getPassword());
            System.out.println("Записан: " + myUser);
            myUser.setRole("USER");
            myUser.setPassword(encodedPassword);
            userService.save(myUser);
            redirectAttributes.addFlashAttribute("success", String.format("%s is successfully registered", myUser.getUsername()));
        }else{
            redirectAttributes.addFlashAttribute("failure", "A user with this username or email already exists");
        }




        //default value = USER
        //myUser.set("USER");



        return "redirect:/register";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("myUser", new MyUser());
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        request.logout();
        SecurityContextHolder.clearContext();
        return "redirect:/";
    }

}

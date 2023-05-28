package com.coursework.controllers;

import com.coursework.AuthenticationCreator;
import com.coursework.MyUser;
import com.coursework.Product;
import com.coursework.services.MyUserServiceImpl;
import com.coursework.services.ProductServiceImpl;
import org.hibernate.boot.SchemaAutoTooling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SimpleController {

    private final MyUserServiceImpl userService;
    private final ProductServiceImpl productService;

    private final AuthenticationCreator auth;


    public SimpleController(MyUserServiceImpl userService,
                            ProductServiceImpl productService,
                            AuthenticationCreator auth){
        this.userService = userService;
        this.productService = productService;
        this.auth = auth;
    }

    @GetMapping({"/", "/main"})
    public String getMain(Model model){
        String currentName = auth.getName();
        if(currentName != "anonymousUser")
          model.addAttribute("loggedUsername", currentName);
        return "main";
    }

    @GetMapping("/user")
    public String user(Model model){
        String currentName = auth.getName();
        MyUser myUser = userService.findByUsername(currentName);
        model.addAttribute("loggedUsername", myUser.getUsername());
        model.addAttribute("loggedEmail", myUser.getEmail());
        return "user";
    }

    @GetMapping("/settings")
    public String settings(Model model){
        String currentName = auth.getName();
        MyUser myUser = userService.findByUsername(currentName);
        model.addAttribute("loggedUsername", currentName);
        model.addAttribute("userRole", myUser.getRole());

        return "settings";
    }

    @GetMapping("/hi")
    public String get(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello";
    }

    @GetMapping("/catalog")
    public String getCatalog(Model model){
        String currentName =  auth.getName();
        model.addAttribute("loggedUsername", currentName);
        model.addAttribute("productList", productService.findAll());

        return "catalog";
    }

    @GetMapping("/catalog/{productId}")
    public String checkProduct(@PathVariable long productId, Model model){;
        Product product = productService.getById(productId);
        model.addAttribute("loggedUsername", auth.getName());
        model.addAttribute("currentProduct", product);
        return "currentProduct";
    }


}

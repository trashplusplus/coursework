package com.coursework.controllers;

import com.coursework.AuthenticationCreator;
import com.coursework.entity.MyUser;
import com.coursework.entity.Product;
import com.coursework.services.MyUserServiceImpl;
import com.coursework.services.ProductServiceImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/settings")
public class SettingsController {

    private final MyUserServiceImpl userService;
    private final ProductServiceImpl productService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationCreator auth;

    public SettingsController(MyUserServiceImpl userService,
                              PasswordEncoder passwordEncoder,
                              ProductServiceImpl productService,
                              AuthenticationCreator auth){
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.productService = productService;
        this.auth = auth;
    }

    @GetMapping("/password")
    public String editPassword(Model model){
        String currentName = auth.getName();
        model.addAttribute("loggedUsername", currentName);
        model.addAttribute("oldPassword", new String());
        model.addAttribute("newPassword", new String());
        return "password";
    }

    @PostMapping("/password")
        public String editPasswordPost(@RequestParam("oldPassword") String oldPassword,
                                       @RequestParam("newPassword") String newPassword,
                                       RedirectAttributes redirectAttributes){
        MyUser currentUser = userService.findByUsername(auth.getName());

        String userPassword = currentUser.getPassword();

        if(passwordEncoder.matches(oldPassword, userPassword)){
            currentUser.setPassword(passwordEncoder.encode(newPassword));
            userService.save(currentUser);
            redirectAttributes.addFlashAttribute("successPassword", "Success! Password was changed!");

        }else{
            redirectAttributes.addFlashAttribute("failurePassword", "Current password is wrong");

        }

        return "redirect:/settings/password";

        }

        @GetMapping("/admin")
        public String adminPage(Model model){
        String currentName = auth.getName();
        model.addAttribute("loggedUsername", currentName);
        model.addAttribute("roleUsername", new String());
        model.addAttribute("userRole", new String());

        List<MyUser> userList = userService.findAll();
        model.addAttribute("userList", userList);

        return "admin";
    }

    @PostMapping("/admin/role")
    public String updateRole(@RequestParam("roleUsername") String roleUsername,
                             @RequestParam("userRole") String userRole,
                             RedirectAttributes redirectAttributes){

        MyUser myUser = userService.findByUsername(roleUsername);

        if(myUser != null && myUser.getUsername() != auth.getName()) {
            myUser.setRole(userRole);
            userService.save(myUser);
            System.out.printf("%s, %s", roleUsername, userRole);
            redirectAttributes.addFlashAttribute("success", "Success! Role has been changed!");
        }else{
            redirectAttributes.addFlashAttribute("failure", "Wrong username!");
        }

        return "redirect:/settings/admin";
    }

    @GetMapping("/product")
    public String getProduct(Model model){
        String currentName = auth.getName();
        model.addAttribute("loggedUsername", currentName);
        model.addAttribute("myProduct", new Product());
        return "addProduct";
    }

    @PostMapping("/product")
    public String postProduct(@ModelAttribute("myProduct") Product myProduct, RedirectAttributes redirectAttributes){

        if(productService.findByTitle(myProduct.getTitle())==null){
            productService.save(myProduct);
            redirectAttributes.addFlashAttribute("success", String.format("%s[%s] sucessfully added",
                    myProduct.getTitle(), myProduct.getCost()));
        }else{
            redirectAttributes.addFlashAttribute("failure", "Something went wrong.");
        }

        System.out.println(myProduct);


        return "redirect:/settings/product";
    }

    //assembler

    @GetMapping("/assembler")
    public String assemblerPage(Model model) {
        String currentName = auth.getName();
        model.addAttribute("loggedUsername", currentName);
        model.addAttribute("roleUsername", new String());
        model.addAttribute("userRole", new String());

        List<Product> productList = productService.findAll();
        model.addAttribute("productList", productList);

        return "assembler";
    }

    @PostMapping("/assembler/product")
    public String changeState(@RequestParam("productId") Long id,
                              @RequestParam("productState") String state,
                              RedirectAttributes redirectAttributes){
        System.out.println("IDDD: " + id);
        Product currentProduct = productService.getById(id);

        if(currentProduct.getId() != null){
            currentProduct.setState(state);
            productService.save(currentProduct);
            redirectAttributes.addFlashAttribute("success", String.format("%s[%s] successfully update", currentProduct.getTitle(), currentProduct.getId()));
        }else{
            redirectAttributes.addFlashAttribute("failure", "Something went wrong!");
        }


        return "redirect:/settings/assembler";
    }

    @GetMapping("/operator")
    public String getOperatorPage(Model model){
        String currentName = auth.getName();
        model.addAttribute("loggedUsername", currentName);
        return "operator";
    }



}

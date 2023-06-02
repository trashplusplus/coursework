package com.coursework.controllers;

import com.coursework.AuthenticationCreator;
import com.coursework.entity.BasketProduct;
import com.coursework.entity.Order;
import com.coursework.services.BasketService;
import com.coursework.services.BasketServiceImpl;
import com.coursework.entity.MyUser;
import com.coursework.entity.Product;
import com.coursework.services.MyUserServiceImpl;
import com.coursework.services.ProductServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Controller
public class SimpleController {

    private final MyUserServiceImpl userService;
    private final BasketServiceImpl basketService;
    private final ProductServiceImpl productService;

    private final AuthenticationCreator auth;



    public SimpleController(MyUserServiceImpl userService,
                            ProductServiceImpl productService,
                            BasketServiceImpl basketService,
                            AuthenticationCreator auth){
        this.userService = userService;
        this.productService = productService;
        this.auth = auth;
        this.basketService = basketService;
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
        String currentName = auth.getName();
        if(currentName != "anonymousUser")
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

    @PostMapping("/catalog/{productId}")
    public String postProduct(@PathVariable long productId, Model model){
        //добавление товара
        Product product = productService.getById(productId);
        if(product.getState().equals("Available")){
            BasketProduct basketProduct = new BasketProduct();
            basketProduct.setProductId(productId);
            basketProduct.setUserId(userService.findByUsername(auth.getName()).getId());
            basketService.save(basketProduct);

            model.addAttribute("loggedUsername", auth.getName());
            model.addAttribute("currentProduct", product);
        }


        return "redirect:/basket";
    }



    @GetMapping("/basket")
    public String getBasket(Model model){

        Long currentUserId = userService.findByUsername(auth.getName()).getId();
        int totalCost = 0;
        List<Product> basketProductsTitles = new ArrayList<>();
        System.out.println(basketService.getAllByUserId(currentUserId).size());

            //получаем товары по их id
            for (BasketProduct product : basketService.getAllByUserId(currentUserId)) {
                    basketProductsTitles.add(productService.getById(product.getProductId()));
                    totalCost += productService.getById(product.getProductId()).getCost();
            }

        Order myOrder = new Order();
            myOrder.setCost(String.format("%d", totalCost));
            myOrder.setUserId(userService.findByUsername(auth.getName()).getId());
            //myOrder.setOrderDate(new LocalDate());
        model.addAttribute("basketList", basketProductsTitles);
        model.addAttribute("totalSum", totalCost);
        model.addAttribute("myOrder", myOrder);
        model.addAttribute("loggedUsername", auth.getName());

        return "basket";
    }

    @PostMapping("/basket/remove/{productId}")
    public String removeProductFromBasket(@PathVariable Long productId,
                                          RedirectAttributes redirectAttributes){
        Long currentUserId = userService.findByUsername(auth.getName()).getId();
        List<BasketProduct> productList = basketService.getAllByUserId(currentUserId);

        for(BasketProduct product: productList){
            if(product.getProductId() == productId){
                redirectAttributes.addFlashAttribute("success",
                        String.format("%s is successfully deleted", productService.getById(product.getProductId()).getTitle()));
                basketService.delete(product);
                break;
            }else{
                redirectAttributes.addFlashAttribute("failure", "Something went wrong");
            }
        }


        System.out.println(productId);
        return "redirect:/basket";
    }

    //finish the order
    @PostMapping("/basket/finish")
    public String postOrderFinish(@ModelAttribute("myOrder") Order myOrder, Model model){

        System.out.println(myOrder.getCost());
        model.addAttribute("loggedUsername", auth.getName());

        return "finishOrder";
    }

    @PostMapping("/basket/finish/done")
    public String postOrderFinishDone(@ModelAttribute("myOrder") Order myOrder, Model model){
        myOrder.setUserId(userService.findByUsername(auth.getName()).getId());
        System.out.println(myOrder);

        model.addAttribute("loggedUsername", auth.getName());
        //заказ успешно оформлен, redirectAttributes
        return "redirect:/user";
    }


}

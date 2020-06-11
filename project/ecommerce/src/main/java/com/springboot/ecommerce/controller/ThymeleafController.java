package com.springboot.ecommerce.controller;

import com.springboot.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ThymeleafController {
    @Autowired
    ProductService productService;

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/products")
    public String listProducts(@RequestParam("id") Integer id, Model model){
        model.addAttribute("products",productService.listProduct(id));
        return "products";
    }


}

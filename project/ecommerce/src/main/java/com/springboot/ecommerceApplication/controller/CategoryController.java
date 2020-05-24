package com.springboot.ecommerceApplication.controller;

import com.springboot.ecommerceApplication.domain.product.Category;
import com.springboot.ecommerceApplication.dto.CategoryDto;
import com.springboot.ecommerceApplication.dto.SuccessDto;
import com.springboot.ecommerceApplication.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    //........TO ADD A CATEGORY BY ADMIN.....................
    @PostMapping("/add")
    public ResponseEntity<SuccessDto> addCategory(HttpServletRequest httpServletRequest, @Valid @RequestBody CategoryDto categoryDto){
        Principal principal = httpServletRequest.getUserPrincipal();
        String username = principal.getName();
        return categoryService.addCategory(username, categoryDto);

    }

    //..............................TO VIEW A CATEGORY BY ADMIN.......................
    @GetMapping("/{categoryId}")
    public CategoryDto getCategory(@PathVariable Integer categoryId, HttpServletRequest httpServletRequest,Category category)
    {
        Principal principal=httpServletRequest.getUserPrincipal();
        String username=principal.getName();
        return categoryService.getCategoryByAdmin(username,categoryId);
    }

    //.................TO LIST CATEGORY BY ADMIN.......................
    @GetMapping("/list")
    public List<CategoryDto> getAllCategories(HttpServletRequest httpServletRequest)
    {    Principal principal=httpServletRequest.getUserPrincipal();
        String username=principal.getName();
        return categoryService.getAllCategories(username);
    }

    //...................TO UPDATE CATEGORY BY ADMIN....................
    @PutMapping("/update/{categoryId}")
    public ResponseEntity<String> updateCategory(@PathVariable Integer categoryId,
                                                         @Valid @RequestBody CategoryDto categoryDto,
                                                         HttpServletRequest httpServletRequest) {
        Principal principal = httpServletRequest.getUserPrincipal();
        String username = principal.getName();
        return categoryService.updateCategoryByAdmin(username, categoryId, categoryDto);
    }


}

package com.springboot.ecommerceApplication.services;

import com.springboot.ecommerceApplication.domain.product.Category;
import com.springboot.ecommerceApplication.domain.product.ProductVariation;
import com.springboot.ecommerceApplication.domain.user.Seller;
import com.springboot.ecommerceApplication.domain.user.User;
import com.springboot.ecommerceApplication.dto.CategoryDto;
import com.springboot.ecommerceApplication.exception.InvalidDetails;
import com.springboot.ecommerceApplication.repositories.CategoryRepo;
import com.springboot.ecommerceApplication.repositories.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    MessageSource messageSource;

    //..........TO ADD A CATEGORY BY ADMIN...................
    public ResponseEntity<String> addCategory(String username, CategoryDto categoryDto) {
        User user = userRepo.findByEmail(username);
        if (categoryRepo.findByName(categoryDto.getName()) != null) {
            throw new InvalidDetails("This Category Already Exists");
        }
        ResponseEntity<String> responseEntity;

        Category category = new Category(categoryDto.getName());
        categoryRepo.save(category);
        responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage
                ("message-category-added", null, LocaleContextHolder.getLocale()));
        return responseEntity;

    }

    //..............................TO VIEW A CATEGORY BY ADMIN........................
    public CategoryDto getCategoryByAdmin(String username, Integer categoryId) {
        User user = userRepo.findByEmail(username);
        Optional<Category> optional = categoryRepo.findById(categoryId);
        if (!optional.isPresent()) {
            throw new InvalidDetails("This category does not exist");
        }
        Category category = categoryRepo.findById(categoryId).get();
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
        categoryDto.setId(category.getId());
        return categoryDto;
    }

    //.........................TO GET LIST OF CATEGORY BY ADMIN..............................
    public List<CategoryDto> getAllCategories(String username) {
        User user = userRepo.findByEmail(username);
        Iterable<Category> categoryList = categoryRepo.findAll();
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        categoryList.forEach(categories -> categoryDtoList.add(new CategoryDto(categories.getId(), categories.getName())));
        return categoryDtoList;
    }

    //........................TO UPDATE CATEGORY BY ADMIN...................................
    public ResponseEntity<String> updateCategoryByAdmin(String username, Integer categoryId, CategoryDto categoryDto) {
        User user = userRepo.findByEmail(username);
        if(!categoryRepo.findById(categoryId).isPresent()){
            throw new InvalidDetails("No category with the given categoryId exists ");
        }

        ResponseEntity<String> responseEntity;
        Category category = categoryRepo.findById(categoryId).get();
        categoryDto.setId(categoryId);
        BeanUtils.copyProperties(categoryDto, category);

        categoryRepo.save(category);
        responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage
                ("message-category-updated",  null, LocaleContextHolder.getLocale()));
        return responseEntity;

    }
}

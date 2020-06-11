package com.springboot.ecommerce.services;

import com.springboot.ecommerce.domain.product.Category;
import com.springboot.ecommerce.domain.user.User;
import com.springboot.ecommerce.dto.CategoryDto;
import com.springboot.ecommerce.dto.SuccessDto;
import com.springboot.ecommerce.exception.InvalidDetails;
import com.springboot.ecommerce.repositories.CategoryRepo;
import com.springboot.ecommerce.repositories.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    MessageSource messageSource;

    //..........TO ADD A CATEGORY BY ADMIN...................
    public ResponseEntity<SuccessDto> addCategory(String username, CategoryDto categoryDto) {
        User user = userRepo.findByEmail(username);
        if (categoryRepo.findByName(categoryDto.getName()) != null) {
            throw new InvalidDetails("This Category Already Exists");
        }
        Integer categoryId = null;
        ResponseEntity<SuccessDto> responseEntity;
        Category category = new Category();
        Optional<Category> optionalCategory = categoryRepo.findById(categoryDto.getParentId());
        if (optionalCategory.isPresent()) {
            while (categoryDto != null) {//getId
                category.setName(categoryDto.getName());
                Category parent = optionalCategory.get();
                category.setParentCategory(parent);
                categoryDto = categoryDto.getParentCategory();

                categoryRepo.save(category);
                categoryId = categoryRepo.findByName(category.getName()).getId();

            }
        }
        SuccessDto successDto = new SuccessDto();
        successDto.setSuccess("Category is successfully added with the above categoryId:");
        successDto.setId(categoryId);
        responseEntity = ResponseEntity.status(HttpStatus.OK).body(successDto);
        return responseEntity;


    }

    //..............................TO VIEW A CATEGORY BY ADMIN........................
    public CategoryDto getCategoryByAdmin(String username, Integer categoryId) {
        User user = userRepo.findByEmail(username);
        Optional<Category> optional = categoryRepo.findById(categoryId);
        if (!optional.isPresent()) {
            throw new InvalidDetails("This category does not exist");
        }
        CategoryDto categoryDto = new CategoryDto();
        Optional<Category> optionalCategory=  categoryRepo.findById(categoryId);
        if(optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            List<CategoryDto> list1 = new ArrayList<>();
            List<Category> categoryList = category.getSubCategoryList();
            Iterator<Category> itr = categoryList.iterator();
//        while (itr.hasNext()){
//            list1.add(new CategoryDto(itr.next().getId(),itr.next().getName());
//         categoryDto.setChildCategory(list1);
//        }
////        Optional<Category> child = categoryRepo.findById(categoryDto.getId());
//
//        while (child.getParentCategory()!=null){
//           Category parent= category.getParentCategory();
//           CategoryDto parentDto= new CategoryDto();
//           parentDto.setId(parent.getId());
//           parentDto.setName(parent.getName());
//           parentDto.
//    }

//        HashMap<Integer, String> categories = new HashMap<>();
//        categories.put(category.getId(), category.getName());
//        while (category.getParentCategory() != null) {
//            category = category.getParentCategory();
//            categories.put(category.getId(), category.getName());
//        }
        }
        return categoryDto;


    }

    //.........................TO GET LIST OF CATEGORY BY ADMIN..............................//edit
    public List<CategoryDto> getAllCategories(String username) {
        User user = userRepo.findByEmail(username);
        Iterable<Category> categoryList = categoryRepo.findAll();
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        categoryList.forEach(categories -> categoryDtoList.add(
                new CategoryDto(categories.getId(), categories.getName())));
        return categoryDtoList;
    }

    //........................TO UPDATE CATEGORY BY ADMIN...................................
    public ResponseEntity<String> updateCategoryByAdmin(String username, Integer categoryId, CategoryDto categoryDto) {
        User user = userRepo.findByEmail(username);
        if (!categoryRepo.findById(categoryId).isPresent()) {
            throw new InvalidDetails("No category with the given categoryId exists ");
        }

        ResponseEntity<String> responseEntity = null;
        Optional<Category> optionalCategory= categoryRepo.findById(categoryId);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            categoryDto.setId(categoryId);
            //to check unique name
            BeanUtils.copyProperties(categoryDto, category);

            categoryRepo.save(category);
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage
                    ("message-category-updated", null, LocaleContextHolder.getLocale()));
        }
        return responseEntity;

    }
}


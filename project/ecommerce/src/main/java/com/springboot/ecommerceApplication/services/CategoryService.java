package com.springboot.ecommerceApplication.services;

import com.springboot.ecommerceApplication.domain.product.Category;
import com.springboot.ecommerceApplication.domain.user.User;
import com.springboot.ecommerceApplication.dto.CategoryDto;
import com.springboot.ecommerceApplication.dto.SuccessDto;
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
        while (categoryDto != null) {//getId
            category.setName(categoryDto.getName());
            Category parent = categoryRepo.findById(categoryDto.getParentId()).get();
            category.setParentCategory(parent);
            categoryDto = categoryDto.getParentCategory();
//
//        }
//
            categoryRepo.save(category);
             //categoryId = categoryRepo.findByName(categoryDto.getName()).getId();
             categoryId=categoryRepo.findByName(category.getName()).getId();

        }


//        if (categoryDto.getParentId() != null) {
//            Category parent = categoryRepo.findById(categoryDto.getParentId()).get();
//            Category category = new Category();
//            category.setName(categoryDto.getName());
//            category.setParentCategory(parent);
//            categoryRepo.save(category);
//        } else {
//            Category category = new Category();
//            category.setName(categoryDto.getName());
//            categoryRepo.save(category);
//        }
            SuccessDto successDto = new SuccessDto();
            successDto.setSuccess("Category is successfully added with the above categoryId:");
            successDto.setId(categoryId);
            // Category category = new Category(categoryDto.getName());
            // categoryRepo.save(category);
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
        Category category = categoryRepo.findById(categoryId).get();
        List<CategoryDto> list1= new ArrayList<>();
        List<Category> categoryList = category.getSubCategoryList();
        Iterator<Category> itr= categoryList.iterator();
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

        ResponseEntity<String> responseEntity;
        Category category = categoryRepo.findById(categoryId).get();
        categoryDto.setId(categoryId);
        //to check unique name
        BeanUtils.copyProperties(categoryDto, category);

        categoryRepo.save(category);
        responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage
                ("message-category-updated", null, LocaleContextHolder.getLocale()));
        return responseEntity;

    }
}


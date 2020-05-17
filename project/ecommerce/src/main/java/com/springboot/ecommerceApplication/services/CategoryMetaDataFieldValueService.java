package com.springboot.ecommerceApplication.services;

import com.springboot.ecommerceApplication.domain.product.*;
import com.springboot.ecommerceApplication.domain.user.Seller;
import com.springboot.ecommerceApplication.domain.user.User;
import com.springboot.ecommerceApplication.dto.CategoryMetaDataFieldValueDto;
import com.springboot.ecommerceApplication.exception.InvalidDetails;
import com.springboot.ecommerceApplication.repositories.CategoryMetaDataFieldValueRepo;
import com.springboot.ecommerceApplication.repositories.CategoryMetadataFieldRepo;
import com.springboot.ecommerceApplication.repositories.CategoryRepo;
import com.springboot.ecommerceApplication.repositories.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class CategoryMetaDataFieldValueService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    CategoryMetadataFieldRepo categoryMetadataFieldRepo;
    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    MessageSource messageSource;
    @Autowired
    CategoryMetaDataFieldValueRepo categoryMetaDataFieldValueRepo;

    //......................TO ADD METADATA VALUE.........................................................
    public ResponseEntity<String> addMetadataValue(String username, CategoryMetaDataFieldValueDto categoryMetaDataFieldValueDto) {
        User user = userRepo.findByEmail(username);
        if (!categoryRepo.findById(categoryMetaDataFieldValueDto.getCategoryId()).isPresent()) {
            throw new InvalidDetails("Category with the given category Id does not exist");
        }
        if (!categoryMetadataFieldRepo.findById(categoryMetaDataFieldValueDto.getMetadataId()).isPresent()) {
            throw new InvalidDetails("Category Metadata field with the given metadata Id does not exist");
        }
        ResponseEntity<String> responseEntity;
      //  String myValue = categoryMetaDataFieldValueDto.getValue();
        Category category=categoryRepo.findById(categoryMetaDataFieldValueDto.getCategoryId()).get();
        CategoryMetaDataField categoryMetaDataField= categoryMetadataFieldRepo.findById(categoryMetaDataFieldValueDto.getMetadataId()).get();
        CategoryMetaDataFieldValues categoryMetaDataFieldValues = new CategoryMetaDataFieldValues(category,
                categoryMetaDataField,categoryMetaDataFieldValueDto.getValue());

      categoryMetaDataFieldValueRepo.save(categoryMetaDataFieldValues);
        responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage
                ("message-metadata-value-added", null, LocaleContextHolder.getLocale()));
        return responseEntity;
    }

    //...................................TO UPDATE METADATA VALUE..................................................
    public ResponseEntity<String> updateMetadataValue(String username, Integer metadataValueId, CategoryMetaDataFieldValueDto categoryMetaDataFieldValueDto) {
        User user = userRepo.findByEmail(username);
        if (!categoryRepo.findById(categoryMetaDataFieldValueDto.getCategoryId()).isPresent()) {
            throw new InvalidDetails("Category with the given category Id does not exist");
        }
        if (!categoryMetadataFieldRepo.findById(categoryMetaDataFieldValueDto.getMetadataId()).isPresent()) {
            throw new InvalidDetails("Category Metadata field with the given metadata Id does not exist");
        }if (!categoryMetaDataFieldValueRepo.findById(metadataValueId).isPresent()) {
            throw new InvalidDetails("Category Metadata field value with the given metadata value Id does not exist");
        }

        ResponseEntity<String> responseEntity;
        CategoryMetaDataFieldValues categoryMetaDataFieldValues = categoryMetaDataFieldValueRepo.findById(metadataValueId).get();
        categoryMetaDataFieldValueDto.setId(metadataValueId);
        BeanUtils.copyProperties(categoryMetaDataFieldValueDto, categoryMetaDataFieldValues);

        categoryMetaDataFieldValueRepo.save(categoryMetaDataFieldValues);
        responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage
                ("message-metadata-value-updated",  null, LocaleContextHolder.getLocale()));
        return responseEntity;


    }
}

package com.springboot.ecommerce.services;


import com.springboot.ecommerce.domain.product.CategoryMetaDataField;
import com.springboot.ecommerce.domain.user.User;
import com.springboot.ecommerce.dto.CategoryMetadataFieldDto;
import com.springboot.ecommerce.exception.InvalidDetails;
import com.springboot.ecommerce.repositories.CategoryMetadataFieldRepo;
import com.springboot.ecommerce.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryMetadataFieldService {
   @Autowired
   UserRepo userRepo;
   @Autowired
   CategoryMetadataFieldRepo categoryMetadataFieldRepo;
   @Autowired
   MessageSource messageSource;
    //..............TO ADD METADATA FIELD BY ADMIN...........................WORKING
    public ResponseEntity<String> addMetaDataFieldByAdmin(String username, CategoryMetadataFieldDto categoryMetadataFieldDto) {
        User user = userRepo.findByEmail(username);
        if (categoryMetadataFieldRepo.findByName(categoryMetadataFieldDto.getName()) != null) {
            throw new InvalidDetails("This Category MetaData Field Already Exists");
        }
        ResponseEntity<String> responseEntity;

        CategoryMetaDataField categoryMetaDataField = new CategoryMetaDataField(categoryMetadataFieldDto.getName());
        categoryMetadataFieldRepo.save(categoryMetaDataField);
        responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage
                ("message-category-metadata-field-added", null, LocaleContextHolder.getLocale()));
        return responseEntity;

    }

    //.........................TO GET LIST OF METADATA BY ADMIN..............................WORKING
    public List<CategoryMetadataFieldDto> getAllMetaData(String username) {
        User user = userRepo.findByEmail(username);
        Iterable<CategoryMetaDataField> metaDataList = categoryMetadataFieldRepo.findAll();
        List<CategoryMetadataFieldDto> categoryMetadataFieldDtoList = new ArrayList<>();
        metaDataList.forEach(metadata -> categoryMetadataFieldDtoList.add(new CategoryMetadataFieldDto(metadata.getId(), metadata.getName())));
        return categoryMetadataFieldDtoList;
    }
}

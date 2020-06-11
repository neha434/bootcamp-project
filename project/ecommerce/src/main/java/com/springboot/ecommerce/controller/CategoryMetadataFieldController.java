package com.springboot.ecommerce.controller;

import com.springboot.ecommerce.dto.CategoryMetadataFieldDto;
import com.springboot.ecommerce.services.CategoryMetadataFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/MetadataField")
public class CategoryMetadataFieldController {
    @Autowired
    CategoryMetadataFieldService categoryMetadataFieldService;

   // ........TO ADD A CATEGORY  METADATA FIELD BY ADMIN.....................
    @PostMapping("/add")
    public ResponseEntity<String> addMetaDatField(HttpServletRequest httpServletRequest, @Valid @RequestBody CategoryMetadataFieldDto categoryMetadataFieldDto){
        Principal principal = httpServletRequest.getUserPrincipal();
        String username = principal.getName();
        return categoryMetadataFieldService.addMetaDataFieldByAdmin(username, categoryMetadataFieldDto);

    }
    //.................TO LIST CATEGORY METADATA BY ADMIN.......................
    @GetMapping("/list")
    public List<CategoryMetadataFieldDto> getAllMetaDataField(HttpServletRequest httpServletRequest)
    {    Principal principal=httpServletRequest.getUserPrincipal();
        String username=principal.getName();
        return categoryMetadataFieldService.getAllMetaData(username);
    }


}

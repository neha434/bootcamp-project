package com.springboot.ecommerce.controller;

import com.springboot.ecommerce.dto.CategoryMetaDataFieldValueDto;
import com.springboot.ecommerce.services.CategoryMetaDataFieldValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/metadataFieldValues")
public class CategoryMetaDataFieldValueController {
    @Autowired
    CategoryMetaDataFieldValueService categoryMetaDataFieldValueService;

    //...........TO ADD METADATA FIELD VALUES BY ADMIN...........
    @PostMapping("/add")
    public ResponseEntity<String> addMetadataValue(HttpServletRequest httpServletRequest,

                                                   @Valid @RequestBody CategoryMetaDataFieldValueDto categoryMetaDataFieldValueDto) {
        Principal principal = httpServletRequest.getUserPrincipal();
        String username = principal.getName();
        return categoryMetaDataFieldValueService.addMetadataValue(username, categoryMetaDataFieldValueDto);

    }
    //...............................TO UPDATE METADATA FILED VALUES BY ADMIN................................
    @PutMapping("/update/{metadataValueId}")
    public ResponseEntity<String> updateMetadataValue(@PathVariable Integer metadataValueId,
                                                       @Valid @RequestBody CategoryMetaDataFieldValueDto categoryMetaDataFieldValueDto,
                                                         HttpServletRequest httpServletRequest) {
        Principal principal = httpServletRequest.getUserPrincipal();
        String username = principal.getName();
        return categoryMetaDataFieldValueService.updateMetadataValue(username,metadataValueId, categoryMetaDataFieldValueDto);
    }




}

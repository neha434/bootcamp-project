package com.springboot.ecommerceApplication.services;

import com.springboot.ecommerceApplication.domain.product.Product;
import com.springboot.ecommerceApplication.domain.product.ProductVariation;
import com.springboot.ecommerceApplication.domain.user.Seller;
import com.springboot.ecommerceApplication.dto.ProductVariationDto;
import com.springboot.ecommerceApplication.exception.InvalidDetails;
import com.springboot.ecommerceApplication.repositories.CategoryMetadataFieldRepo;
import com.springboot.ecommerceApplication.repositories.ProductRepo;
import com.springboot.ecommerceApplication.repositories.ProductVariationRepo;
import com.springboot.ecommerceApplication.repositories.SellerRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductVariationService {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    ProductVariationRepo productVariationRepo;
    @Autowired
    SellerRepo sellerRepository;

    @Autowired
    MessageSource messageSource;
    @Autowired
    CategoryMetadataFieldRepo categoryMetadataFieldRepo

    //................TO ADD A PRODUCT BY SELLER...........working
    public ResponseEntity<String> addProductVariation(String username, Integer productId, ProductVariationDto productVariationDto) {
        Seller seller = sellerRepository.findByEmail(username);
        if (!productRepo.findById(productId).isPresent()) {
            throw new InvalidDetails("No such product with the given product Id exist");
        }
        Product product = productRepo.findById(productId).get();
        ResponseEntity<String> responseEntity;
        if(!product.isActive())
        {
             throw new  InvalidDetails("Product is not active");
        }
        if(product.isDeleted())
        {
            throw new InvalidDetails("Product is deleted");
        }
        ProductVariation productVariation = new ProductVariation(productVariationDto.getPrice(),productVariationDto.getQuantityAvailable());
        productVariation.setActive(true);

        productVariationRepo.save(productVariation);
        responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage
                ("message-productVariation-added", null, LocaleContextHolder.getLocale()));

        return responseEntity;
    }

    //...........................TO UPDATE PRODUCT VARIATION......................WORKING
    public ResponseEntity<String> updateProductVariationBySeller( String username, Integer productVariationId, ProductVariationDto productVariationDto) {
        Seller seller = sellerRepository.findByEmail(username);
        if(!productVariationRepo.findById(productVariationId).isPresent()){
            throw new InvalidDetails("No product with the given productVariationId exists ");
        }
//        if(productRepository.findById(productId).get().getSeller().getEmail()!=username){
//            throw new InvalidDetails("Product cannot be updated as Logged-In seller is not a creator of product.");
//        }  //validation
        //else working
        ResponseEntity<String> responseEntity;
        ProductVariation productVariation = productVariationRepo.findById(productVariationId).get();
        productVariationDto.setId(productVariationId);
        BeanUtils.copyProperties(productVariationDto, productVariation);

        productVariationRepo.save(productVariation);
        responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage
                ("message-productVariation-updated",  null, LocaleContextHolder.getLocale()));
        return responseEntity;
    }


    //..........TO GET PRODUCT VARIATION BY SELLER...........................
    public ProductVariationDto getProductVariationBySeller(Integer productVariationId, String username) {
        Seller seller = sellerRepository.findByEmail(username);
        if (!productVariationRepo.findById(productVariationId).isPresent()) {
            throw new InvalidDetails("Details entered are not valid");
        }
//        if(productVariationRepo.findById(productVariationId).get()getEmail()!=username){
//          throw new InvalidDetails("Product cannot be updated as Logged-In seller is not a creator of product.");
//     }
        //to get parent product details???????
        ProductVariation productVariation = productVariationRepo.findById(productVariationId).get();
        ProductVariationDto productVariationDto = new ProductVariationDto();
        productVariationDto.setId(productVariation.getId());
        productVariationDto.setPrice(productVariation.getPrice());
        productVariationDto.setQuantityAvailable(productVariation.getQuantityAvailable());

        return productVariationDto;
    }

    //........................TO GET PRODUCT VARIATION BY SELLER................................
    public List<ProductVariationDto> getProductVariationListBySeller(String username, Integer productId) {
        Seller seller = sellerRepository.findByEmail(username);
        if (!productRepo.findById(productId).isPresent()) {
            throw new InvalidDetails("Details entered are not valid");
        }
        //if(productVariationRepo.findById(productVariationId).get()getEmail()!=username){
//          throw new InvalidDetails("Product cannot be updated as Logged-In seller is not a creator of product.");
//     }
        Product product = productRepo.findById(productId).get();
        if(product.isDeleted())
        {
            throw new InvalidDetails("Product is deleted");
        }

        Iterable<ProductVariation> productVariationList = productVariationRepo.findAll();
        List<ProductVariationDto> productVariationDtos = new ArrayList<>();
        productVariationList.forEach(productVariation -> productVariationDtos.add(
                new ProductVariationDto(productVariation.getId(), productVariation.getPrice(),
                        productVariation.getQuantityAvailable())));
        return productVariationDtos;

    }

}


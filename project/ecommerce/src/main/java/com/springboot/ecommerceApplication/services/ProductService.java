package com.springboot.ecommerceApplication.services;


import com.springboot.ecommerceApplication.co.ProductCO;

import com.springboot.ecommerceApplication.domain.product.Product;


import com.springboot.ecommerceApplication.dto.ProductDto;

import com.springboot.ecommerceApplication.repositories.ProductRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class ProductService {
    @Autowired
    ProductRepo productRepository;


    public ProductDto getProduct(int id) {

        List<Product> optional = productRepository.findByName("name");
        Product product = new Product();
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setBrand(product.getBrand());
        return productDto;
    }

    public List<ProductDto> getProductList() {
        Iterable<Product> productList = productRepository.findAll();
        List<ProductDto> productDtoList = new ArrayList<>();
        productList.forEach(products -> productDtoList.add(new ProductDto(products.getId(), products.getName(),
                products.getDescription(), products.getBrand())));
        return productDtoList;
    }

    public ProductDto updateProduct(Integer id, ProductCO productCO) {
        Product product = productRepository.findById(id).get();
        BeanUtils.copyProperties(productCO, product);
       // product.getProductVariationList().get(1);
        productRepository.save(product);
        ProductDto productDto = getProduct(product.getId());
        return productDto;
    }

    public Map<String, Boolean> deleteProduct(Integer id) {
        Map<String, Boolean> map = new HashMap<>();
        Optional<Product> optional = productRepository.findById(id);
        if (!optional.isPresent()) {
            map.put("Deleted", false);
        } else {
            productRepository.deleteById(id);
            map.put("Deleted", true);
        }
        return map;
    }

    public ProductDto addProduct(ProductCO productCO){
        Product newProduct = new Product();

        newProduct.setName(productCO.getName());
        newProduct.setDescription(productCO.getDescription());
        newProduct.setBrand(productCO.getBrand());
        productRepository.save(newProduct);
        ProductDto productDto = getProduct(newProduct.getId());
        return productDto;
    }


}

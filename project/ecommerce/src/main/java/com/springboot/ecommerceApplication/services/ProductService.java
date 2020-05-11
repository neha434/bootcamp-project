package com.springboot.ecommerceApplication.services;

import com.springboot.ecommerceApplication.domain.product.Category;
import com.springboot.ecommerceApplication.domain.product.Product;
import com.springboot.ecommerceApplication.domain.product.ProductVariation;
import com.springboot.ecommerceApplication.domain.user.Seller;
import com.springboot.ecommerceApplication.domain.user.User;
import com.springboot.ecommerceApplication.dto.ProductDto;
import com.springboot.ecommerceApplication.dto.ProductVariationDto;
import com.springboot.ecommerceApplication.exception.InvalidDetails;
import com.springboot.ecommerceApplication.repositories.CategoryRepo;
import com.springboot.ecommerceApplication.repositories.ProductRepo;
import com.springboot.ecommerceApplication.repositories.SellerRepo;
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
public class ProductService {
    @Autowired
    ProductRepo productRepository;
    @Autowired
    SellerRepo sellerRepository;
    @Autowired
    MessageSource messageSource;
    @Autowired
    MailService mailService;
    @Autowired
    UserRepo userRepository;
    @Autowired
    CategoryRepo categoryRepository;


    //.................TO ADD A PRODUCT BY SELLER.....................//working
    public ResponseEntity<String> addProduct(String username, ProductDto productDto) {
        Seller seller = sellerRepository.findByEmail(username);
        if (productRepository.findByName(productDto.getName()) != null) {
            throw new InvalidDetails("Product Already Exists");
        }
        if (!categoryRepository.findById(productDto.getCategoryId()).isPresent()){
            throw new InvalidDetails("Category Does Not Exists In Database");
        }
        Category category = categoryRepository.findById(productDto.getCategoryId()).get();
        Seller sellerrId= sellerRepository.findById(productDto.getSellerId()).get();
        ResponseEntity<String> responseEntity;
        Product product = new Product(category,sellerrId,productDto.getName(), productDto.getBrand(),
                productDto.getDescription(), true, productDto.isCancellable(), productDto.isReturnable(),productDto.isDeleted());
        productRepository.save(product);
        // mailService.sendAddedProductDetailsEmail(product,username);
        responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage
                ("message-product-added", null, LocaleContextHolder.getLocale()));
        return responseEntity;
        //to change product status
        //email to admin

    }



    //.....................TO VIEW A PRODUCT BY SELLER................................working
    public ProductDto getProduct(Integer productId, String username) {
        Seller seller = sellerRepository.findByEmail(username);
          if(!productRepository.findById(productId).get().getSeller().getEmail().equals(username)){
            throw new InvalidDetails("Product cannot be viewed as Logged-In seller is not a creator of product.");
        }
        if (!productRepository.findById(productId).isPresent()) {
            throw new InvalidDetails("Details entered are not valid");
        }
        Product product = productRepository.findById(productId).get();
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setBrand(product.getBrand());
        productDto.setActive(product.isActive());
        productDto.setSellerId(product.getSeller().getId());
        productDto.setCategoryId(product.getProductCategory().getId());
        productDto.setReturnable(product.isReturnable());
        productDto.setCancellable(product.isCancellable());
        return productDto;
    }


    //..................TO GET LIST OF PRODUCTS BY SELLER.....................
    public List<ProductDto> getProductListBySeller(String username) {

        Seller seller = sellerRepository.findByEmail(username);
        List<Product> productList = seller.getProductList();
        List<ProductDto> productDtoList = new ArrayList<>();
        productList.forEach(product -> productDtoList.add(new ProductDto(product.getId(), product.getName(),
                product.getDescription(),
                product.isCancellable(), product.isReturnable(), product.getBrand(), product.isActive())));
        return productDtoList;
    }



    //................TO UPDATE A PRODUCT BY SELLER........................//working
    public ResponseEntity<String> updateProductBySeller( String username, Integer productId, ProductDto productDto) {
        Seller seller = sellerRepository.findByEmail(username);
        if(!productRepository.findById(productId).isPresent()){
            throw new InvalidDetails("No product with the given productId exists ");
        }
        if(!productRepository.findById(productId).get().getSeller().getEmail().equals(username)){
            throw new InvalidDetails("Product cannot be updated as Logged-In seller is not a creator of product.");
        }  //validation
        //else working
        ResponseEntity<String> responseEntity;
        Product product = productRepository.findById(productId).get();
        BeanUtils.copyProperties(productDto, product);
       //product.getProductVariationList().get(1);
        productRepository.save(product);
        responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage
                ("message-product-updated", null, LocaleContextHolder.getLocale()));
        return responseEntity;
    }

    //..............TO DELETE A PRODUCT BY SELLER.............................//working
    public ResponseEntity<String> deleteProductBySeller(Integer productId, String username){
        Seller seller = sellerRepository.findByEmail(username);
        if(!productRepository.findById(productId).isPresent()){
            throw new InvalidDetails("Product does not exists");
        }
        if(!productRepository.findById(productId).get().getSeller().getEmail().equals(username)){
            throw new InvalidDetails("Product cannot be deleted as Logged-In seller is not a creator of product.");
        }
        ResponseEntity<String> responseEntity;
        productRepository.deleteById(productId);
        responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage
                ("message-product-deleted", null, LocaleContextHolder.getLocale()));
        return responseEntity;
    }






//    public ProductDto getProduct(int id) {
//
//       List<Product> optional = productRepository.findByName("name");
//        Product product = new Product();
//        ProductDto productDto = new ProductDto();
//        productDto.setId(product.getId());
//        productDto.setName(product.getName());
//        productDto.setDescription(product.getDescription());
//        productDto.setBrand(product.getBrand());
//        return productDto;
//    }



    //.........TO VIEW PRODUCT LIST BY ADMIN..................
    public List<ProductDto> getProductList(String username) {
        User user = userRepository.findByEmail(username);
        Iterable<Product> productList = productRepository.findAll();
        List<ProductDto> productDtoList = new ArrayList<>();
        productList.forEach(products -> productDtoList.add(new ProductDto(products.getId(), products.getName(),
                products.getDescription(), products.getBrand())));
        return productDtoList;
    }


    public ProductDto getProductByAdmin(String username, Integer productId) {
        User user = userRepository.findByEmail(username);
        Optional<Product> optional = productRepository.findById(productId);
        if (!optional.isPresent()) {
            throw new InvalidDetails("This product does not exist");
        }
        Product product = productRepository.findById(productId).get();
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setBrand(product.getBrand());
        productDto.setActive(product.isActive());
        productDto.setDeleted(product.isDeleted());
        return productDto;
    }
}

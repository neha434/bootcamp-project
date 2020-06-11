package com.springboot.ecommerce.services;

import com.springboot.ecommerce.domain.product.Category;
import com.springboot.ecommerce.domain.product.Product;
import com.springboot.ecommerce.domain.product.ProductVariation;
import com.springboot.ecommerce.domain.user.Seller;
import com.springboot.ecommerce.domain.user.User;
import com.springboot.ecommerce.dto.ProductDto;
import com.springboot.ecommerce.dto.ProductVariationDto;
import com.springboot.ecommerce.exception.InvalidDetails;
import com.springboot.ecommerce.repositories.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;


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
    @Autowired
    ProductVariationRepo productVariationRepo;
    //public Date date;


    //.................TO ADD A PRODUCT BY SELLER.....................//working
    public ResponseEntity<String> addProduct(String username, ProductDto productDto) {

        Seller seller = sellerRepository.findByEmail(username);
        if (productRepository.findByName(productDto.getName()) != null) {
            throw new InvalidDetails("Product Already Exists");
        }
        if (!categoryRepository.findById(productDto.getCategoryId()).isPresent()) {
            throw new InvalidDetails("Category Does Not Exists In Database");
        }
        Category category = categoryRepository.findById(productDto.getCategoryId()).get();
        Seller sellerrId = sellerRepository.findById(productDto.getSellerId()).get();
        ResponseEntity<String> responseEntity;
        if (!category.getSubCategoryList().isEmpty()) {
            throw new InvalidDetails(" Category ID passed is not valid leaf node category");
        }
        Product product = new Product(category, sellerrId, productDto.getName(), productDto.getBrand(),
                productDto.getDescription(), false, productDto.isCancellable(), productDto.isReturnable(), productDto.isDeleted());
        productRepository.save(product);

        mailService.sendAddedProductDetailsEmail(product);
        responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage
                ("message-product-added", null, LocaleContextHolder.getLocale()));
        return responseEntity;

    }


    //.....................TO VIEW A PRODUCT BY SELLER................................working
    // @Cacheable(value = "getProductCache", key = "#root.methodName")
    public ProductDto getProduct(Integer productId, String username) {
        Seller seller = sellerRepository.findByEmail(username);
        if (!productRepository.findById(productId).get().getSeller().getEmail().equals(username)) {
            throw new InvalidDetails("Product cannot be viewed as Logged-In seller is not a creator of product.");
        }
        if (!productRepository.findById(productId).isPresent()) {
            throw new InvalidDetails("Details entered are not valid");
        }
        Product product = productRepository.findById(productId).get();
        ProductDto productDto = new ProductDto();
        productDto.setCategory(product.getProductCategory());
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


    //..................TO GET LIST OF PRODUCTS BY SELLER.....................//working
    // @Cacheable(value = "productCacheSeller", key = "#root.methodName")
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
    public ResponseEntity<String> updateProductBySeller(String username, Integer productId, ProductDto productDto) {
        Seller seller = sellerRepository.findByEmail(username);
        if (!productRepository.findById(productId).isPresent()) {
            throw new InvalidDetails("No product with the given productId exists ");
        }
        if (!productRepository.findById(productId).get().getSeller().getEmail().equals(username)) {
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
    public ResponseEntity<String> deleteProductBySeller(Integer productId, String username) {
        Seller seller = sellerRepository.findByEmail(username);
        if (!productRepository.findById(productId).isPresent()) {
            throw new InvalidDetails("Product does not exists");
        }
        if (!productRepository.findById(productId).get().getSeller().getEmail().equals(username)) {
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
    @Cacheable(value = "productCache", key = "#root.methodName")
    public List<ProductDto> getProductList(String username) {
        User user = userRepository.findByEmail(username);
        Iterable<Product> productList = productRepository.findAll();
        List<ProductDto> productDtoList = new ArrayList<>();
        productList.forEach(products -> productDtoList.add(new ProductDto(products.getId(), products.getName(),
                products.getDescription(), products.getBrand())));
        return productDtoList;
    }

    //............TO GET PRODUCT BY ADMIN......................
    @Cacheable(value = "productCacheAdmin", key = "#root.methodName")
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


    //............TO VIEW A PRODUCT BY CUSTOMER....................
    public ProductDto getProductByCustomer(String username, Integer productId) {
        User user = userRepository.findByEmail(username);

        if (!productRepository.findById(productId).isPresent()) {
            throw new InvalidDetails("Product does not exists");
        }
        Product valid = new Product();
        if (!valid.isActive() && valid.isDeleted()) {
            throw new InvalidDetails("Product is not available anymore");
        }

        Product product = productRepository.findById(productId).get();
        List<ProductVariation> productVariationList = product.getProductVariationList();
        List<ProductVariationDto> ProductVariationDtoList = new ArrayList<>();
        productVariationList.forEach(productVariation -> ProductVariationDtoList.add(new
                ProductVariationDto(productVariation.getId(), productVariation.getPrice(),
                productVariation.getQuantityAvailable())));
        ProductDto customerProductViewDto = new ProductDto(product.getId(),
                product.getName(), product.getDescription(), product.getBrand(),
                product.isCancellable(), product.isReturnable(), ProductVariationDtoList);
        return customerProductViewDto;
    }

    //....................TO VIEW PRODUCT LIST BY CUSTOMER...........................
    public List<ProductDto> getProductListByCustomer(String username, Integer categoryId) {
        User user = userRepository.findByEmail(username);
        if (!categoryRepository.findById(categoryId).isPresent()) {
            throw new InvalidDetails("You Have Entered A Wrong Category id,Category Not Present");
        }
        // Pageable paging = PageRequest.of(0, 3, Sort.by("id").ascending());
        Category category = categoryRepository.findById(categoryId).get();
        List<Product> productsList = category.getProductList();
        Iterator<Product> productIterator = productsList.iterator();
        List<ProductDto> customerProductViewDtoList = new ArrayList<>();
        while (productIterator.hasNext()) {
            Product product = productIterator.next();
            //   List<ProductVariation> productVariationList = productVariationRepo.findAll(paging);
            List<ProductVariation> productVariationList = product.getProductVariationList();
            Iterator<ProductVariation> productVariationIterator = productVariationList.iterator();
            List<ProductVariationDto> productVariationDtoList = new ArrayList<>();
            while (productVariationIterator.hasNext()) {
                ProductVariation productVariation = productVariationIterator.next();
                ProductVariationDto customerProductVariationViewDto = new ProductVariationDto(
                        productVariation.getId(), productVariation.getPrice(), productVariation.getQuantityAvailable());
                productVariationDtoList.add(customerProductVariationViewDto);
            }
            customerProductViewDtoList.add(new ProductDto(product.getId(), product.getName(),
                    product.getProductCategory().getId(), product.getProductCategory().getName(), product.getDescription(),
                    product.isCancellable(), product.getBrand(), product.isReturnable(), productVariationDtoList));
        }
        return customerProductViewDtoList;
    }


    public List<ProductDto> listProduct(Integer id) {
        Optional<Seller> seller = sellerRepository.findById(id);
        List<ProductDto> productDtoList = null;
        if (seller.isPresent()) {
            String username = seller.get().getEmail();
            Seller productSeller = sellerRepository.findByEmail(username);
            List<Product> productList = productSeller.getProductList();
            productDtoList = new ArrayList<>();
            for (Product product : productList) {
                productDtoList.add(new ProductDto(product.getId(), product.getName(),
                        product.getDescription(),
                        product.isCancellable(), product.isReturnable(), product.getBrand(), product.isActive()));
            }
        }
        return productDtoList;
    }
}

package com.springboot.ecommerceApplication.controller;

import com.springboot.ecommerceApplication.domain.product.Product;
import com.springboot.ecommerceApplication.domain.product.ProductVariation;
import com.springboot.ecommerceApplication.domain.user.User;
import com.springboot.ecommerceApplication.repositories.ProductRepo;
import com.springboot.ecommerceApplication.repositories.ProductVariationRepo;
import com.springboot.ecommerceApplication.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
//@RequestMapping( consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public class ImageController {
    @Autowired
    UserRepo userRepository;
    @Autowired
    ProductRepo productRepository;
    @Autowired
    ProductVariationRepo productVariationRepository;
    //upload image
    @PostMapping(value = "/upload/user")
    public ResponseEntity<Object>uploadUserToLocalFileSystem(@RequestParam("file") MultipartFile file,@RequestParam("id") Integer id) throws IOException {
    File image = new File("/home/neha-rai/Downloads/project/ecommerce/user/" + file.getOriginalFilename());
    image.createNewFile();
    FileOutputStream fout = new FileOutputStream(image);
    fout.write(file.getBytes());
    fout.close();
    User user = userRepository.findById(id).get();
    user.setImage(file.getOriginalFilename());
        userRepository.save(user);
      return new ResponseEntity<>("User Image is uploaded successfully", HttpStatus.OK);
}
    @PostMapping(value = "/upload/product")
    public ResponseEntity<Object>uploadProductToLocalFileSystem(@RequestParam("file") MultipartFile file,@RequestParam("id") Integer id) throws IOException {
        File image = new File("/home/neha-rai/Downloads/project/ecommerce/product/" + file.getOriginalFilename());
        image.createNewFile();
        FileOutputStream fout = new FileOutputStream(image);
        fout.write(file.getBytes());
        fout.close();
        Product product = productRepository.findById(id).get();
        product.setImage(file.getOriginalFilename());
        productRepository.save(product);
        return new ResponseEntity<>("Product Image is uploaded successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/upload/product-variation")
    public ResponseEntity<Object>uploadProductVariationToLocalFileSystem(@RequestParam("file") MultipartFile file,@RequestParam("id") Integer id) throws IOException {
        File image = new File("/home/neha-rai/Downloads/project/ecommerce/product-variation/" + file.getOriginalFilename());
        image.createNewFile();
        FileOutputStream fout = new FileOutputStream(image);
        fout.write(file.getBytes());
        fout.close();
        ProductVariation productVariation = productVariationRepository.findById(id).get();
        productVariation.setImage(file.getOriginalFilename());
        productVariationRepository.save(productVariation);
        return new ResponseEntity<>("Product variation Image is uploaded successfully", HttpStatus.OK);
    }
//display image
    @GetMapping(value = "/show/user")
    public ResponseEntity<Object> showUserImage(@RequestParam("file") Integer id) {
        String fileName = userRepository.findById(id).get().getImage();
        Path path = Paths.get( "//home/neha-rai/Downloads/project/ecommerce/user/"+ fileName);
        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;  filename=\"" +resource.getFilename() +"\"")
                .body(resource);
    }
    @GetMapping(value = "/show/product")
    public ResponseEntity<Object> showProductImage(@RequestParam("file") Integer id) {
        String fileName = productRepository.findById(id).get().getImage();
        Path path = Paths.get( "/home/neha-rai/Downloads/project/ecommerce/product/"+ fileName);
        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;  filename=\"" +resource.getFilename() +"\"")
                .body(resource);
    }
    @GetMapping(value = "/show/product-variation")
    public ResponseEntity<Object> showProductVariationImage(@RequestParam("file") Integer id) {
        String fileName = productVariationRepository.findById(id).get().getImage();
        Path path = Paths.get( "/home/neha-rai/Downloads/project/ecommerce/product-variation/"+ fileName);
        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;  filename=\"" +resource.getFilename() +"\"")
                .body(resource);
    }


 }

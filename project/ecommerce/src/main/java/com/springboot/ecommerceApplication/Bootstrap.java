package com.springboot.ecommerceApplication;

import com.springboot.ecommerceApplication.domain.Role;
import com.springboot.ecommerceApplication.domain.order.Cart;
import com.springboot.ecommerceApplication.domain.order.Order;
import com.springboot.ecommerceApplication.domain.product.Category;
import com.springboot.ecommerceApplication.domain.product.Product;
import com.springboot.ecommerceApplication.domain.product.ProductReview;
import com.springboot.ecommerceApplication.domain.product.ProductVariation;
import com.springboot.ecommerceApplication.domain.user.Address;
import com.springboot.ecommerceApplication.domain.user.Customer;
import com.springboot.ecommerceApplication.domain.user.Seller;
import com.springboot.ecommerceApplication.domain.user.User;
import com.springboot.ecommerceApplication.dto.AddressDto;
import com.springboot.ecommerceApplication.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Bootstrap implements ApplicationRunner {
@Autowired
AddressRepository addressRepository;
    @Autowired
    RoleRepo roleRepository;

    @Autowired
    UserRepo userRepository;

    @Autowired
    CustomerRepo customerRepository;

    @Autowired
    SellerRepo sellerRepository;

    @Autowired
    CategoryRepo categoryRepository;

    @Autowired
    ProductRepo productRepository;

    @Autowired
    CartRepo cartRepository;

    @Autowired
    OrderRepo orderRepository;

    @Autowired
    ProductVariationRepo productVariationRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(userRepository.count()<1){
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            Role roleAdmin = new Role(1,"ROLE_ADMIN");
            roleRepository.save(roleAdmin);
            Role roleSeller = new Role(2,"ROLE_SELLER");
            roleRepository.save(roleSeller);
            Role roleCustomer = new Role(3,"ROLE_CUSTOMER");
            roleRepository.save(roleCustomer);

            User appUser = new User();
            appUser.setEmail("neha.rai@tothenew.com");
            appUser.setFirstName("Neha");
            appUser.setMiddleName("");
            appUser.setLastName("Rai");
            appUser.setPassword(passwordEncoder.encode("neharaiA1!"));
            appUser.setActive(true);
            appUser.setDeleted(false);
           // appUser.setEnabled(true);

            List<Role> roleList = new ArrayList<>();
            roleList.add(roleAdmin);
            appUser.setRoleList(roleList);

            Seller seller = new Seller();
            seller.setEmail("eller.user@gmail.com");
            seller.setFirstName("Pragati");
            seller.setLastName("Singhal");
            seller.setPassword(passwordEncoder.encode("nehaA123!"));
            seller.setActive(true);
            seller.setDeleted(false);
            seller.setCompanyContact("Company Contact");
            seller.setCompanyName("Company Name");
            seller.setGst("22AAAAA00001Z5");

            List<Role> sellerRoleList = new ArrayList<>();
            sellerRoleList.add(roleSeller);
            seller.setRoleList(sellerRoleList);

            Customer customer = new Customer();
            customer.setEmail("neha.rai8209@gmail.com");
            customer.setFirstName("Neha");
            customer.setMiddleName("");
            customer.setLastName("Rai");
            customer.setPassword(passwordEncoder.encode("nehaA123!"));
            customer.setActive(true);
            customer.setDeleted(false);
            customer.setContact("Customer Contact");
          // customer.setAccountNonLocked(true);
            //customer.setAccountNotExpired(true);
            //customer.setCredentialsNonExpired(true);
           // customer.setEnabled(true);


            List<Role> customerRoleList = new ArrayList<>();
            customerRoleList.add(roleCustomer);
            customer.setRoleList(customerRoleList);

            userRepository.save(appUser);
            sellerRepository.save(seller);
            customerRepository.save(customer);

            System.out.println("Total users saved::"+ userRepository.count());
        }

        if(addressRepository.count()<1){
         //   Customer customer = new Customer();
            User customer = userRepository.findById(3).get();
            Address address = new Address();
            address.setCountry("India");
            address.setState("U.P");
            address.setCity("XYZ");
            address.setAddressLine("abcdef");
            address.setZipCode("1234");
            address.setLabel("home");
            address.setUser(customer);
            addressRepository.save(address);
        }
        if(categoryRepository.count() < 1){
            Category category = new Category();
            category.setName("Laptops");
            categoryRepository.save(category);
        }
        if(productRepository.count() < 1){
            Product product = new Product();
            product.setName("Lenovo Ideapad");
            product.setDescription("Portable Design");

            product.setCategory(categoryRepository.findByName("Laptops"));
            product.setSeller( sellerRepository.findByEmail("eller.user@gmail.com"));

            product.setCancellable(true);
            product.setReturnable(true);
            product.setBrand("Lenovo");
            product.setActive(true);

            List<ProductVariation> productVariations = new ArrayList<>();
            ProductVariation productVariation1 = new ProductVariation(1,26990,"Image Name 1",
                    "Meta Data 1");
            productVariation1.setProduct(product);
            productVariations.add(productVariation1);
            ProductVariation productVariation2 = new ProductVariation(2, 18990,"Image Name 2",
                    "Meta Data 2");
            productVariation2.setProduct(product);
            productVariations.add(productVariation2);
            productVariation1.setActive(true);
            productVariation2.setActive(true);
            productVariation1.setQuantityAvailable(1);
            productVariation2.setQuantityAvailable(1);
            product.setProductVariationList(productVariations);

            productRepository.save(product);

            List<ProductReview> productReviewsList = new ArrayList<>();

            ProductReview productReview1 = new ProductReview();
            productReview1.setReview("Display is not good");
            productReview1.setRating(3.8);
            productReview1.setProduct(product);
            productReviewsList.add(productReview1);

            ProductReview productReview2 = new ProductReview();
            productReview2.setReview("Average laptop for daily task");
            productReview2.setRating(4.0);
            productReview2.setProduct(product);
            productReviewsList.add(productReview2);
            product.setProductReviewsList(productReviewsList);
           productRepository.save(product);
        }
        if (cartRepository.count() < 1){
            Cart cart = new Cart();
          //  cart.setProductVariation(productVariationRepository.findById(1).get());
         // cart.setCustomer(customerRepository.findById(3).get());
            cart.setWishList(true);
            cart.setQuantity(2);
            cartRepository.save(cart);
        }
        if (orderRepository .count() < 1){
            Order order = new Order();
            order.setAmountPaid(10000);

            Date date = new Date();
            order.setDate_created(date);
            order.setPaymentMethod("Net Banking");
            order.setCustomerAddressCity("Greater Noida");
            order.setCustomerAddressState("Uttar Pradsh");
            order.setCustomerAddressCountry("India");
            order.setCustomerAddressAddressLine("Kaushalya Residency");
            order.setCustomerAddressLabel("Home");
            order.setCustomerAddressZipCode(201310);
            order.setProductVariationId(1);
            order.setQuantity(1);
            orderRepository.save(order);
        }

    }
}

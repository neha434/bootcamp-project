package com.springboot.ecommerceApplication.services;

import com.springboot.ecommerceApplication.domain.order.Cart;
import com.springboot.ecommerceApplication.dto.CartDto;
import com.springboot.ecommerceApplication.dto.ProductDto;
import com.springboot.ecommerceApplication.repositories.CartRepo;
import com.springboot.ecommerceApplication.repositories.CustomerRepo;
import com.springboot.ecommerceApplication.repositories.ProductVariationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CartService {
    @Autowired
    CartRepo cartRepository;
    @Autowired
    CustomerRepo customerRepository;
    @Autowired
    ProductVariationRepo productVariationRepository;
    @Autowired
    ProductService productService;

    public ProductDto addProductToCart(CartDto cartDto){
        Cart cart = new Cart();
        cart.setCustomer(customerRepository.findById(cartDto.getCustomerId()).get());
        cart.setProductVariation(productVariationRepository.findById(cartDto.getProductVariationId()).get());
        cart.setQuantity(cartDto.getQuantity());
        cart.setWishList(cartDto.isWishList());
        cartRepository.save(cart);

        ProductDto productDto = productService.getProduct(productVariationRepository.
                findById(cartDto.getProductVariationId()).get().getProduct().getId());
        return productDto;
    }

    public List<CartDto> getCartProduct() {
        Iterable<Cart> cartList = cartRepository.findAll();
        List<CartDto> cartDtoList = new ArrayList<>();
        cartList.forEach(carts -> cartDtoList.add(new CartDto(carts.getId(), carts.getQuantity(),
                carts.WishList(), carts.getCustomer().getId(),carts.getProductVariation().getId())));
        return cartDtoList;
    }

}

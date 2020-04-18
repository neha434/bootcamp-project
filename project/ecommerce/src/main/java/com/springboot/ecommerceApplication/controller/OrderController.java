package com.springboot.ecommerceApplication.controller;

import com.springboot.ecommerceApplication.co.OrderCO;
import com.springboot.ecommerceApplication.dto.OrderDto;
import com.springboot.ecommerceApplication.dto.ProductDto;
import com.springboot.ecommerceApplication.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")

public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/{id}")
    public OrderDto getOrder(@PathVariable Integer id){
        return orderService.getOrder(id);
    }

    @GetMapping("/")
    public List<OrderDto> getAllOrder(){
        return orderService.getAllOrder();
    }

    @PostMapping("/")
     public OrderDto addOrder(@RequestBody OrderDto orderDto, WebRequest webRequest) {
        return orderService.addOrder(orderDto);
    }
    @PutMapping("/{id}")
    public OrderDto updateProduct (@PathVariable Integer id, @RequestBody OrderCO orderCO, WebRequest webRequest)
        {
            return orderService.updateOrder(id,orderCO);
        }
    @DeleteMapping("/{id}")
    public Map<String,Boolean> deleteProduct(@PathVariable Integer id){
        return orderService.deleteOrder(id);
    }

}
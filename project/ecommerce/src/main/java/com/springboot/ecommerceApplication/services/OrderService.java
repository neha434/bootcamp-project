package com.springboot.ecommerceApplication.services;

import com.springboot.ecommerceApplication.co.CustomerCO;
import com.springboot.ecommerceApplication.co.OrderCO;
import com.springboot.ecommerceApplication.co.ProductCO;
import com.springboot.ecommerceApplication.domain.order.Order;
import com.springboot.ecommerceApplication.domain.product.Product;
import com.springboot.ecommerceApplication.dto.OrderDto;
import com.springboot.ecommerceApplication.dto.ProductDto;
import com.springboot.ecommerceApplication.exception.NotFoundException;
import com.springboot.ecommerceApplication.repositories.CartRepo;
import com.springboot.ecommerceApplication.repositories.OrderRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {
@Autowired
OrderRepo orderRepository;
@Autowired
    CartRepo cartRepository;

public List<OrderDto> getAllOrder(){
        Iterable<Order> orderList= orderRepository.findAll();
        List<OrderDto> orderDtoList = new ArrayList<>();
        orderList.forEach(order-> orderDtoList.add(new OrderDto(order.getId(),order.getAmountPaid(),
                order.getDate_created(),order.getPaymentMethod(), order.getCustomerAddressAddressLine(),
                order.getCustomerAddressCity(), order.getCustomerAddressState(), order.getCustomerAddressCountry(),
                order.getCustomerAddressLabel(), order.getCustomerAddressZipCode())));

        return orderDtoList;
    }

    public OrderDto updateOrder(Integer id, OrderCO orderCO ){
    Order order = orderRepository.findById(id).get();
            BeanUtils.copyProperties(orderCO, order);
            orderRepository.save(order);
            OrderDto orderDto = getOrder(order.getId());
            return orderDto;
        }

    public Map<String, Boolean> deleteOrder(Integer id) {
        Map<String, Boolean> map = new HashMap<>();
        Optional<Order> optional = orderRepository.findById(id);
        if (!optional.isPresent()) {
            map.put("Deleted", false);
        } else {
            orderRepository.deleteById(id);
            map.put("Deleted", true);
        }
        return map;
    }
    public OrderDto addOrder(OrderDto orderDto){
     if(!cartRepository.findById(orderDto.getId()).isPresent()){
         throw new NotFoundException("No such Item Present");
     }
     Order newOrder = new Order(orderDto.getId(),orderDto.getAmountPaid(),orderDto.getDate_created(),orderDto.getPaymentMethod(),
             orderDto.getCustomerAddressAddressLine(), orderDto.getCustomerAddressCity(),orderDto.getCustomerAddressState(),orderDto.getCustomerAddressCountry(),
             orderDto.getCustomerAddressLabel(), orderDto.getCustomerAddressZipCode());
     orderRepository.save(newOrder);
     return orderDto;
    }
    public OrderDto getOrder(int id) {

        Optional<Order> optional = orderRepository.findById(id);
        Order order = new Order();
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setAmountPaid(order.getAmountPaid());
        orderDto.setDate_created(order.getDate_created());

        orderDto.setCustomerAddressAddressLine(order.getCustomerAddressAddressLine());
        orderDto.setCustomerAddressCity(order.getCustomerAddressCity());
        orderDto.setCustomerAddressCountry(order.getCustomerAddressCountry());
        orderDto.setCustomerAddressLabel(order.getCustomerAddressLabel());
        orderDto.setCustomerAddressZipCode(order.getCustomerAddressZipCode());
        orderDto.setCustomerAddressState(order.getCustomerAddressState());
        orderDto.setCustomerAddressZipCode(order.getCustomerAddressZipCode());
        return orderDto;
    }


}

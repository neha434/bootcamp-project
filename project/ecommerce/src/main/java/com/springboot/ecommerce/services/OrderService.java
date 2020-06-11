package com.springboot.ecommerce.services;

import com.springboot.ecommerce.co.OrderCO;
import com.springboot.ecommerce.domain.order.Order;
import com.springboot.ecommerce.domain.product.ProductVariation;
import com.springboot.ecommerce.dto.OrderDto;
import com.springboot.ecommerce.exception.NotFoundException;
import com.springboot.ecommerce.repositories.CartRepo;
import com.springboot.ecommerce.repositories.OrderRepo;
import com.springboot.ecommerce.repositories.ProductVariationRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderService {
@Autowired
OrderRepo orderRepository;
@Autowired
    CartRepo cartRepository;
@Autowired
    ProductVariationRepo productVariationRepo;



//public List<OrderDto> getAllOrder(){
//        Iterable<Order> orderList= orderRepository.findAll();
//        List<OrderDto> orderDtoList = new ArrayList<>();
//        orderList.forEach(order-> orderDtoList.add(new OrderDto(order.getId(),order.getAmountPaid(),
//                order.getDate_created(),order.getPaymentMethod(), order.getCustomerAddressAddressLine(),
//                order.getCustomerAddressCity(), order.getCustomerAddressState(), order.getCustomerAddressCountry(),
//                order.getCustomerAddressLabel(), order.getCustomerAddressZipCode())));
//
//        return orderDtoList;
//    }

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
    public OrderDto addOrder( OrderDto orderDto){
        ProductVariation savedProduct = productVariationRepo.findById(orderDto.getProductVariationId()).get();
       String productName = savedProduct.getProduct().getName();
        Integer quantity =  savedProduct.getQuantityAvailable() - orderDto.getQuantity();
        savedProduct.setQuantityAvailable(quantity);
        productVariationRepo.save(savedProduct);
     if(!cartRepository.findById(orderDto.getId()).isPresent()){
         throw new NotFoundException("No such Item Present");
     }
     Order newOrder = new Order(orderDto.getId(),orderDto.getAmountPaid(),orderDto.getDate_created(),orderDto.getPaymentMethod(),
             orderDto.getCustomerAddressAddressLine(), orderDto.getCustomerAddressCity(),orderDto.getCustomerAddressState(),orderDto.getCustomerAddressCountry(),
             orderDto.getCustomerAddressLabel(), orderDto.getCustomerAddressZipCode(),orderDto.getProductVariationId(),orderDto.getQuantity());
     orderRepository.save(newOrder);
       // OrderProduct  product = new OrderProduct(orderDto.getQuantity(), savedProduct.getPrice(),savedProduct,newOrder);;
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

package com.progectFood.service.impl;

import com.progectFood.controller.exception.ResourceNotFoundException;
import com.progectFood.domian.dto.DishDto;
import com.progectFood.domian.dto.OrderDto;
import com.progectFood.domian.entity.Dish;
import com.progectFood.domian.entity.Order;
import com.progectFood.domian.entity.StatusDelivery;
import com.progectFood.repository.OrderRepository;
import com.progectFood.repository.StatusDeliveryRepository;
import com.progectFood.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    @Autowired
    StatusDeliveryRepository statusDeliveryRepository;
    @Autowired
    OrderRepository orderRepository;
    private final ConversionService conversionService;

    @Override
    public List<OrderDto> getAllOrders() throws ResourceNotFoundException {
        StatusDelivery statusDelivery = statusDeliveryRepository.findById(4)
                .orElseThrow(() -> new ResourceNotFoundException("Status not found for this id = " + 4));
        ArrayList<OrderDto> orders = new ArrayList<>();
        for (Order order : orderRepository.getAllOrders(statusDelivery)) {
            OrderDto orderDto = conversionService.convert(order, OrderDto.class);
            orders.add(orderDto);
        }
        return orders;
    }

    @Override
    public List<OrderDto> getOrderById(Integer id) throws ResourceNotFoundException {
        StatusDelivery statusDelivery = statusDeliveryRepository.findById(4)
                .orElseThrow(() -> new ResourceNotFoundException("Status not found for this id = " + 4));
        List<OrderDto> orders = new ArrayList<>();
        for (Order order : orderRepository.getOrderById(id, statusDelivery)) {
            OrderDto orderDto = conversionService.convert(order, OrderDto.class);
            orders.add(orderDto);
        }
        return orders;
    }

    @Override
    public List<OrderDto> getAllOrdersWaiting() throws ResourceNotFoundException {
        StatusDelivery statusDelivery = statusDeliveryRepository.findById(1)
                .orElseThrow(() -> new ResourceNotFoundException("Status not found for this id = " + 1));
        List<OrderDto> orders = new ArrayList<>();
        for (Order order : orderRepository.getAllOrdersWaiting(statusDelivery)) {
            OrderDto orderDto = conversionService.convert(order, OrderDto.class);
            orders.add(orderDto);
        }
        return orders;
    }

    @Override
    public Map<String, Boolean> deleteOrder(Integer orderId) throws ResourceNotFoundException {
        StatusDelivery statusDelivery = statusDeliveryRepository.findById(4)
                .orElseThrow(() -> new ResourceNotFoundException("Status not found for this id = " + 4));
        Map<String, Boolean> response = new HashMap<>();
        orderRepository.changeStatus(statusDelivery, orderId);
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}

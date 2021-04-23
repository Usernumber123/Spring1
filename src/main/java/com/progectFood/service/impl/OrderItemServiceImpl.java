package com.progectFood.service.impl;

import com.progectFood.controller.exception.ResourceNotFoundException;
import com.progectFood.domian.dto.OrderDto;
import com.progectFood.domian.dto.OrderItemDto;
import com.progectFood.domian.entity.Order;
import com.progectFood.domian.entity.OrderItem;
import com.progectFood.repository.OrderItemRepository;
import com.progectFood.repository.OrderRepository;
import com.progectFood.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;
    private final ConversionService conversionService;

    @Override
    public List<OrderItemDto> getOrderItemsByOrder(Integer id) throws ResourceNotFoundException {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id = " + 1));

        List<OrderItemDto> orders = new ArrayList<>();
        for (OrderItem orderItem : orderItemRepository.getOrderItemsByOrder(order)) {
            OrderItemDto orderItemDto = conversionService.convert(orderItem, OrderItemDto.class);
            orders.add(orderItemDto);
        }
        return orders;
    }
}

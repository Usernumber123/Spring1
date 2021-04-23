package com.progectFood.service;

import com.progectFood.controller.exception.ResourceNotFoundException;
import com.progectFood.domian.dto.OrderDto;


import java.util.List;
import java.util.Map;


public interface OrderService {
    List<OrderDto> getAllOrders() throws ResourceNotFoundException;
    List<OrderDto> getOrderById(Integer id)throws ResourceNotFoundException;
    List<OrderDto> getAllOrdersWaiting()throws  ResourceNotFoundException;
    Map<String,Boolean> deleteOrder(Integer orderId)throws ResourceNotFoundException;
}

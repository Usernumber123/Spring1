package com.progectFood.service;

import com.progectFood.controller.exception.ResourceNotFoundException;
import com.progectFood.domian.dto.OrderItemDto;

import java.util.List;

public interface OrderItemService {
    List<OrderItemDto> getOrderItemsByOrder(Integer id)throws ResourceNotFoundException;
}

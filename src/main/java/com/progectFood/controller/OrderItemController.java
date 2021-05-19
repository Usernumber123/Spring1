package com.progectFood.controller;

import com.progectFood.controller.exception.ResourceNotFoundException;
import com.progectFood.domian.dto.OrderItemDto;
import com.progectFood.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orderItems")
@RequiredArgsConstructor
public class OrderItemController {


    private final OrderItemService orderItemService;
    @SneakyThrows
    @GetMapping("/order/{id}")
    public List<OrderItemDto> getOrderItemsByOrder(@PathVariable(value = "id") Integer id) {

        return orderItemService.getOrderItemsByOrder(id);
    }


}
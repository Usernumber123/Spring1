package com.progectFood.controller;

import com.progectFood.controller.exception.ResourceNotFoundException;
import com.progectFood.domian.dto.OrderDto;
import com.progectFood.domian.entity.Order;
import com.progectFood.domian.entity.StatusDelivery;
import com.progectFood.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderService orderService;


    @GetMapping
    public List<OrderDto> getAllOrders() throws ResourceNotFoundException {

        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public List<OrderDto> getOrderById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        return orderService.getOrderById(id);
    }


    @GetMapping("/waitingDelivery")
    public List<OrderDto> getAllOrdersWaiting() throws ResourceNotFoundException {
        return orderService.getAllOrdersWaiting();
    }


    @Transactional
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteOrder(@PathVariable(value = "id") Integer orderId)
            throws ResourceNotFoundException {
        return orderService.deleteOrder(orderId);
    }


}

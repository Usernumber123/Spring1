package com.progectFood.controller;

import com.progectFood.controller.exception.ResourceNotFoundException;
import com.progectFood.domian.dto.OrderDto;
import com.progectFood.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


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
    @PutMapping("/status/{id}")
    public Map<String,Boolean> changeStatus(@PathVariable(value="id") Integer orderId)
            throws ResourceNotFoundException{
        return orderService.changeStatus(orderId);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteOrder(@PathVariable(value = "id") Integer orderId)
            throws ResourceNotFoundException {
        return orderService.deleteOrder(orderId);
    }
    @GetMapping("/customers")
    public List<OrderDto> getOrderByCust() throws ResourceNotFoundException{
        return orderService.getOrderByCust();
    }
    @GetMapping("/couriers")
    public List<OrderDto> getOrderByCourier() throws ResourceNotFoundException{
      return orderService.getOrderByCourier();
    }
    @GetMapping("/count")
    public Integer countOrder() throws ResourceNotFoundException{
      return orderService.countOrder();
    }
    @Transactional
    @PostMapping
    public void createOrder(@RequestBody OrderDto orderDto) throws ResourceNotFoundException{
        orderService.createOrder(orderDto);
    }

}

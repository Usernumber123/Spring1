package com.progectFood.controller;

import com.progectFood.controller.exception.ResourceNotFoundException;
import com.progectFood.domian.dto.OrderDto;
import com.progectFood.service.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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

    @SneakyThrows
    @GetMapping
    public List<OrderDto> getAllOrders(){

        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public List<OrderDto> getOrderById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        return orderService.getOrderById(id);
    }

    @SneakyThrows
    @GetMapping("/waitingDelivery")
    public List<OrderDto> getAllOrdersWaiting() {
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
    @SneakyThrows
    @GetMapping("/customers")
    public List<OrderDto> getOrderByCust(){
        return orderService.getOrderByCust();
    }
    @SneakyThrows
    @GetMapping("/couriers")
    public List<OrderDto> getOrderByCourier(){
      return orderService.getOrderByCourier();
    }
    @SneakyThrows
    @GetMapping("/count")
    public Integer countOrder() {
      return orderService.countOrder();
    }
    @SneakyThrows
    @Transactional
    @PostMapping
    public void createOrder(@RequestBody OrderDto orderDto) {
        orderService.createOrder(orderDto);
    }

}

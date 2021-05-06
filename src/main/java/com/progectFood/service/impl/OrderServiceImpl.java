package com.progectFood.service.impl;

import com.progectFood.controller.exception.ResourceNotFoundException;
import com.progectFood.domian.dto.DishDto;
import com.progectFood.domian.dto.OrderDto;
import com.progectFood.domian.entity.*;
import com.progectFood.repository.*;
import com.progectFood.security.UserDetailsImpl;
import com.progectFood.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    @Autowired
    DishRepository dishRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
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

    @Override
    public List<OrderDto> getOrderByCust() throws ResourceNotFoundException {
        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User cust = principal.getUser();
        StatusDelivery statusDelivery = statusDeliveryRepository.findById(4)
                .orElseThrow(() -> new ResourceNotFoundException("Status not found for this id = " + 4));
        List<OrderDto> orders = new ArrayList<>();
        for (Order order : orderRepository.getAllOrdersByCust(cust, statusDelivery)) {
            OrderDto orderDto = conversionService.convert(order, OrderDto.class);
            orders.add(orderDto);
        }
        return orders;
    }

    @Override
    public List<OrderDto> getOrderByCourier() throws ResourceNotFoundException {
        StatusDelivery statusDelivery = statusDeliveryRepository.findById(2)
                .orElseThrow(() -> new ResourceNotFoundException("Status not found for this id = " + 2));
        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User courier = principal.getUser();
        List<OrderDto> orders = new ArrayList<>();
        for (Order order : orderRepository.findOrderForCourier(statusDelivery, courier)) {
            OrderDto orderDto = conversionService.convert(order, OrderDto.class);
            orders.add(orderDto);
        }
        return orders;
    }

    @Override
    public Map<String, Boolean> changeStatus(Integer orderId) throws ResourceNotFoundException {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id = " + orderId));
        StatusDelivery status1 = statusDeliveryRepository.findById(1)
                .orElseThrow(() -> new ResourceNotFoundException("Status not found for this id = " + 1));

        StatusDelivery status2 = statusDeliveryRepository.findById(2)
                .orElseThrow(() -> new ResourceNotFoundException("Status not found for this id = " + 2));

        StatusDelivery status3 = statusDeliveryRepository.findById(3)
                .orElseThrow(() -> new ResourceNotFoundException("Status not found for this id = " + 3));

        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User courier = principal.getUser();

        if (order.getStatus().getStatus().equals(status1.getStatus())) {
            orderRepository.changeStatus(status2, orderId);
            orderRepository.setCourier(courier, orderId);
        } else if (order.getStatus().getStatus().equals(status2.getStatus())) {
            orderRepository.changeStatus(status3, orderId);
        }
        Map<String, Boolean> response = new HashMap<>();
        response.put("Status was changed", Boolean.TRUE);
        return response;
    }

    @Override
    public Integer countOrder() throws ResourceNotFoundException {
        StatusDelivery statusDelivery = statusDeliveryRepository.findById(2)
                .orElseThrow(() -> new ResourceNotFoundException("Status not found for this id = " + 2));
        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User courier = principal.getUser();
        return orderRepository.countOrderForCourier(statusDelivery, courier);
    }

    @Override
    public void createOrder(OrderDto orderDto) throws ResourceNotFoundException {
        StatusDelivery statusDelivery = statusDeliveryRepository.findById(1)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id = " + 1));
        Date date = new Date();
        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = principal.getUser();
        Order order = conversionService.convert(orderDto, Order.class);
        order.setUser(user);
        order.setDate(date);
        order.setStatus(statusDelivery);
        Order orderForItem = orderRepository.save(order);
        Integer total = 0;

        for (DishDto dish : orderDto.getDishes()) {
            Dish dishItem = dishRepository.findById(dish.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Dish not found for this id = " + dish.getId()));
            total += dishItem.getPrice() * dish.getNumber();
            orderItemRepository.save(new OrderItem(dishItem, dish.getNumber(), orderForItem));
        }
        orderRepository.putTotal(total, orderForItem.getId());

    }


}

package com.progectFood.converter;

import com.progectFood.domian.dto.OrderDto;
import com.progectFood.domian.entity.Order;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderDtoOrderConverter implements Converter<OrderDto, Order> {

    @Override
    public Order convert(OrderDto orderDto) {
        Order order = new Order();
        order.setAddress(orderDto.getAddress());
        order.setCoords(orderDto.getCoords());
        order.setRestaurant(orderDto.getRestaurant());
        return order;
    }
}
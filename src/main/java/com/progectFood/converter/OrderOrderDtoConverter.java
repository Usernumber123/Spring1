package com.progectFood.converter;

import com.progectFood.domian.dto.OrderDto;
import com.progectFood.domian.entity.Order;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderOrderDtoConverter implements Converter<Order, OrderDto> {
    @Override
    public OrderDto convert(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setAddress(order.getAddress());
        orderDto.setCoords(order.getCoords());
        orderDto.setRestaurant(order.getRestaurant());
        return orderDto;
    }
}
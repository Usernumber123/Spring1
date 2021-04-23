package com.progectFood.converter;

import com.progectFood.domian.dto.OrderItemDto;
import com.progectFood.domian.entity.OrderItem;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderItemDtoOrderItemConverter implements Converter<OrderItemDto, OrderItem> {

    @Override
    public OrderItem convert(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setDish(orderItemDto.getDish());
        orderItem.setNumber(orderItemDto.getNumber());
        orderItem.setOrder(orderItemDto.getOrder());
        return orderItem;
    }


}

package com.progectFood.converter;

import com.progectFood.domian.dto.OrderItemDto;
import com.progectFood.domian.entity.OrderItem;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderItemOrderItemDtoConverter  implements Converter<OrderItem, OrderItemDto> {

    @Override
    public OrderItemDto convert(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setDish(orderItem.getDish());
        orderItemDto.setNumber(orderItem.getNumber());
        orderItemDto.setOrder(orderItem.getOrder());
        return orderItemDto;
    }
}

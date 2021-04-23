package com.progectFood.domian.dto;

import com.progectFood.domian.entity.Dish;
import com.progectFood.domian.entity.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemDto {
    private Integer id;
    private Dish dish;
    private Integer number;
    private Order order;
}
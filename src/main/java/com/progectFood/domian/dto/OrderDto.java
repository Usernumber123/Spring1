package com.progectFood.domian.dto;

import com.progectFood.domian.entity.Restaurant;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@Data
@NoArgsConstructor
public class OrderDto {
    private Restaurant restaurant;
    private String address;
    private ArrayList<Double> coords;
    private ArrayList<DishDto> dishes;
}

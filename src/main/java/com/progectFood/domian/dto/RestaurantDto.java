package com.progectFood.domian.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@Data
@NoArgsConstructor
public class RestaurantDto {
    private Integer id;
    private String title;
    private ArrayList<Double> coords;
    private Integer openTime;
    private Integer closeTime;
}

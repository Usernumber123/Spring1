package com.progectFood.domian.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DishDto {

    private Integer id;
    private String title ;
    private Integer price ;
    private Integer number;

}

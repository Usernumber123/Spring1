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
    private String title ="123";
    private Integer price =0;
   // private Integer number;

}

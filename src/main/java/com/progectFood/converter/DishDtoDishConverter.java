package com.progectFood.converter;

import com.progectFood.domian.dto.DishDto;
import com.progectFood.domian.entity.Dish;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DishDtoDishConverter implements Converter<DishDto, Dish> {

    @Override
    public Dish convert(DishDto dishDto) {
        Dish dish = new Dish();
        //user.setId(userDto.getId());
        dish.setPrice(dishDto.getPrice());
      //  dish.setRestaurant(dishDto.getRestaurant());
        dish.setTitle(dishDto.getTitle());
        return dish;
    }
}

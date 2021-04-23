package com.progectFood.converter;

import com.progectFood.domian.dto.DishDto;
import com.progectFood.domian.entity.Dish;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DishDishDtoConverter implements Converter<Dish, DishDto> {
    @Override
    public DishDto convert(Dish dish) {
        DishDto dishDto = new DishDto();
        dishDto.setId(dish.getId());
        dishDto.setPrice(dish.getPrice());
        dishDto.setTitle(dish.getTitle());
        return dishDto;
    }
}

package com.progectFood.converter;

import com.progectFood.domian.dto.RestaurantDto;
import com.progectFood.domian.entity.Restaurant;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RestaurantDtoRestaurantConverter implements Converter<RestaurantDto, Restaurant> {
    @Override
    public Restaurant convert(RestaurantDto restaurantDto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setCloseTime(restaurantDto.getCloseTime());
        restaurant.setOpenTime(restaurantDto.getOpenTime());
        restaurant.setTitle(restaurantDto.getTitle());
        restaurant.setCoords(restaurantDto.getCoords());
        return restaurant;
    }


}

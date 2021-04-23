package com.progectFood.converter;


import com.progectFood.domian.dto.RestaurantDto;
import com.progectFood.domian.entity.Restaurant;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RestaurantRestaurantDtoConverter implements Converter<Restaurant, RestaurantDto> {
    @Override
    public RestaurantDto convert(Restaurant restaurant) {
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setId(restaurant.getId());
        restaurantDto.setCloseTime(restaurant.getCloseTime());
        restaurantDto.setCoords(restaurant.getCoords());
        restaurantDto.setOpenTime(restaurant.getOpenTime());
        restaurantDto.setTitle(restaurant.getTitle());
        return restaurantDto;
    }
}

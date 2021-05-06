package com.progectFood.service;

import com.progectFood.controller.exception.ResourceNotFoundException;
import com.progectFood.domian.dto.RestaurantDto;

import java.util.List;
import java.util.Map;

public interface RestaurantService {
    List<RestaurantDto> getAllActiveRestaurants() throws ResourceNotFoundException;

    List<RestaurantDto> getAllOpenRestaurants() throws ResourceNotFoundException;

    List<RestaurantDto> getRestaurantByLastName(String title) throws ResourceNotFoundException;

    Map<String, Boolean> deleteRestaurant(Integer restaurantId) throws ResourceNotFoundException;
}

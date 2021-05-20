package com.progectFood.service.impl;

import com.progectFood.controller.exception.ResourceNotFoundException;
import com.progectFood.domian.dto.RestaurantDto;
import com.progectFood.domian.entity.Restaurant;
import com.progectFood.domian.entity.StatusRestaurant;
import com.progectFood.repository.RestaurantRepository;
import com.progectFood.repository.StatusRestaurantRepository;
import com.progectFood.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final StatusRestaurantRepository statusRestaurantRepository;
    private final ConversionService conversionService;

    @Override
    public List<RestaurantDto> getAllActiveRestaurants() throws ResourceNotFoundException {
        StatusRestaurant status = statusRestaurantRepository.findById(1)
                .orElseThrow(() -> new ResourceNotFoundException("Status not found for this id = " + 1));
        ArrayList<RestaurantDto> restaurants = new ArrayList<>();
        for (Restaurant restaurant : restaurantRepository.getAllActiveRestaurants(status)) {
            RestaurantDto restaurantDto = conversionService.convert(restaurant, RestaurantDto.class);
            restaurants.add(restaurantDto);
        }
        return restaurants;
    }

    @Override
    public List<RestaurantDto> getAllOpenRestaurants() throws ResourceNotFoundException {
        StatusRestaurant status = statusRestaurantRepository.findById(1)
                .orElseThrow(() -> new ResourceNotFoundException("Status not found for this id = " + 1));
        ArrayList<RestaurantDto> restaurants = new ArrayList<>();
        for (Restaurant restaurant : restaurantRepository.getAllOpenRestaurants(status)) {
            RestaurantDto restaurantDto = conversionService.convert(restaurant, RestaurantDto.class);
            restaurants.add(restaurantDto);
        }
        return restaurants;
    }

    @Override
    public List<RestaurantDto> getRestaurantByLastName(String title) throws ResourceNotFoundException {
        StatusRestaurant status = statusRestaurantRepository.findById(1)
                .orElseThrow(() -> new ResourceNotFoundException("Status not found for this id = " + 1));
        ArrayList<RestaurantDto> restaurants = new ArrayList<>();
        for (Restaurant restaurant : restaurantRepository.findByTitle(title, status)) {
            RestaurantDto restaurantDto = conversionService.convert(restaurant, RestaurantDto.class);
            restaurants.add(restaurantDto);
        }
        return restaurants;
    }
    @SneakyThrows
    @Override
    public RestaurantDto createRestaurant(RestaurantDto restaurantDto) {
        StatusRestaurant status = statusRestaurantRepository.findById(1)
                .orElseThrow(() -> new ResourceNotFoundException("Status not found for this id = " + 1));
        Restaurant restaurant = conversionService.convert(restaurantDto, Restaurant.class);
        restaurant.setStatusRestaurant(status);
        restaurantRepository.save(restaurant);
        return  conversionService.convert(restaurant, RestaurantDto.class);
    }

    @Override
    public Map<String, Boolean> deleteRestaurant(Integer restaurantId) throws ResourceNotFoundException {
        StatusRestaurant status = statusRestaurantRepository.findById(2)
                .orElseThrow(() -> new ResourceNotFoundException("Status not found for this id = " + 2));
        Map<String, Boolean> response = new HashMap<>();
        restaurantRepository.changeStatus(status, restaurantId);
        response.put("status deleted", Boolean.TRUE);
        return response;
    }
}

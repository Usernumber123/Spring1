package com.progectFood.service.impl;

import com.progectFood.controller.exception.ResourceNotFoundException;
import com.progectFood.converter.DishDishDtoConverter;
import com.progectFood.converter.DishDtoDishConverter;
import com.progectFood.domian.dto.DishDto;
import com.progectFood.domian.entity.Dish;
import com.progectFood.domian.entity.Restaurant;
import com.progectFood.domian.entity.StatusRestaurant;
import com.progectFood.repository.DishRepository;
import com.progectFood.repository.RestaurantRepository;
import com.progectFood.repository.StatusRestaurantRepository;
import com.progectFood.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {
    @Autowired
    DishRepository dishRepository;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    StatusRestaurantRepository statusRestaurantRepository;


    @Override
    public List<DishDto> findDishesByRest(String title) throws ResourceNotFoundException {
        StatusRestaurant status = statusRestaurantRepository.findById(1)
                .orElseThrow(() -> new ResourceNotFoundException("Status not found for this id = " +1));
        List<Restaurant> rest = restaurantRepository.findByTitle(title, status);

        Restaurant restaurant = rest.get(0);
        List<DishDto> dishes = null;

        for (Dish dish:dishRepository.findDishesByRest(restaurant)) {
            dishes.add(new DishDishDtoConverter().convert(dish));
        }
        return dishes;
    }

    @Override
    public List<DishDto> findAll() {
        return null;
    }

    @Override
    public void save(DishDto dishDto) {
        dishRepository.save(new DishDtoDishConverter().convert(dishDto));
    }
}

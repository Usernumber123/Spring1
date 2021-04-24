package com.progectFood.service.impl;

import com.progectFood.controller.exception.ResourceNotFoundException;
import com.progectFood.domian.dto.DishDto;
import com.progectFood.domian.entity.Dish;
import com.progectFood.domian.entity.Restaurant;
import com.progectFood.domian.entity.StatusRestaurant;
import com.progectFood.repository.DishRepository;
import com.progectFood.repository.RestaurantRepository;
import com.progectFood.repository.StatusRestaurantRepository;
import com.progectFood.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {
    @Autowired
    DishRepository dishRepository;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    StatusRestaurantRepository statusRestaurantRepository;

    private final ConversionService conversionService;



    @Override
    public List<DishDto> findDishesByRest(String title) throws ResourceNotFoundException {
        StatusRestaurant status = statusRestaurantRepository.findById(1)
                .orElseThrow(() -> new ResourceNotFoundException("Status not found for this id = " + 1));
        List<Restaurant> rest = restaurantRepository.findByTitle(title, status);

        Restaurant restaurant = rest.get(0);
        List<DishDto> dishes = new ArrayList<DishDto>();

        for (Dish dish : dishRepository.findDishesByRest(restaurant)) {
            DishDto dishDto = conversionService.convert(dish, DishDto.class);
            dishes.add(dishDto);
        }
        return dishes;
    }

    @Override
    public List<DishDto> findAll() {
        List<DishDto> dishes = new ArrayList<>();
        for (Dish dish : dishRepository.findAll()) {
            DishDto dishDto = conversionService.convert(dish, DishDto.class);
            dishes.add(dishDto);
        }
        return dishes;
    }

    @Override
    public void save(DishDto dishDto) {
        Dish dish = conversionService.convert(dishDto, Dish.class);
        dishRepository.save(dish);
    }
}

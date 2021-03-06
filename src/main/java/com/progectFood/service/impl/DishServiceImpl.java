package com.progectFood.service.impl;

import com.progectFood.controller.exception.ResourceNotFoundException;
import com.progectFood.domian.dto.DishDto;
import com.progectFood.domian.entity.*;
import com.progectFood.repository.*;
import com.progectFood.service.DishService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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

    private final  DishRepository dishRepository;

    private final RestaurantRepository restaurantRepository;

    private final StatusRestaurantRepository statusRestaurantRepository;
    private final StatusRepository status;
    private final RoleRepository role;
    private final StatusDeliveryRepository  statusDeliveryRepository ;
    private final ConversionService conversionService;



    @Override
    @SneakyThrows
    public List<DishDto> findDishesByRest(String title)  {
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
    public DishDto save(DishDto dishDto) {
        Dish dish = conversionService.convert(dishDto, Dish.class);
        Dish dish1= dishRepository.save(dish);
        return conversionService.convert(dish1, DishDto.class);
    }

}

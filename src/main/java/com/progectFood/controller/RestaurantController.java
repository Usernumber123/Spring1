package com.progectFood.controller;

import com.progectFood.controller.exception.ResourceNotFoundException;
import com.progectFood.domian.dto.RestaurantDto;
import com.progectFood.service.OrderService;
import com.progectFood.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.security.Provider;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    OrderService orderService;


    @GetMapping
    public List<RestaurantDto> getAllActiveRestaurants() throws ResourceNotFoundException {
        return restaurantService.getAllActiveRestaurants();
    }

    @GetMapping("/open/show")
    public List<RestaurantDto> getAllOpenRestaurants() throws ResourceNotFoundException {
        return restaurantService.getAllOpenRestaurants();
    }

    @GetMapping("/{title}")
    public List<RestaurantDto> getRestaurantByLastName(@PathVariable(value = "title") String title) throws ResourceNotFoundException {
        return restaurantService.getRestaurantByLastName(title);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteRestaurant(@PathVariable(value = "id") Integer restaurantId)
            throws ResourceNotFoundException {

        return restaurantService.deleteRestaurant(restaurantId);

    }

}
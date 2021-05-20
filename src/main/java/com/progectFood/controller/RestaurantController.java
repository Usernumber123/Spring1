package com.progectFood.controller;

import com.progectFood.controller.exception.ResourceNotFoundException;
import com.progectFood.domian.dto.RestaurantDto;
import com.progectFood.service.OrderService;
import com.progectFood.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {


    private final RestaurantService restaurantService;


    @SneakyThrows
    @GetMapping
    public List<RestaurantDto> getAllActiveRestaurants()  {
        return restaurantService.getAllActiveRestaurants();
    }
    @SneakyThrows
    @GetMapping("/open/show")
    public List<RestaurantDto> getAllOpenRestaurants() {
        return restaurantService.getAllOpenRestaurants();
    }
    @SneakyThrows
    @GetMapping("/{title}")
    public List<RestaurantDto> getRestaurantByLastName(@PathVariable(value = "title") String title)  {
        return restaurantService.getRestaurantByLastName(title);
    }
    @SneakyThrows
    @PostMapping
    @PreAuthorize(value = "hasRole('ADMIN')")
    public RestaurantDto createRestaurant(@RequestBody RestaurantDto restaurantDto) {
     return restaurantService.createRestaurant(restaurantDto);
    }
    @Transactional
    @PreAuthorize(value = "hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteRestaurant(@PathVariable(value = "id") Integer restaurantId)
            throws ResourceNotFoundException {

        return restaurantService.deleteRestaurant(restaurantId);

    }

}

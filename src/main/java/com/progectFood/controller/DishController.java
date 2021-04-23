package com.progectFood.controller;

import com.progectFood.controller.exception.ResourceNotFoundException;
import com.progectFood.domian.dto.DishDto;
import com.progectFood.service.DishService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dishes")
@RequiredArgsConstructor
public class DishController {
    @Autowired
    DishService dishService;

    @GetMapping
    public List<DishDto> getAllDishes() {
        return dishService.findAll();
    }


    @GetMapping("/{title}")
    public List<DishDto> getDishesByRest(@PathVariable(value = "title") String title) throws ResourceNotFoundException {
        return dishService.findDishesByRest(title);
    }

    @PostMapping
    public void createDish(@RequestBody DishDto dishDto) {
        dishService.save(dishDto);
    }
}

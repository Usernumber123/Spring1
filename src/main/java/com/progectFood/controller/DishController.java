package com.progectFood.controller;

import com.progectFood.controller.exception.ResourceNotFoundException;
import com.progectFood.domian.dto.DishDto;
import com.progectFood.service.DishService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dishes")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    @GetMapping
    public List<DishDto> getAllDishes() {
        return dishService.findAll();
    }


    @GetMapping("/{title}")
    @SneakyThrows
    public List<DishDto> getDishesByRest(@PathVariable(value = "title") String title) {
        return dishService.findDishesByRest(title);
    }

    @PostMapping
    public DishDto createDish(@RequestBody DishDto dishDto) {
      return   dishService.save(dishDto);
    }
}

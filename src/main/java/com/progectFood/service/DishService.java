package com.progectFood.service;

import com.progectFood.controller.exception.ResourceNotFoundException;
import com.progectFood.domian.dto.DishDto;

import java.util.List;


public interface DishService {
    List<DishDto> findDishesByRest(String title) throws ResourceNotFoundException;

    List<DishDto> findAll();

    DishDto save(DishDto dishDto);
}

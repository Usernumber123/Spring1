package com.progectFood.service.impl;


import com.progectFood.controller.exception.ResourceNotFoundException;
import com.progectFood.domian.dto.DishDto;
import com.progectFood.domian.entity.Dish;
import com.progectFood.domian.entity.Restaurant;
import com.progectFood.repository.DishRepository;
import com.progectFood.repository.RestaurantRepository;
import com.progectFood.repository.StatusRestaurantRepository;
import com.progectFood.service.DishService;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.PathVariable;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class DishServiceImplTest {
        @InjectMocks
        private DishServiceImpl dishServiceImplMock;
        @Mock
        private DishRepository dishRepository;
        @Mock
        private ConversionService conversionService;
        @Mock
        private  RestaurantRepository restaurantRepository;
        @Mock
        private StatusRestaurantRepository statusRestaurantRepository;
    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    public void testCreate()  {
       val dishDto= DishDto.builder().build();
       val dish=new Dish();
       val savedDish=new Dish();
       val exceptedResult= DishDto.builder().build();

       Mockito.when(conversionService.convert(dishDto,Dish.class)).thenReturn(dish);
       Mockito.when(dishRepository.save(dish)).thenReturn(savedDish);
       Mockito.when(conversionService.convert(savedDish,DishDto.class)).thenReturn(exceptedResult);
       val resalt=  dishServiceImplMock.save(exceptedResult);

       assertSame(exceptedResult,resalt);
    }
    @Test
    public void testFindAllCallMethod()  {
        dishServiceImplMock.findAll();
        Mockito.verify(dishRepository,times(1)).findAll();
    }
    @Test
    public void testThrowExceptionFindDishesByRest() {
        Assertions.assertThrows(ResourceNotFoundException.class,()-> dishServiceImplMock.findDishesByRest(null));
    }

}

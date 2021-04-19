package com.progectFood.repository;

import com.progectFood.domian.entity.Dish;
import com.progectFood.domian.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {

    @Query("from Dish where restaurant=:rest")
    List<Dish> findDishesByRest(@Param("rest") Restaurant restaurant);

}

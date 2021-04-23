package com.progectFood.repository;


import com.progectFood.domian.entity.StatusRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRestaurantRepository extends JpaRepository<StatusRestaurant,Integer> {
}


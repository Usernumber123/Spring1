package com.progectFood.repository;


import com.progectFood.domian.entity.Restaurant;
import com.progectFood.domian.entity.StatusRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {

    @Query("from Restaurant where status=:status order by id")
    List<Restaurant> getAllActiveRestaurants(@Param("status") StatusRestaurant status);

    @Modifying
    @Query("update Restaurant set status=:status where id=:id")
    void changeStatus(@Param("status") StatusRestaurant status, @Param("id") Integer id);

    @Query("from Restaurant where title=:title and status=:status")
    List<Restaurant> findByTitle(@Param("title") String title, @Param("status") StatusRestaurant status);

    @Query(value = "select * from restaurants as r where r.status=:status and r.close_time>date_part('hour',current_time) and r.open_time<=date_part('hour',current_time) order by id", nativeQuery = true)
    List<Restaurant> getAllOpenRestaurants(@Param("status") StatusRestaurant statusRestaurant);


}

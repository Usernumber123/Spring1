package com.progectFood.repository;


import com.progectFood.domian.entity.Order;
import com.progectFood.domian.entity.StatusDelivery;
import com.progectFood.domian.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("from Order where status<>:status order by id")
    List<Order> getAllOrders(@Param("status") StatusDelivery statusDelivery);

    @Query("from Order where id=:id and status<>:status")
    List<Order> getOrderById(@Param("id") Integer id, @Param("status") StatusDelivery statusDelivery);


    @Modifying
    @Query("update Order set status = :newStatus where id = :id")
    void changeStatus(@Param("newStatus") StatusDelivery newStatus, @Param("id") Integer id);


    @Query("from Order where status=:status order by id")
    List<Order> getAllOrdersWaiting(@Param("status") StatusDelivery statusDelivery);

    @Query("from Order where status<>:status and user=:customer order by id desc")
    List<Order> getAllOrdersByCust(@Param("customer") User customer, @Param("status") StatusDelivery statusDelivery);

    @Query("from Order where status=:status and courier=:courier order by id")
    List<Order> findOrderForCourier(@Param("status") StatusDelivery status, @Param("courier") User courier);

    @Query("select count(id) from Order where status=:status and courier=:courier")
    Integer countOrderForCourier(@Param("status") StatusDelivery status, @Param("courier") User courier);

    @Modifying
    @Query("update Order set courier =:courier where id = :id")
    void setCourier(@Param("courier") User courier, @Param("id") Integer id);

    @Modifying
    @Query("update Order set total = :total where id = :id")
    void putTotal(@Param("total") Integer total, @Param("id") Integer id);

}

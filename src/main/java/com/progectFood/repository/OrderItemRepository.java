package com.progectFood.repository;


import com.progectFood.domian.entity.Order;
import com.progectFood.domian.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {

    List<OrderItem> getOrderItemsByOrder(Order order);
}

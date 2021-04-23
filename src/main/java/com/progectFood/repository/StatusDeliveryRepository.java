package com.progectFood.repository;

import com.progectFood.domian.entity.StatusDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusDeliveryRepository extends JpaRepository<StatusDelivery, Integer> {
}

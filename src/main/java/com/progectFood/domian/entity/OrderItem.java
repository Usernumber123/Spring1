package com.progectFood.domian.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Entity
@Table(name="orderitems")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="dish")
    private Dish dish;

    @Column(name="portionsnumber")
    private Integer number;

    @ManyToOne
    @JoinColumn(name="orders")
    private Order order;


}

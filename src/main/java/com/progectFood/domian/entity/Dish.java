package com.progectFood.domian.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "dishes")
@Getter
@Setter
public class Dish extends EntityBase{



    @Column(name = "title")
    @NonNull
    private String title;

    @Column(name = "price")
    @NonNull
    private Integer price = 0;

    @ManyToOne
    @JoinColumn(name = "restaurant")
   // @NonNull
    private Restaurant restaurant = new Restaurant();


}

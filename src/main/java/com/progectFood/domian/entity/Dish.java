package com.progectFood.domian.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "dishes")
@Data
@NoArgsConstructor
public class Dish extends EntityBase{

   /* @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "id")
    private Integer id;*/

    @Column(name = "title")
    @NonNull
    private String title;

    @Column(name = "price")
    @NonNull
    private Integer price = 0;

    @ManyToOne
    @JoinColumn(name = "restaurant")
    @NonNull
    private Restaurant restaurant = new Restaurant();


}

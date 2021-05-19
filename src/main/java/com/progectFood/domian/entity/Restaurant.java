package com.progectFood.domian.entity;


import com.progectFood.domian.dto.RestaurantDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import io.swagger.models.auth.In;

import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name="restaurants")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant extends EntityBase{


    @Column(name="title")
    @NonNull
    private String title;

    @Column(name="coords")
    @NonNull
    private ArrayList<Double> coords;

    @Column(name="openTime")
    @NonNull
    private Integer openTime;

    @Column(name="closeTime")
    @NonNull
    private Integer closeTime;

    @ManyToOne
    @JoinColumn(name="status")
    private StatusRestaurant statusRestaurant;


}

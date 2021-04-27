package com.progectFood.domian.entity;

import com.progectFood.domian.dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name="orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order extends EntityBase{

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "id")
    private Integer id;*/

    @ManyToOne
    @JoinColumn(name = "restaurant")
    @NonNull
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "client")
    @NonNull
    private User user;

    @ManyToOne
    @JoinColumn(name = "courier")
    private User courier;

    @Column(name = "address")
    @NonNull
    private String address;

    @Column(name = "coords")
    @NonNull
    private ArrayList<Double> coords;

    @Column(name = "total")
    @NonNull
    private Integer total;

    @Column(name = "date")
    private Date date;

     @ManyToOne
       @JoinColumn(name="status")
    private StatusDelivery status;


}
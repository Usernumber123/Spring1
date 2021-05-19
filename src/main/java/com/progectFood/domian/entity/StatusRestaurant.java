package com.progectFood.domian.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name="statusrestaurant")
@Data
@NoArgsConstructor
public class StatusRestaurant {
    @Id
    @JoinColumn(name="id")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="status")
    @NonNull
    private String status;
}

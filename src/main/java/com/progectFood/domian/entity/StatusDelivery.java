package com.progectFood.domian.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name="statusdelivery")
@Data
@NoArgsConstructor
public class StatusDelivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name="id")
    private Integer id;

    @Column(name="status")
    @NonNull
    private String status;

}
